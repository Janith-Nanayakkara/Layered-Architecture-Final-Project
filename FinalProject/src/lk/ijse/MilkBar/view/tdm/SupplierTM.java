package lk.ijse.MilkBar.view.tdm;

public class SupplierTM implements Comparable<SupplierTM> {
   private String id;
   private String name;
   private String contact;
   private String email;
   private String company;

    public SupplierTM(String id) {
        this.id = id;
    }

    public SupplierTM() {
    }

    public SupplierTM(String id, String name, String contact, String email, String company) {
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

    @Override
    public String toString() {
        return "SupplierTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    @Override
    public int compareTo(SupplierTM o) {
        return id.compareTo(o.getId());
    }
}
