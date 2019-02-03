package cn.jboa.entity;

import java.util.Date;
import java.util.Objects;

public class ClaimVoucherStatistics {
    private int id;
    private double totalCount;
    private int year;
    private int month;
    private Date modifyTime;
    private Department department;

    public ClaimVoucherStatistics() {
    }

    public ClaimVoucherStatistics(double totalCount, int year, int month, Date modifyTime, Department department) {
        this.totalCount = totalCount;
        this.year = year;
        this.month = month;
        this.modifyTime = modifyTime;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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
        ClaimVoucherStatistics that = (ClaimVoucherStatistics) o;
        return id == that.id &&
                Double.compare(that.totalCount, totalCount) == 0 &&
                year == that.year &&
                month == that.month &&
                Objects.equals(modifyTime, that.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCount, year, month, modifyTime);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
