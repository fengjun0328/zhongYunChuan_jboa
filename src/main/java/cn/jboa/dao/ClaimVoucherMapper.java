package cn.jboa.dao;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 保险单：Dao
 */
public interface ClaimVoucherMapper {

    /**
     * 根据条件查询当前员工报销单数据量
     * @param createSn
     * @param dealSn
     * @param status
     * @param startDate
     * @param endDate
     * @return
     */
    int getClaimVoucherCount(@Param("createSn") String createSn, @Param("dealSn")String dealSn,
                             @Param("status")String status,@Param("startDate")String startDate,
                             @Param("endDate")String endDate);

    /**
     * 根据条件查询当前员工当前页显示数据
     * @param createSn
     * @param dealSn
     * @param status
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<ClaimVoucher> getClaimVoucherPage(@Param("createSn") String createSn, @Param("dealSn")String dealSn,
                                           @Param("status")String status,@Param("startDate")String startDate,
                                           @Param("endDate")String endDate, @Param("pageNo")Integer pageNo,
                                           @Param("pageSize")Integer pageSize);

    /**
     * 添加报销单那
     * @param claimVoucher
     * @return
     */
    int save(ClaimVoucher claimVoucher);

    /**
     * 根据id获取报销单
     * @param id
     * @return
     */
    ClaimVoucher get(int id);

    /**
     * 删除报销单
     * @param id
     * @return
     */
    int deleteClaimVoucherById(int id);

    /**
     * 修改报销单
     * @param claimVoucher
     * @return
     */
    int updateClaimVoucher(ClaimVoucher claimVoucher);

    /**
     * 查新报销单
     * @param startDate
     * @param endDate
     * @param departmentId
     * @return
     */
    List<ClaimVoucher> getClaimVoucherByModifyDate(@Param("startDate") Date startDate,
                                                   @Param("endDate") Date endDate,
                                                   @Param("departmentId") int departmentId);
}
