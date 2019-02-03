package cn.jboa.service.impl;

import cn.jboa.dao.ClaimVoucherStatisticsMapper;
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.service.ClaimVoucherStatisticsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销汇总信息：service实现
 */
@Service("claimVoucherStatisticsService")
public class ClaimVoucherStatisticsServiceImpl implements ClaimVoucherStatisticsService {

    private Logger logger = Logger.getLogger(ClaimVoucherStatisticsServiceImpl.class);

    @Autowired
    private ClaimVoucherStatisticsMapper claimVoucherStatisticsMapper;

    @Override
    public int getDeptClaimVoucherStatisticsCount(int year, int startMonth, int endMonth, int deptId) {
        return claimVoucherStatisticsMapper.getDeptClaimVoucherStatisticsCount(year,startMonth,endMonth,deptId);
    }

    @Override
    public List<ClaimVoucherStatistics> getDeptClaimVoucherStatisticsByPage(int year, int startMonth, int endMonth,
                                                                            int deptId, int from, int pageSize) {
        return claimVoucherStatisticsMapper.getDeptClaimVoucherStatisticsByPage(year,startMonth,endMonth,deptId,from,pageSize);
    }

    @Override
    public List<ClaimVoucherStatistics> getClaimVoucherStatistics(int year, int startMonth, int endMonth) {
        return claimVoucherStatisticsMapper.getClaimVoucherStatistics(year,startMonth,endMonth);
    }
}
