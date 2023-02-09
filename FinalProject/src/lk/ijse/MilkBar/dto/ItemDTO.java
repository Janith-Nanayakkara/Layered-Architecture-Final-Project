package lk.ijse.MilkBar.dto;



public class ItemDTO {
private String code;
private String name;

private int qtyOnHand;
private double unitPrice;

    public ItemDTO(String code) {
        this.code = code;
    }

    public ItemDTO(String code, String name, int qtyOnHand, double unitPrice) {
        this.code = code;
        this.name = name;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public ItemDTO() {
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


    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
