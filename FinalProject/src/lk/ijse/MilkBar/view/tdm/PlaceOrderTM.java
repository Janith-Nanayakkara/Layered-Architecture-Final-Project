package lk.ijse.MilkBar.view.tdm;

import java.math.BigDecimal;

public class PlaceOrderTM {
     private String code;
     private String name;
     private int qty;
     private BigDecimal unitPrice;
     private BigDecimal total;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String code, String name, int qty, BigDecimal unitPrice, BigDecimal total) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public PlaceOrderTM(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
