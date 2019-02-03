package cn.jboa.service;

import cn.jboa.entity.ClaimVouYearStatistics;

import java.util.List;

/**
 * 报销汇总信息：service
 */
public interface ClaimVouYearStatisticsService {

    /**
     * 查询年度报销数据量
     * @param startYear
     * @param endYear
     * @param departmentId
     * @return
     */
    int findDeptYearStatisticsCount(int startYear, int endYear, int departmentId);

    /**
     * 查询年度报销数据集合
     * @param startYear
     * @param endYear
     * @param departmentId
     * @param from
     * @param pageSize
     * @return
     */
    List<ClaimVouYearStatistics> findDeptYearStatistics(int startYear, int endYear, int departmentId, int from, int pageSize);

    /**
     * 查询公司年度报销统计
     * @param startYear
     * @param endYear
     * @return
     */
    List<ClaimVouYearStatistics> findYearStatistics(int startYear, int endYear);
}
