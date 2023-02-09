package lk.ijse.MilkBar.dao.custom;

import lk.ijse.MilkBar.dao.SuperDAO;
import lk.ijse.MilkBar.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    ArrayList<CustomEntity> searchOrder(String oid) throws SQLException, ClassNotFoundException;
}
