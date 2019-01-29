package cn.jboa.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class ClaimVouYearStatistics {
    private int id;
    private double totalCount;
    private int year;
    private Timestamp modifyTime;
    private Department dept;

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

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
