package lk.ijse.MilkBar.bo.custom.impl;

import lk.ijse.MilkBar.bo.custom.CustomerBO;
import lk.ijse.MilkBar.dao.DAOFactory;
import lk.ijse.MilkBar.dao.DAOTypes;
import lk.ijse.MilkBar.dao.custom.CustomerDAO;
import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.entity.Customer;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(),c.getName(),c.getAddress(),c.getContact()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.exist(id);
    }
}
