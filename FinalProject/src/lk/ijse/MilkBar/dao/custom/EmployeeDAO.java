package lk.ijse.MilkBar.dao.custom;

import lk.ijse.MilkBar.dao.CRUDDAO;
import lk.ijse.MilkBar.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CRUDDAO<Employee> {

    ArrayList<String> loadEmployeeID() throws SQLException, ClassNotFoundException;

    ArrayList<Employee> getAllIds() throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
