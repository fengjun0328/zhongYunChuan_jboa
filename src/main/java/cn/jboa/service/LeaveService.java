package cn.jboa.service;

import cn.jboa.entity.Leave;

import java.util.List;
import java.util.Map;

/**
 * 请假：service
 */
public interface LeaveService {

    /**
     * 查询请假总条数
     * @param createSn
     * @param dealSn
     * @param startDate
     * @param endDate
     * @return
     */
    int getLeaveCount(String createSn, String dealSn, String startDate, String endDate);

    /**
     * 分页查询请假数据
     * @param createSn
     * @param dealSn
     * @param startDate
     * @param endDate
     * @param from
     * @param pageSize
     * @return
     */
    List<Leave> getLeavePage(String createSn, String dealSn, String startDate, String endDate, int from, int pageSize);

    /**
     * 查询请假条详细信息
     * @param id
     * @return
     */
    Leave findLeaveById(int id);

    /**
     * 审批请假条
     * @param leave
     * @return
     */
    boolean checkLeave(Leave leave);

    /**
     * 添加请假条
     * @param leave
     * @return
     */
    boolean saveLeave(Leave leave);

    /**
     * 获取请假类型Map集合
     * @return
     */
    Map getLeaveTypeMap();

}
