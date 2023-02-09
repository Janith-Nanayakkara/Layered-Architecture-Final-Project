package lk.ijse.MilkBar.bo.custom;

import lk.ijse.MilkBar.dto.EmployeeDTO;
import lk.ijse.MilkBar.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO{
    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO entity) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO entity) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO search(String id) throws SQLException, ClassNotFoundException;

    String generateEmployeeNewID() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllIds() throws SQLException, ClassNotFoundException;

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException;
}
