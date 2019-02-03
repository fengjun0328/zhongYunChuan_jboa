package cn.jboa.dao;

import cn.jboa.entity.ClaimVouYearStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报销汇总信息：Dao
 */
public interface ClaimVouYearStatisticsMapper {

    /**
     * 查询部门年度报销数据量
     * @param startYear
     * @param endYear
     * @param departmentId
     * @return
     */
    int findDeptYearStatisticsCount(@Param("startYear") int startYear, @Param("endYear")int endYear,
                                    @Param("departmentId") int departmentId);

    /**
     * 查询部门年度报销数据集合
     * @param startYear
     * @param endYear
     * @param departmentId
     * @param from
     * @param pageSize
     * @return
     */
    List<ClaimVouYearStatistics> findDeptYearStatistics(@Param("startYear") int startYear,@Param("endYear") int endYear,
                                                        @Param("departmentId") int departmentId, @Param("from") int from,
                                                        @Param("pageSize") int pageSize);

    /**
     * 查询公司年度统计
     * @param startYear
     * @param endYear
     * @return
     */
    List<ClaimVouYearStatistics> findYearStatistics(@Param("startYear") int startYear,@Param("endYear") int endYear);

    /**
     * 修改
     * @param claimVouYearStatistics
     * @return
     */
    int update(ClaimVouYearStatistics claimVouYearStatistics);

    /**
     * 添加
     * @param claimVouYearStatistics
     * @return
     */
    int save(ClaimVouYearStatistics claimVouYearStatistics);
}
