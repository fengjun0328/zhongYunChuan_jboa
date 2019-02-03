package cn.jboa.controller;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.service.ClaimVoucherService;
import cn.jboa.service.ClaimVoucherStatisticsService;
import cn.jboa.util.ExportExcelUtil;
import cn.jboa.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理有关公司月度报销数据统计的请求
 */
@Controller
@RequestMapping("/compMonthStatistics")
public class CompMonthStatisticsController {

    @Autowired
    private ClaimVoucherStatisticsService claimVoucherStatisticsService;

    @Autowired
    private ClaimVoucherService claimVoucherService;

    /**
     * 获取所有报销数据
     *
     * @return
     */
    @RequestMapping("/getList")
    public String getList(Model model,
                          @RequestParam(defaultValue = "0") int year,
                          @RequestParam(defaultValue = "0") int startMonth,
                          @RequestParam(defaultValue = "0") int endMonth) {
        //创建分页对象，并赋值
        int totalCount = claimVoucherStatisticsService.getDeptClaimVoucherStatisticsCount(year, startMonth, endMonth, 0);
        List<ClaimVoucherStatistics> compMonthList = claimVoucherStatisticsService.getClaimVoucherStatistics(year, startMonth, endMonth);
        model.addAttribute("currYear", year);
        model.addAttribute("startMonth", startMonth);
        model.addAttribute("endMonth", endMonth);
        model.addAttribute("compMonthList", compMonthList);
        return "jsp/statistics/compMonthStatistics_list";
    }

    /**
     * 获取公司月度报销详细
     *
     * @return
     */
    @RequestMapping("/getDetail")
    public String getDetail(Model model,
                            @RequestParam(defaultValue = "0") int year,
                            @RequestParam(defaultValue = "0") int currMonth) {
        List<ClaimVoucher> compMonthDetail = claimVoucherService.getClaimVoucherByModifyDate(year, currMonth,0);
        Double totalCount = 0.0;
        for (ClaimVoucher cv : compMonthDetail) {
            totalCount += cv.getTotalAccount();
        }
        model.addAttribute("year", year);
        model.addAttribute("currMonth", currMonth);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("compMonthDetail", compMonthDetail);
        return "jsp/statistics/compMonthStatistics_detail";
    }

    /**
     * 导入Excel
     *
     * @return
     */
    @RequestMapping("/getDetailExcel")
    public boolean getDetailExcel(HttpServletResponse response,
                                  @RequestParam(defaultValue = "0") int year,
                                  @RequestParam(defaultValue = "0") int currMonth) {
        List<ClaimVoucher> statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, currMonth, 0);
        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        for (ClaimVoucher cv : statisticsDetailOfDeptMonth) {
            i++;
            String index = new Integer(i).toString();
            String nameCell = cv.getCreator().getName();
            String totalCell = cv.getTotalAccount() + "";
            String yearCell = new Integer(year).toString();
            String monthCell = new Integer(currMonth).toString();
            list.add(new String[]{index, nameCell, totalCell, yearCell, monthCell, null});
        }
        String fileName = year + "年" + currMonth  + "公司月度报销统计";
        try {
            ExportExcelUtil.createExcel(response, list, fileName, "monthly", null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
