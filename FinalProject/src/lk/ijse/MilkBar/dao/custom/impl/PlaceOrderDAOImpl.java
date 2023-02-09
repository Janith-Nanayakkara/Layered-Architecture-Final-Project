package lk.ijse.MilkBar.dao.custom.impl;

import lk.ijse.MilkBar.dao.custom.PlaceOrderDAO;
import lk.ijse.MilkBar.entity.PlaceOrder;

import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public ArrayList<PlaceOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public boolean save(PlaceOrder entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO OrderDetails (order_id, item_code, qty, unitPrice) VALUES (?,?,?,?)", entity.getOid(),entity.getItemCode(),entity.getUnitPrice(),entity.getQty());
    }

    @Override
    public boolean update(PlaceOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PlaceOrder search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
