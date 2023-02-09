package lk.ijse.MilkBar.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.MilkBar.dao.CRUDDAO;
import lk.ijse.MilkBar.dto.OrderDTO;
import lk.ijse.MilkBar.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CRUDDAO<Order> {
    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
