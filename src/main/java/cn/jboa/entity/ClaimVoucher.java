package cn.jboa.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class ClaimVoucher {
    private int id;
    private Date createTime;
    private String event;
    private double totalAccount;
    private String status;
    private Date modifyTime;

    private Employee nextDeal;
    private Employee creator;

    private Collection<ClaimVoucherDetail> detailList = new ArrayList<ClaimVoucherDetail>();
    private Collection<CheckResult> checkResultList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(double totalAccount) {
        this.totalAccount = totalAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        ClaimVoucher that = (ClaimVoucher) o;
        return id == that.id &&
                Double.compare(that.totalAccount, totalAccount) == 0 &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(event, that.event) &&
                Objects.equals(status, that.status) &&
                Objects.equals(modifyTime, that.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, event, totalAccount, status, modifyTime);
    }

    public Collection<CheckResult> getCheckResultList() {
        return checkResultList;
    }

    public void setCheckResultList(Collection<CheckResult> checkResultList) {
        this.checkResultList = checkResultList;
    }

    public Employee getNextDeal() {
        return nextDeal;
    }

    public void setNextDeal(Employee nextDeal) {
        this.nextDeal = nextDeal;
    }

    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Collection<ClaimVoucherDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(Collection<ClaimVoucherDetail> detailList) {
        this.detailList = detailList;
    }
}
