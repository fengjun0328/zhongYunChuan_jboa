package cn.jboa.dao;

import cn.jboa.entity.ClaimVouYearStatistics;
import cn.jboa.entity.ClaimVoucherStatistics;
import com.auth0.jwt.interfaces.Claim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报销汇总信息：Dao
 */
public interface ClaimVoucherStatisticsMapper {

    /**
     * 获取月度报销数据量
     *
     * @param year
     * @param startMonth
     * @param endMonth
     * @param deptId
     * @return
     */
    int getDeptClaimVoucherStatisticsCount(@Param("year") int year, @Param("startMonth") int startMonth,
                                           @Param("endMonth") int endMonth, @Param("deptId") int deptId);

    /**
     * 查询部门月度报销统计
     *
     * @param year
     * @param startMonth
     * @param endMonth
     * @param deptId
     * @param from
     * @param pageSize
     * @return
     */
    List<ClaimVoucherStatistics> getDeptClaimVoucherStatisticsByPage(@Param("year") int year, @Param("startMonth") int startMonth,
                                                                     @Param("endMonth") int endMonth, @Param("deptId") int deptId,
                                                                     @Param("from") int from, @Param("pageSize") int pageSize);

    /**
     * 查询公司月度统计
     *
     * @param year
     * @param startMonth
     * @param endMont
     * @return
     */
    List<ClaimVoucherStatistics> getClaimVoucherStatistics(@Param("year") int year, @Param("startMonth") int startMonth,
                                                           @Param("endMonth") int endMont);

    /**
     * 修改
     *
     * @param claimVoucherStatistics
     * @return
     */
    int update(ClaimVoucherStatistics claimVoucherStatistics);

    /**
     * 添加
     *
     * @param claimVoucherStatistics
     * @return
     */
    int save(ClaimVoucherStatistics claimVoucherStatistics);
}

