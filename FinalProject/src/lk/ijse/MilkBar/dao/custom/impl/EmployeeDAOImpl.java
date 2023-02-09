package lk.ijse.MilkBar.dao.custom.impl;

import lk.ijse.MilkBar.dao.custom.EmployeeDAO;
import lk.ijse.MilkBar.entity.Employee;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from Employee";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList<Employee> employeeDTOS = new ArrayList<>();
        while (result.next()){
            employeeDTOS.add(new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5)
            ));
        }
        return employeeDTOS;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?,?)";
        return CrudUtil.execute(sql, entity.getId(), entity.getName(), entity.getAddress(), entity.getEmail(), entity.getSalary());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        String sql = "update Employee set emp_name=? ,address=?,email=?,salary=? where emp_id=?";
        return (CrudUtil.execute(sql, entity.getName(), entity.getAddress(), entity.getEmail(), entity.getSalary(), entity.getId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from Employee where emp_id = ?";
        return CrudUtil.execute(sql, id);
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Employee WHERE emp_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);
        if(result.next()) {
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT emp_id FROM Employee ORDER BY emp_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString(1);
            int newCustomerId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newCustomerId);
        } else {
            return "E00-001";
        }
    }
    @Override
    public ArrayList<String> loadEmployeeID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT emp_id FROM Employee";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> empIDList = new ArrayList<>();

        while (result.next()) {
            empIDList.add(result.getString(1));
        }
        return empIDList;
    }
    @Override
    public ArrayList<Employee> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT emp_id FROM Employee");
        ArrayList<Employee> employeeDTOS = new ArrayList<>();
        while (result.next()){
            employeeDTOS.add(new Employee(
                    result.getString(1)
            ));
        }
        return employeeDTOS;

    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT emp_id FROM Employee WHERE emp_id=?", id);
        return rst.next();
    }
}
