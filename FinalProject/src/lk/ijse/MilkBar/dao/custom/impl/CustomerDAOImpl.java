package lk.ijse.MilkBar.dao.custom.impl;

import lk.ijse.MilkBar.dao.custom.CustomerDAO;
import lk.ijse.MilkBar.entity.Customer;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from customer";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList<Customer> customers = new ArrayList<>();
        while (result.next()){
            customers.add(new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            ));
        }
        return customers ;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, entity.getId(), entity.getName(), entity.getAddress(), entity.getContact());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "update Customer set cus_name=? ,address=?,tel_number=? where cus_id=?";
        return  (CrudUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getContact(),entity.getId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from Customer where cus_id = ?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Customer WHERE cus_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);
        if(result.next()) {
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT cus_id FROM Customer ORDER BY cus_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString(1);
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public ArrayList<Customer> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet result= CrudUtil.execute("SELECT cus_id FROM customer");
        ArrayList<Customer> customers=new ArrayList<>();
        while (result.next()){
            customers.add(
                    new Customer(
                            result.getString(1)
                    )
            );
        }
        return customers;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT cus_id FROM Customer WHERE cus_id=?", id);
        return rst.next();
    }

}
