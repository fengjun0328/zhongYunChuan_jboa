package cn.jboa.service;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherStatistics;

import java.util.List;
import java.util.Map;

/**
 * 报销单：Service
 */
public interface ClaimVoucherService {

    /**
     * 根据条件查询总数据量
     * @param createSn
     * @param dealSn
     * @param status
     * @param startDate
     * @param endDate
     * @return
     */
    int getClaimVoucherCount(String createSn, String dealSn, String status, String startDate, String endDate);

    /**
     * 根据条件查询当前页显示的数据
     * @param createSn
     * @param dealSn
     * @param status
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<ClaimVoucher> getClaimVoucherPage(String createSn, String dealSn, String status, String startDate, String endDate, Integer pageNo, Integer pageSize);

    /**
     * 获取报销单所有状态
     * @return
     */
    Map getAllStatusMap();

    /**
     * 添加報銷單
     * @param claimVoucher
     * @return
     */
    boolean saveClaimVoucher(ClaimVoucher claimVoucher);

    /**
     * 根据id获取报销单对象
     * @param id
     * @return
     */
    ClaimVoucher findClaimVoucherById(int id);

    /**
     * 根据id删除报销单
     * @param id
     * @return
     */
    boolean deleteClaimVoucherById(int id);

    /**
     * 修改报销单
     * @param claimVoucher
     * @return
     */
    boolean updateClaimVoucher(ClaimVoucher claimVoucher);

    /**
     * 根据条件：年、月、部门，查询报销单
     * @param year
     * @param selectMonth
     * @param departmentId
     * @return
     */
    List<ClaimVoucher> getClaimVoucherByModifyDate(int year, int selectMonth, int departmentId);


}
