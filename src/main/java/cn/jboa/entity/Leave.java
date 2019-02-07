package cn.jboa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class Leave {
    private int id;
    private Date startTime;
    private Date endTime;
    private double leaveDay;
    private String reason;
    private String status;
    private String leaveType;
    private String approveOpinion;
    private Date createTime;
    private Date modifyTime;
    private Employee creator;
    private Employee nextDeal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
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
                Objects.equals(endTime, leave.endTime) &&
                Objects.equals(reason, leave.reason) &&
                Objects.equals(status, leave.status) &&
                Objects.equals(leaveType, leave.leaveType) &&
                Objects.equals(approveOpinion, leave.approveOpinion) &&
                Objects.equals(createTime, leave.createTime) &&
                Objects.equals(modifyTime, leave.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, leaveDay, reason, status, leaveType, approveOpinion, createTime, modifyTime);
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
