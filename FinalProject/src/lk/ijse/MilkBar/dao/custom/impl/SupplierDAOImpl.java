package lk.ijse.MilkBar.dao.custom.impl;

import lk.ijse.MilkBar.dao.custom.SupplierDAO;
import lk.ijse.MilkBar.entity.Supplier;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from Supplier";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList<Supplier> supplierDTOS = new ArrayList<>();
        while (result.next()){
            supplierDTOS.add(new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            ));
        }
        return supplierDTOS;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Supplier VALUES (?, ?, ?, ?,?)";
        return CrudUtil.execute(sql, entity.getId(), entity.getName(), entity.getContact(), entity.getEmail(), entity.getCompany());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql = "update Supplier set sup_name=? ,contact=?,email=?,company=? where sup_id=?";
        return  (CrudUtil.execute(sql, entity.getName(), entity.getContact(), entity.getEmail(), entity.getCompany(), entity.getId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from Supplier where sup_id = ?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Supplier WHERE sup_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);
        if(result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT sup_id FROM Supplier ORDER BY sup_id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString(1);
            int newCustomerId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newCustomerId);
        } else {
            return "S00-001";
        }
    }
    public static ResultSet getAllIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT sup_id FROM Supplier");
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT sup_id FROM Supplier WHERE sup_id=?", id);
        return rst.next();
    }

}
