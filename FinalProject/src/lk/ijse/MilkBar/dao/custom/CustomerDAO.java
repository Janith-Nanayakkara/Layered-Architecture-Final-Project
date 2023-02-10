package lk.ijse.MilkBar.dao.custom;

import lk.ijse.MilkBar.dao.CRUDDAO;
import lk.ijse.MilkBar.entity.Customer;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CRUDDAO<Customer> {
    ArrayList<Customer> getAllIds() throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
