package lk.ijse.MilkBar.dao.custom.impl;


import lk.ijse.MilkBar.dao.custom.OrderDAO;

import lk.ijse.MilkBar.entity.Order;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Orders` (order_id, order_date, cus_id) VALUES (?,?,?)",entity.getOid(), Date.valueOf(entity.getDate()),entity.getCustomerID());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("order_id").replace("OID-", "")) + 1)) : "OID-001";
    }


    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT order_id FROM orders WHERE order_id=?",id);
        return rst.next();
    }

}
