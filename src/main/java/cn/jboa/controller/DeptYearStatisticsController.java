package cn.jboa.controller;

import cn.jboa.common.Constants;
import cn.jboa.entity.ClaimVouYearStatistics;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.Employee;
import cn.jboa.service.ClaimVouYearStatisticsService;
import cn.jboa.service.ClaimVoucherService;
import cn.jboa.util.ExportExcelUtil;
import cn.jboa.util.JFreeChatUtil;
import cn.jboa.util.PaginationSupport;
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
 * 处理有关公司报销单年度统计的请求
 */
@Controller
@RequestMapping("deptYearStatistics")
public class DeptYearStatisticsController {

    @Autowired
    private ClaimVouYearStatisticsService claimVouYearStatisticsService;

    @Autowired
    private ClaimVoucherService claimVoucherService;

    /**
     * 获取部门年度报销数据
     * @return
     */
    @RequestMapping("/findDeptYearStatisticsList")
    public String findDeptYearStatisticsList(HttpSession session, Model model,
                                             @RequestParam(defaultValue = "0")int startYear,
                                             @RequestParam(defaultValue = "0") int endYear,
                                             @RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "5") int pageSize){
        //获取当前登录对象，并获取部门id
        Employee employee = (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        int departmentId = employee.getDepartment().getId();
        //创建分页对象，并赋值
        PaginationSupport<ClaimVouYearStatistics> pageSupport = new PaginationSupport<ClaimVouYearStatistics>();
        pageSupport.setCurrPageNo(pageNo);
        pageSupport.setPageSize(pageSize);
        int totalCount = claimVouYearStatisticsService.findDeptYearStatisticsCount(startYear,endYear,departmentId);
        pageSupport.setTotalCount(totalCount);
        List<ClaimVouYearStatistics> items = claimVouYearStatisticsService.findDeptYearStatistics(startYear,endYear,departmentId,(pageNo-1)*pageSize,pageSize);
        pageSupport.setItems(items);
        //将参数放入Model作用域中
        model.addAttribute("startYear",startYear);
        model.addAttribute("endYear",endYear);
        model.addAttribute("pageSupport",pageSupport);
        return "jsp/statistics/deptYearStatistics_list";
    }

    /**
     * 获取年度报销单详细
     * @return
     */
    @RequestMapping("/findDeptYearStatisticsDetail")
    public String findDeptYearStatisticsDetail(Model model,int currYear,int departmentId){
        List<ClaimVoucher> deptDetailList = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0 , departmentId);
        Double detailCount = 0.0;
        for (ClaimVoucher cv: deptDetailList) {
            detailCount += cv.getTotalAccount();
        }
        //将查询条件、数据放入model中
        model.addAttribute("year",currYear);
        model.addAttribute("departmentId",departmentId);
        model.addAttribute("detailCount",detailCount);
        model.addAttribute("deptDetailList",deptDetailList);

        return "jsp/statistics/deptYearStatistics_detail";
    }

    /**
     * 导入Excel
     * @param response
     * @param currYear
     * @param departmentId
     * @return
     */
    @RequestMapping("/createDetailExcel")
    public boolean createDetailExcel(HttpServletResponse response,int currYear, int departmentId){
        List<ClaimVoucher> deptDetailList = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, departmentId);

        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        String deptName = "";
        for(ClaimVoucher cv:deptDetailList){
            i++;
            String index = new Integer(i).toString();
            String nameCell = cv.getCreator().getName();
            String totalCell = cv.getTotalAccount()+"";
            String yearCell = new Integer(currYear).toString();
            String deptNameCell = cv.getCreator().getDepartment().getName();
            deptName = deptNameCell;
            list.add(new String[]{index,nameCell,totalCell,yearCell,deptNameCell});
        }
        String fileName=currYear+"年"+deptName+"年度报销统计";
        try{
            ExportExcelUtil.createExcel(response, list, fileName,null,"dept");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 创建年度饼图
     * @param currYear
     * @param departmentId
     */
    @RequestMapping("/createDetailChart")
    public void createDetailChart(int currYear,int departmentId){
        List<ClaimVoucher> deptDetailList = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, departmentId);
        DefaultPieDataset dataset = new DefaultPieDataset();
        String deptName="";
        for(ClaimVoucher cv:deptDetailList){
            dataset.setValue(cv.getCreator().getName(), cv.getTotalAccount());
            deptName = cv.getCreator().getDepartment().getName();
        }
        String title =currYear+"年"+deptName+"年度报销统计饼图";
        JFreeChart chart = new JFreeChatUtil().createPieChar(dataset, title);
    }

}
