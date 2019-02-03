package cn.jboa.service.impl;

import cn.jboa.dao.ClaimVouYearStatisticsMapper;
import cn.jboa.entity.ClaimVouYearStatistics;
import cn.jboa.service.ClaimVouYearStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销汇总信息：service
 */
@Service("claimVouYearStatisticsService")
public class ClaimVouYearStatisticsServiceImpl implements ClaimVouYearStatisticsService {

    @Autowired
    private ClaimVouYearStatisticsMapper claimVouYearStatisticsMapper;

    @Override
    public int findDeptYearStatisticsCount(int startYear, int endYear, int departmentId) {
        return claimVouYearStatisticsMapper.findDeptYearStatisticsCount(startYear,endYear,departmentId);
    }

    @Override
    public List<ClaimVouYearStatistics> findDeptYearStatistics(int startYear, int endYear, int departmentId, int from, int pageSize) {
        return claimVouYearStatisticsMapper.findDeptYearStatistics(startYear,endYear,departmentId,from,pageSize);
    }

    @Override
    public List<ClaimVouYearStatistics> findYearStatistics(int startYear, int endYear) {
        return claimVouYearStatisticsMapper.findYearStatistics(startYear,endYear);
    }
}
