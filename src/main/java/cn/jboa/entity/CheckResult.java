package cn.jboa.entity;

import java.util.Date;
import java.util.Objects;

public class CheckResult {
    private int id;
    private Date checkTime;
    private String result;
    private String comment;
    private ClaimVoucher claimVoucher;
    private Employee checkEmployee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Employee getCheckEmployee() {
        return checkEmployee;
    }

    public void setCheckEmployee(Employee checkEmployee) {
        this.checkEmployee = checkEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckResult that = (CheckResult) o;
        return id == that.id &&
                Objects.equals(checkTime, that.checkTime) &&
                Objects.equals(result, that.result) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkTime, result, comment);
    }

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }
}
