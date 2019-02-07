package cn.jboa.dao;

import cn.jboa.entity.Leave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请假：Dao
 */
public interface LeaveMapper {

    /**
     * 查询请假失踪条数
     * @param createSn
     * @param dealSn
     * @param startDate
     * @param endDate
     * @return
     */
    int getLeaveCount(@Param("createSn") String createSn,@Param("dealSn") String dealSn,
                      @Param("startDate") String startDate,@Param("endDate") String endDate);

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
    List<Leave> getLeavePage(@Param("createSn") String createSn,@Param("dealSn") String dealSn,
                             @Param("startDate") String startDate,@Param("endDate") String endDate,
                             @Param("from") int from,@Param("pageSize") int pageSize);

    /**
     * 查询请假条详细信息
     * @param id
     * @return
     */
    Leave findLeaveById(int id);

    /**
     * 修改请假条状态
     * @param leave
     * @return
     */
    int checkLeave(Leave leave);

    /**
     * 添加请假条
     * @param leave
     * @return
     */
    int saveLeave(Leave leave);
}
