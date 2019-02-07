package cn.jboa.service.impl;

import cn.jboa.common.Constants;
import cn.jboa.dao.LeaveMapper;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假：service实现
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public int getLeaveCount(String createSn, String dealSn, String startDate, String endDate) {
        return leaveMapper.getLeaveCount(createSn,dealSn,startDate,endDate);
    }

    @Override
    public List<Leave> getLeavePage(String createSn, String dealSn, String startDate, String endDate, int from, int pageSize) {
        return leaveMapper.getLeavePage(createSn,dealSn,startDate,endDate,from,pageSize);
    }

    @Override
    public Leave findLeaveById(int id) {
        return leaveMapper.findLeaveById(id);
    }

    @Override
    public boolean checkLeave(Leave leave) {
        Leave leave2=leaveMapper.findLeaveById(leave.getId());
        leave2.setModifyTime(new Date());
        leave2.setStatus(leave.getStatus());
        leave2.setApproveOpinion(leave.getApproveOpinion());
        if (leaveMapper.checkLeave(leave2)==1)
            return true;
        return false;
    }

    @Override
    public boolean saveLeave(Leave leave) {
        leave.setCreateTime(new Date());
        leave.setStatus(Constants.LEAVESTATUS_APPROVING);
        if (leaveMapper.saveLeave(leave)==1)
            return true;
        return false;
    }

    @Override
    public Map getLeaveTypeMap() {
        return new HashMap(){
            {
                put(Constants.LEAVE_ANNUAL,Constants.LEAVE_ANNUAL);
                put(Constants.LEAVE_CASUAL,Constants.LEAVE_CASUAL);
                put(Constants.LEAVE_MARRIAGE,Constants.LEAVE_MARRIAGE);
                put(Constants.LEAVE_MATERNITY,Constants.LEAVE_MATERNITY);
                put(Constants.LEAVE_SICK,Constants.LEAVE_SICK);
            }
        };
    }
}
