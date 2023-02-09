package lk.ijse.MilkBar.dto;

public class EmployeeDTO {
    private String id ;
    private String name;
    private String address;
    private String email;
    private double salary;

    public EmployeeDTO(String id, String name, String address, String email, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.salary = salary;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
