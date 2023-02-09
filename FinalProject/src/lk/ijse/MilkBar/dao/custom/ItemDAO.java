package lk.ijse.MilkBar.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.MilkBar.dao.CRUDDAO;
import lk.ijse.MilkBar.dto.ItemDTO;
import lk.ijse.MilkBar.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CRUDDAO<Item> {


    boolean updateQty(Item itemDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException;

    ResultSet getDetails(String valueOf) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllIds() throws SQLException, ClassNotFoundException;


    String getLastId() throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
