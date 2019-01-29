package cn.jboa.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Leave {
    private int id;
    private Timestamp startTime;
    private Timestamp endtime;
    private double leaveDay;
    private String reason;
    private String status;
    private String leaveType;
    private String approveOpinion;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Employee creator;
    private Employee nextDeal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public double getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(double leaveDay) {
        this.leaveDay = leaveDay;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leave leave = (Leave) o;
        return id == leave.id &&
                Double.compare(leave.leaveDay, leaveDay) == 0 &&
                Objects.equals(startTime, leave.startTime) &&
                Objects.equals(endtime, leave.endtime) &&
                Objects.equals(reason, leave.reason) &&
                Objects.equals(status, leave.status) &&
                Objects.equals(leaveType, leave.leaveType) &&
                Objects.equals(approveOpinion, leave.approveOpinion) &&
                Objects.equals(createTime, leave.createTime) &&
                Objects.equals(modifyTime, leave.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endtime, leaveDay, reason, status, leaveType, approveOpinion, createTime, modifyTime);
    }

    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Employee getNextDeal() {
        return nextDeal;
    }

    public void setNextDeal(Employee nextDeal) {
        this.nextDeal = nextDeal;
    }
}
