package lk.ijse.MilkBar.dao.custom;


import lk.ijse.MilkBar.dao.CRUDDAO;

import lk.ijse.MilkBar.entity.Item;


import java.sql.SQLException;

public interface ItemDAO extends CRUDDAO<Item> {

    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
