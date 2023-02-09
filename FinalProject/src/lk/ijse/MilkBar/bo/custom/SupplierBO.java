package lk.ijse.MilkBar.bo.custom;

import lk.ijse.MilkBar.dto.SupplierDTO;
import lk.ijse.MilkBar.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO{
    ArrayList<SupplierDTO> getAllSupplier () throws SQLException, ClassNotFoundException;

    boolean existSupplier(String id) throws SQLException, ClassNotFoundException;

    boolean save(SupplierDTO entity) throws SQLException, ClassNotFoundException;

    boolean update(SupplierDTO entity) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    SupplierDTO search(String id) throws SQLException, ClassNotFoundException;

    String generateNewIDSupplier() throws SQLException, ClassNotFoundException;
}
