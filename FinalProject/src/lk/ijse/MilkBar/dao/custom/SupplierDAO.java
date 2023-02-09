package lk.ijse.MilkBar.dao.custom;

import lk.ijse.MilkBar.dao.CRUDDAO;
import lk.ijse.MilkBar.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CRUDDAO<Supplier> {
    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
