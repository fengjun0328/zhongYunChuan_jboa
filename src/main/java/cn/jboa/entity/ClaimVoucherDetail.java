package cn.jboa.entity;

import java.util.Objects;

public class ClaimVoucherDetail {
    private int id;
    private String item;
    private double account;
    private String desc;
    private ClaimVoucher claimVoucher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaimVoucherDetail that = (ClaimVoucherDetail) o;
        return id == that.id &&
                Double.compare(that.account, account) == 0 &&
                Objects.equals(item, that.item) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, account, desc);
    }

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }
}
