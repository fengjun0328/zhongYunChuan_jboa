package cn.jboa.controller;

import cn.jboa.common.Constants;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.Employee;
import cn.jboa.exception.JboaException;
import cn.jboa.service.ClaimVoucherService;
import cn.jboa.util.PaginationSupport;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 处理有关报销单的请求
 */
@Controller
@RequestMapping("/claimVoucher")
public class ClaimVoucherController {

    @Autowired
    private ClaimVoucherService claimVoucherService; //报销单业务类

    private Map statusMap;

    /**
     * 查询当前员工所有报销单
     * @return
     */
    @RequestMapping(value = "/searchClaimVoucher")
    public String searchClaimVoucher(HttpSession session, String startDate, String endDate, String status
                                    , Integer pageNo, Integer pageSize, Model model){
        String createSn="";
        String dealSn="";
        Employee employee= (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        if (employee.getPosition().getNameCn().equals(Constants.POSITION_STAFF)){
            createSn=employee.getSn();
        }else{
            dealSn=employee.getSn();
        }
        if (pageNo==null || pageSize==null){
            pageNo=1;
            pageSize=5;
        }
        //创建分页工具类对象
        PaginationSupport<ClaimVoucher> pageSupport = new PaginationSupport<ClaimVoucher>();
        pageSupport.setCurrPageNo(pageNo);
        pageSupport.setPageSize(pageSize);
        int totalCount = claimVoucherService.getClaimVoucherCount(createSn,dealSn,status,startDate,endDate);
        pageSupport.setTotalCount(totalCount);
        List<ClaimVoucher> items = claimVoucherService.getClaimVoucherPage(createSn,dealSn,status,startDate,endDate,pageSupport.getStartRow(),pageSize);
        pageSupport.setItems(items);
        model.addAttribute("pageSupport",pageSupport);
        model.addAttribute("statusMap",getStatusMap());
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("status",status);
        return "jsp/claim/claim_voucher_list";
    }

    /**
     * 进入添加报销单页面
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAdd(){
        return "jsp/claim/claim_voucher_edit";
    }

    /**
     * 添加報銷單
     * @return
     */
    @RequestMapping(value = "/saveClaimVoucher")
    public String saveClaimVoucher(HttpSession session,ClaimVoucher claimVoucher){
        Employee employee= (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        claimVoucher.setCreator(employee);
        if (employee.getStatus().equals(Constants.CLAIMVOUCHER_SUBMITTED)){
            //状态为已提交，下一个处理人是经理
            claimVoucher.setNextDeal((Employee) session.getAttribute(Constants.AUTH_EMPLOYEE_MANAGER));
        }
        boolean bSave=false;
        try {
            bSave=claimVoucherService.saveClaimVoucher(claimVoucher);
        }catch (JboaException e){
            e.printStackTrace();
        }
        if (bSave){
            return "redirect:/claimVoucher/searchClaimVoucher";
        }else{
            return "jsp/claim/claim_voucher_edit";
        }
    }

    /**
     * 查询报销单信息
     * @return
     */
    @RequestMapping(value = "/getClaimVoucherById/{id}")
    public String getClaimVoucherById(Model model, @PathVariable(value = "id") int id){
        ClaimVoucher claimVoucher=claimVoucherService.findClaimVoucherById(id);
        model.addAttribute("claimVoucher",claimVoucher);
        return "jsp/claim/claim_voucher_view";
    }

    /**
     * 进入修改页面
     * @return
     */
    @RequestMapping(value = "/toUpdate/{id}")
    public String toUpdate(Model model,@PathVariable("id")int id){
        ClaimVoucher claimVoucher=claimVoucherService.findClaimVoucherById(id);
        model.addAttribute("claimVoucher",claimVoucher);
        return "jsp/claim/claim_voucher_update";
    }

    /**
     * 修改报销单
     * @return
     */
    @RequestMapping(value = "updateClaimVoucher")
    public String updateClaimVoucher(HttpSession session,ClaimVoucher claimVoucher){
        claimVoucher.setModifyTime(new Date());
        Employee employee = (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        claimVoucher.setCreator(employee);
        if (employee.getStatus().equals(Constants.CLAIMVOUCHER_SUBMITTED)){
            //状态为已提交，下一个处理人是经理
            claimVoucher.setNextDeal((Employee) session.getAttribute(Constants.AUTH_EMPLOYEE_MANAGER));
        }
        boolean bRet = claimVoucherService.updateClaimVoucher(claimVoucher);
        if (bRet){
            return "jsp/claim/claim_voucher_list";
        }else{
            return "redirect:/claimVoucher/toUpdate/"+claimVoucher.getId();
        }

    }

    /**
     * 删除报销单
     * @return
     */
    @RequestMapping(value = "/deleteClaimVoucherById/{id}")
    public String deleteClaimVoucherById(@PathVariable(value = "id") int id){
        boolean bDel=claimVoucherService.deleteClaimVoucherById(id);
        return "redirect:/claimVoucher/searchClaimVoucher";
    }

    /**
     * 进入审批页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/toCheck/{id}")
    public String toCheck(Model model,@PathVariable("id") int id){
        ClaimVoucher claimVoucher = claimVoucherService.findClaimVoucherById(id);
        model.addAttribute("claimVoucher",claimVoucher);
        return "jsp/claim/claim_voucher_check";
    }

    @RequestMapping(value = "/checkClaimVoucher")
    public String checkClaimVoucher(CheckResult checkResult){

        return "";
    }

    public Map getStatusMap() {
        return claimVoucherService.getAllStatusMap();
    }

    public void setStatusMap(Map statusMap) {
        this.statusMap = statusMap;
    }
}
