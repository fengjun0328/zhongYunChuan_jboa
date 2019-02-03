package cn.jboa.service;

import cn.jboa.entity.ClaimVouYearStatistics;
import cn.jboa.entity.ClaimVoucherStatistics;

import java.util.List;

/**
 * 报销汇总信息：service
 */
public interface ClaimVoucherStatisticsService {

    /**
     * 获取部门月度报销统计总数
     * @param year
     * @param startMonth
     * @param endMonth
     * @param deptId
     * @return
     */
    int getDeptClaimVoucherStatisticsCount(int year, int startMonth, int endMonth, int deptId);

    /**
     * 获取部门月度报销统计
     * @param year
     * @param startMonth
     * @param endMonth
     * @param deptId
     * @param from
     * @param pageSize
     * @return
     */
    List<ClaimVoucherStatistics> getDeptClaimVoucherStatisticsByPage(int year, int startMonth, int endMonth, int deptId, int from, int pageSize);

    /**
     * 查询公司月度报销统计
     * @param year
     * @param startMonth
     * @param endMonth
     * @return
     */
    List<ClaimVoucherStatistics> getClaimVoucherStatistics(int year, int startMonth, int endMonth);
}
