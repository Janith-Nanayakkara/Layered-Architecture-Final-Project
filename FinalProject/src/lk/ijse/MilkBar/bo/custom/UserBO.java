package lk.ijse.MilkBar.bo.custom;

import lk.ijse.MilkBar.dto.UserDTO;
import lk.ijse.MilkBar.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO{

    ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException;

    boolean save(UserDTO entity) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO entity) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    UserDTO search(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
