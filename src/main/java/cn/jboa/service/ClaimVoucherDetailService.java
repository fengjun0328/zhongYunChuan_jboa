package cn.jboa.service;

import cn.jboa.entity.ClaimVoucherDetail;

/**
 * 保险单详细信息结果：Service
 */
public interface ClaimVoucherDetailService {

    /**
     * 添加报销单报销项目
     * @param claimVoucherDetail
     * @return id
     */
    int saveClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail);

    /**
     * 删除报销单报销项目
     * @param id
     * @return
     */
    boolean deleteClaimVoucherDetail(int id);
}
