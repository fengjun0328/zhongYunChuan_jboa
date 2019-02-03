package cn.jboa.entity;

import java.util.Date;
import java.util.Date;
import java.util.Objects;

public class ClaimVouYearStatistics {
    private int id;
    private double totalCount;
    private int year;
    private Date modifyTime;
    private Department department;

    public ClaimVouYearStatistics(Double totalCount, int year, Date modifyTime, Department dept) {
        this.totalCount = totalCount;
        this.year = year;
        this.modifyTime = modifyTime;
        this.department = dept;
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
        ClaimVouYearStatistics that = (ClaimVouYearStatistics) o;
        return id == that.id &&
                Double.compare(that.totalCount, totalCount) == 0 &&
                year == that.year &&
                Objects.equals(modifyTime, that.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCount, year, modifyTime);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
