package cn.jboa.entity;

import java.util.Collection;
import java.util.Objects;

public class Department {
    private int id;
    private String name;
    private Collection<ClaimVoucherStatistics> claimVouchers;
    private Collection<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<ClaimVoucherStatistics> getClaimVouchers() {
        return claimVouchers;
    }

    public void setClaimVouchers(Collection<ClaimVoucherStatistics> claimVouchers) {
        this.claimVouchers = claimVouchers;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
