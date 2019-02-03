package cn.jboa.service;

import cn.jboa.entity.CheckResult;

/**
 * 保险审批结果：Service
 */
public interface CheckResultService{

    /**
     * 审批报销单
     * @param checkResult
     * @return
     */
    boolean saveCheckResult(CheckResult checkResult);
}
