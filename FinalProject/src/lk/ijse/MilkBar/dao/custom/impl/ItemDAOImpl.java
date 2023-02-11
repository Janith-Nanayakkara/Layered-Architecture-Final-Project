package lk.ijse.MilkBar.dao.custom.impl;


import lk.ijse.MilkBar.dao.custom.ItemDAO;
import lk.ijse.MilkBar.dto.ItemDTO;
import lk.ijse.MilkBar.entity.Item;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from item";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList<Item> itemDTOS = new ArrayList<>();
        while (result.next()){
            itemDTOS.add(new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4)
            ));
        }
        return itemDTOS;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, entity.getCode(), entity.getName(), entity.getQtyOnHand(), entity.getUnitPrice());

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "update item set item_name=?,qtyOnHand=?,unitprice=?  where item_code=?";
        return  (CrudUtil.execute(sql, entity.getName(), entity.getQtyOnHand(), entity.getUnitPrice(), entity.getCode()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from item where  item_code= ?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM item WHERE item_code = ?";
        ResultSet result = CrudUtil.execute(sql, id);
        if(result.next()) {
            return new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4)

            );
        }
        return null;
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT item_code FROM item ORDER BY item_code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString(1);
            int newCustomerId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newCustomerId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT item_code FROM item WHERE item_code=?", id);
        return rst.next();
    }
}
