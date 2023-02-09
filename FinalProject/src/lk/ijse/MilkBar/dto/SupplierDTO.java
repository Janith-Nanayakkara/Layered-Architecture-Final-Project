package lk.ijse.MilkBar.dto;

public class SupplierDTO {
   private String id;
   private String name;
   private String contact;
   private String email;
   private String company;

    public SupplierDTO(String id) {
        this.id = id;
    }

    public SupplierDTO() {
    }

    public SupplierDTO(String id, String name, String contact, String email, String company) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
