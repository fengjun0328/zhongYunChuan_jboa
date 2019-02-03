package cn.jboa.dao;

import cn.jboa.entity.ClaimVoucherDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 保险单详细项目：Dao
 */
public interface ClaimVoucherDetailMapper {

    /**
     * 添加报销单报销项目
     * @param detail
     * @return
     */
    int save(ClaimVoucherDetail detail);

    /**
     * 根据保险单id删除报销单报销项目
     * @param mainId
     * @return
     */
    int deleteClaimVoucherDetailByMainId(int mainId);

    /**
     * 根据报销项目id删除报销单报销项目
     * @param id
     * @return
     */
    int deleteClaimVoucherDetailById(int id);

    /**
     * 获取报销单报销项目集合
     * @param claimId
     * @return
     */
    List<ClaimVoucherDetail> getDetailListByClaimId(@Param("claimId") int claimId);
}
