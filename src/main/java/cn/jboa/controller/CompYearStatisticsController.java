package cn.jboa.controller;

import cn.jboa.entity.ClaimVouYearStatistics;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.service.ClaimVouYearStatisticsService;
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
 * 处理有关公司年度报销数据统计的请求
 */
@Controller
@RequestMapping("/compYearStatistics")
public class CompYearStatisticsController {

    @Autowired
    private ClaimVouYearStatisticsService claimVouYearStatisticsService;

    @Autowired
    private ClaimVoucherService claimVoucherService;

    /**
     * 获取所有报销数据
     *
     * @return
     */
    @RequestMapping("/getList")
    public String getList(Model model,
                          @RequestParam(defaultValue = "0") int startYear,
                          @RequestParam(defaultValue = "0") int endYear) {
        int totalCount = claimVouYearStatisticsService.findDeptYearStatisticsCount(startYear,endYear,0);
        List<ClaimVouYearStatistics> compYearList = claimVouYearStatisticsService.findYearStatistics(startYear,endYear);
        model.addAttribute("startYear", startYear);
        model.addAttribute("endYear", endYear);
        model.addAttribute("compYearList", compYearList);
        return "jsp/statistics/compYearStatistics_list";
    }

    /**
     * 获取公司月度报销详细
     *
     * @return
     */
    @RequestMapping("/getDetail")
    public String getDetail(Model model,
                            @RequestParam(defaultValue = "0") int currYear) {
        List<ClaimVoucher> compYearDetail = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, 0);
        Double totalCount = 0.0;
        for (ClaimVoucher cv : compYearDetail) {
            totalCount += cv.getTotalAccount();
        }
        model.addAttribute("currYear", currYear);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("compYearDetail", compYearDetail);
        return "jsp/statistics/compYearStatistics_detail";
    }

    /**
     * 导入Excel
     *
     * @return
     */
    @RequestMapping("/getDetailExcel")
    public boolean getDetailExcel(HttpServletResponse response,
                                  @RequestParam(defaultValue = "0") int currYear) {
        List<ClaimVoucher> statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, 0);
        List<String[]> list = new ArrayList<String[]>();
        int i = 0;

        for (ClaimVoucher cv : statisticsDetailOfDeptMonth) {
            i++;
            String index = new Integer(i).toString();
            String nameCell = cv.getCreator().getName();
            String totalCell = cv.getTotalAccount() + "";
            String monthCell = new Integer(currYear).toString();
            list.add(new String[]{index, nameCell, totalCell, monthCell, null});
        }
        String fileName = currYear + "年"  + "公司年度报销统计";
        try {
            ExportExcelUtil.createExcel(response, list, fileName, "monthly",null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
