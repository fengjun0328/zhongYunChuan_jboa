package cn.jboa.dao;

import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucherDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报销单检查结果：DAO
 */
public interface CheckResultMapper {

    /**
     * 删除报销单审批结果
     * @param claimId
     * @return
     */
    int deleteCheckResultById(int claimId);

    /**
     * 获取报销单审批结果
     * @param claimId
     * @return
     */
    List<CheckResult> getCheckResultByClaimId(@Param("claimId") int claimId);

    /**
     * 添加报销单审批结果
     * @param checkResult
     * @return
     */
    int save(CheckResult checkResult);
}
