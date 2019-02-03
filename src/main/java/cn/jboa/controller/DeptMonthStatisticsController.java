package cn.jboa.controller;

import cn.jboa.common.Constants;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.entity.Employee;
import cn.jboa.service.ClaimVoucherService;
import cn.jboa.service.ClaimVoucherStatisticsService;
import cn.jboa.util.ExportExcelUtil;
import cn.jboa.util.JFreeChatUtil;
import cn.jboa.util.PaginationSupport;
import com.sun.deploy.net.HttpResponse;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理有关公司报销单月度统计的请求
 */
@Controller
@RequestMapping("/deptMonthStatistics")
public class DeptMonthStatisticsController {

    @Autowired
    private ClaimVoucherStatisticsService claimVoucherStatisticsService;

    @Autowired
    private ClaimVoucherService claimVoucherService;

    /**
     * 获取部门月度报销统计
     * @return
     */
    @RequestMapping(value = "/getDeptStatisticsByMonth")
    public String getDeptStatisticsByMonth(Model model, HttpSession session,@RequestParam(defaultValue = "0") int year,
                                           @RequestParam(defaultValue = "0")int startMonth,@RequestParam(defaultValue = "0")int endMonth,
                                           @RequestParam(defaultValue = "0")int pageNo,@RequestParam(defaultValue = "0")int pageSize){
        //获取当前登录对象，并获取当前部门id
        Employee employee = (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        int deptId = employee.getDepartment().getId();
        //创建分页对象，并赋值
        PaginationSupport<ClaimVoucherStatistics> pageSupport = new PaginationSupport<ClaimVoucherStatistics>();
        pageNo = pageNo>0?pageNo:1;
        pageSize = pageSize>0?pageSize:5;
        pageSupport.setCurrPageNo(pageNo);
        pageSupport.setPageSize(pageSize);
        //查询当前登录的经理部门月度数据量
        int totalCount = claimVoucherStatisticsService.getDeptClaimVoucherStatisticsCount(year,startMonth,endMonth,deptId);
        pageSupport.setTotalCount(totalCount);
        //查询当前登录的经理部门，当前页显示报销月度数据
        List<ClaimVoucherStatistics> items = claimVoucherStatisticsService.getDeptClaimVoucherStatisticsByPage(year,startMonth,endMonth,deptId,(pageNo-1)*pageSize,pageSize);
        pageSupport.setItems(items);
        //将查询条件放入model，到页面
        model.addAttribute("year",year);
        model.addAttribute("startMonth",startMonth);
        model.addAttribute("endMonth",endMonth);
        //将当前页对象放入model
        model.addAttribute("pageSupport",pageSupport);
        return "jsp/statistics/deptMonthStatistics_list";
    }

    /**
     * 获取部门月度报销详细
     * @return
     */
    @RequestMapping("/getDeptVoucherDetailByMonth")
    public String getDeptVoucherDetailByMonth(Model model,@RequestParam(defaultValue = "0")int year,
                                              @RequestParam(defaultValue = "0")int selectMonth,
                                              @RequestParam(defaultValue = "0")int departmentId){
        List<ClaimVoucher> statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, selectMonth, departmentId);
        Double detailCount = 0.0;
        for (ClaimVoucher cv: statisticsDetailOfDeptMonth) {
            detailCount += cv.getTotalAccount();
        }
        //将查询条件、数据放入model中
        model.addAttribute("year",year);
        model.addAttribute("selectMonth",selectMonth);
        model.addAttribute("departmentId",departmentId);
        model.addAttribute("detailCount",detailCount);
        model.addAttribute("statisticsDetailOfDeptMonth",statisticsDetailOfDeptMonth);

        return "jsp/statistics/deptMonthStatistics_detail";
    }

    /**
     * 将报销数据导入到Excel
     * @return
     */
    @RequestMapping("/createDetailExcel")
    public boolean createDetailExcel(HttpServletResponse response, @RequestParam(defaultValue = "0")int year,
                                    @RequestParam(defaultValue = "0")int selectMonth,
                                    @RequestParam(defaultValue = "0")int departmentId){
        List<ClaimVoucher> statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, selectMonth, departmentId);
        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        String deptName="";
        for(ClaimVoucher cv: statisticsDetailOfDeptMonth){
            i++;
            String index = new Integer(i).toString();
            String nameCell = cv.getCreator().getName();
            String totalCell = cv.getTotalAccount()+"";
            String yearCell = new Integer(year).toString();
            String monthCell = new Integer(selectMonth).toString();
            String deptNameCell = cv.getCreator().getDepartment().getName();
            deptName = deptNameCell;
            list.add(new String[]{index,nameCell,totalCell,yearCell,monthCell,deptNameCell});
        }
        String fileName=year+"年"+selectMonth+"月"+deptName+"月度报销统计";
        try{
            ExportExcelUtil.createExcel(response, list, fileName,"monthly","dept");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 月度饼图
     */
    @RequestMapping("/createDetailChart")
    public void createDetailChart(int year,int selectMonth,int departmentId){
        List<ClaimVoucher> statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, selectMonth, departmentId);
        DefaultPieDataset dataset = new DefaultPieDataset();
        String deptName="";
        for(ClaimVoucher cv:statisticsDetailOfDeptMonth){
            dataset.setValue(cv.getCreator().getName(), cv.getTotalAccount());
            deptName = cv.getCreator().getDepartment().getName();
        }
        String title =year+"年"+selectMonth+"月"+deptName+"月度报销统计饼图";
        JFreeChart chart = new JFreeChatUtil().createPieChar(dataset, title);
    }
}
