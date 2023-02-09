package lk.ijse.MilkBar.bo.custom;

import lk.ijse.MilkBar.dto.ItemDTO;
import lk.ijse.MilkBar.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO{
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
