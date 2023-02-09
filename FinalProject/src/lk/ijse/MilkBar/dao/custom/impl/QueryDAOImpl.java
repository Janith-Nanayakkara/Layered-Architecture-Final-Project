package lk.ijse.MilkBar.dao.custom.impl;

import lk.ijse.MilkBar.dao.custom.QueryDAO;
import lk.ijse.MilkBar.entity.CustomEntity;
import lk.ijse.MilkBar.util.CrudUtil;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> searchOrder(String oid) throws SQLException, ClassNotFoundException {
       ResultSet rst= CrudUtil.execute("SELECT Orders.order_id,Orders.order_date,Orders.cus_id,OrderDetails.order_id,OrderDetails.item_code,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.order_id=OrderDetails.order_id where Orders.order_id=?",oid);
        ArrayList<CustomEntity> allRecords= new ArrayList<>();
        while (rst.next()) {
            String oid1 = rst.getString("oid");
            String date = rst.getString("date");
            String customerID = rst.getString("customerID");
            String itemCode = rst.getString("itemCode");
            int qty = rst.getInt("qty");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");

            CustomEntity customEntity = new CustomEntity(oid1, LocalDate.parse(date), customerID, itemCode, qty, unitPrice);
            allRecords.add(customEntity);
        }
        return allRecords;
    }
}