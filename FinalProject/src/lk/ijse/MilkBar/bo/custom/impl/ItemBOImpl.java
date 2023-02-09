package lk.ijse.MilkBar.bo.custom.impl;

import lk.ijse.MilkBar.bo.custom.ItemBO;
import lk.ijse.MilkBar.dao.DAOFactory;
import lk.ijse.MilkBar.dao.DAOTypes;
import lk.ijse.MilkBar.dao.custom.ItemDAO;
import lk.ijse.MilkBar.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MilkBar.dto.ItemDTO;
import lk.ijse.MilkBar.entity.Employee;
import lk.ijse.MilkBar.entity.Item;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item i : all) {
            itemDTOS.add(new ItemDTO(i.getCode(),i.getName(),i.getQtyOnHand(),i.getUnitPrice()));
        }
        return itemDTOS;
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
       return itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getQtyOnHand(), itemDTO.getUnitPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
       return itemDAO.update(new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getQtyOnHand(), itemDTO.getUnitPrice()) );
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
       return itemDAO.delete(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getCode(), item.getName(), item.getQtyOnHand(), item.getUnitPrice());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
      return itemDAO.generateNewID();
    }
}
