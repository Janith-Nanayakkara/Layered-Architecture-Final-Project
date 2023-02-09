package lk.ijse.MilkBar.bo.custom.impl;

import lk.ijse.MilkBar.bo.custom.EmployeeBO;
import lk.ijse.MilkBar.dao.DAOFactory;
import lk.ijse.MilkBar.dao.DAOTypes;
import lk.ijse.MilkBar.dao.custom.EmployeeDAO;
import lk.ijse.MilkBar.dto.EmployeeDTO;
import lk.ijse.MilkBar.entity.Employee;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
       ArrayList<Employee> all = employeeDAO.getAll();
       ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e : all){
            employeeDTOS.add(new EmployeeDTO(e.getId(),e.getName(),e.getAddress(),e.getEmail(),e.getSalary()));
        }
        return employeeDTOS;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(),dto.getAddress(), dto.getEmail(), dto.getSalary()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {

        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getAddress(), dto.getEmail(), dto.getSalary()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        Employee search = employeeDAO.search(id);
        return new EmployeeDTO(search.getId(), search.getName(), search.getAddress(), search.getEmail(), search.getSalary());
    }

   @Override
    public String generateEmployeeNewID() throws SQLException, ClassNotFoundException {
      return employeeDAO.generateNewID();
    }

    @Override
    public ArrayList<EmployeeDTO> getAllIds() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allIds = employeeDAO.getAllIds();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e : allIds){
            employeeDTOS.add(new EmployeeDTO(e.getId(),e.getName(),e.getAddress(),e.getEmail(),e.getSalary()));
        }
        employeeDTOS.toString();
        return employeeDTOS;
    }
    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
      return employeeDAO.exist(id);
    }
}
