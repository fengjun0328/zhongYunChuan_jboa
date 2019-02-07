package cn.jboa.controller;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;
import cn.jboa.util.PaginationSupport;
import cn.jboa.util.RedisCacheStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理有关请假条的请求
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }

    /**
     * 查询请假条
     * @return
     */
    @RequestMapping("/searchLeave")
    public String searchLeave(HttpSession session, Model model,
                              @RequestParam(defaultValue = "") String startDate,
                              @RequestParam(defaultValue = "") String endDate,
                              @RequestParam(defaultValue = "1") int pageNo,
                              @RequestParam(defaultValue = "5") int pageSize){
        Employee employee= (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        String createSn="";
        String dealSn="";
        if (employee.getPosition().getNameCn().equals(Constants.POSITION_STAFF)){
            createSn=employee.getSn();
        }else{
            dealSn=employee.getSn();
        }
        PaginationSupport<Leave> pageSupport=new PaginationSupport<Leave>();
        pageSupport.setCurrPageNo(pageNo);
        pageSupport.setPageSize(pageSize);
        int totalCount=leaveService.getLeaveCount(createSn,dealSn,startDate,endDate);
        pageSupport.setTotalCount(totalCount);
        List<Leave> items=leaveService.getLeavePage(createSn,dealSn,startDate,endDate,(pageNo-1)*pageSize,pageSize);
        pageSupport.setItems(items);
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("pageSupport",pageSupport);
        return "jsp/leave/leave_list";
    }

    /**
     * 查询请假详细信息
     * @return
     */
    @RequestMapping("/getLeaveById/{id}")
    public String getLeaveById(Model model,@PathVariable("id") int id){
        Leave leave=leaveService.findLeaveById(id);
        model.addAttribute("leave",leave);
        return "jsp/leave/leave_view";
    }

    /**
     * 进入审批页面
     * @return
     */
    @RequestMapping("/toCheck/{id}")
    public String toCheck(Model model,@PathVariable("id") int id){
        Leave leave=leaveService.findLeaveById(id);
        model.addAttribute("leave",leave);
        return "jsp/leave/leave_check";
    }

    /**
     * 审批请假条
     * @return
     */
    @RequestMapping("/checkLeave")
    public String checkLeave(Leave leave){
        boolean flag=leaveService.checkLeave(leave);
        if (!flag)
            return "redirect:/toCheck/"+leave.getId();
        return "redirect:/leave/searchLeave";
    }

    @Autowired
    private RedisCacheStorage redisCacheStorage;

    private Map leaveTypeMap;

    /**
     * 进入添加请假条页面
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(Model model){
        model.addAttribute("leaveTypeMap",getLeaveTypeMap());
        return "jsp/leave/leave_edit";
    }

    /**
     * 添加请假条
     * @param leave
     * @return
     */
    @RequestMapping("/saveLeave")
    public String saveLeave(Leave leave){
        boolean flag=leaveService.saveLeave(leave);
        if (!flag)
            return "jsp/leave/leave_edit";
        return "redirect:/leave/searchLeave";
    }

    public Map getLeaveTypeMap() {
        leaveTypeMap=redisCacheStorage.queryHashMapByKey("leaveTypeMap");
        if (leaveTypeMap==null){
            leaveTypeMap = leaveService.getLeaveTypeMap();
            redisCacheStorage.addHashMap("leaveTypeMap",leaveTypeMap);
            System.out.println("在redis中不存在leaveTypeMap");
        }
        return leaveTypeMap;
    }


}
