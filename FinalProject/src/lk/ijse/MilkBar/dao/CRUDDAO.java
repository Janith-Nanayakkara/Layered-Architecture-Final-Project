package lk.ijse.MilkBar.dao;

import lk.ijse.MilkBar.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface
CRUDDAO <T> extends SuperDAO{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}
