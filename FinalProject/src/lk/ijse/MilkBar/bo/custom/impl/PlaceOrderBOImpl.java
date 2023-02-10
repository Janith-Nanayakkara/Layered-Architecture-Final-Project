package lk.ijse.MilkBar.bo.custom.impl;

import lk.ijse.MilkBar.bo.custom.PlaceOrderBO;
import lk.ijse.MilkBar.dao.DAOFactory;
import lk.ijse.MilkBar.dao.DAOTypes;
import lk.ijse.MilkBar.dao.custom.CustomerDAO;
import lk.ijse.MilkBar.dao.custom.ItemDAO;
import lk.ijse.MilkBar.dao.custom.OrderDAO;
import lk.ijse.MilkBar.dao.custom.PlaceOrderDAO;
import lk.ijse.MilkBar.db.DBConnection;
import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.dto.ItemDTO;
import lk.ijse.MilkBar.dto.OrderDTO;
import lk.ijse.MilkBar.dto.PlaceOrderDTO;
import lk.ijse.MilkBar.entity.Customer;
import lk.ijse.MilkBar.entity.Item;
import lk.ijse.MilkBar.entity.Order;
import lk.ijse.MilkBar.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ORDER);
    PlaceOrderDAO placeOrderDAO = (PlaceOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.PLACEODER);

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getId(),c.getName(),c.getAddress(),c.getContact());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item i = itemDAO.search(code);
        return new ItemDTO(i.getCode(),i.getName(),i.getQtyOnHand(),i.getUnitPrice());
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerEntityData = customerDAO.getAll();
        ArrayList<CustomerDTO> convertToDto= new ArrayList<>();
        for (Customer c : customerEntityData) {
            convertToDto.add(new CustomerDTO(c.getId(),c.getName(),c.getAddress(),c.getContact()));
        }
        return convertToDto;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> entityTypeData = itemDAO.getAll();
        ArrayList<ItemDTO> dtoTypeData= new ArrayList<>();
        for (Item i : entityTypeData) {
            dtoTypeData.add(new ItemDTO(i.getCode(),i.getName(),i.getQtyOnHand(),i.getUnitPrice()));
        }
        return dtoTypeData;
    }


    @Override
    public boolean purchaseOrder(OrderDTO dto)throws SQLException, ClassNotFoundException{
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            boolean b1 = orderDAO.exist(dto.getOrderId());

            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);

            boolean b2 = orderDAO.save(new Order(dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId()));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (PlaceOrderDTO d : dto.getOrderDetails()) {
                PlaceOrder orderDetails = new PlaceOrder(d.getOid(),d.getItemCode(),d.getQty(),d.getUnitPrice());
                boolean b3 = placeOrderDAO.save(orderDetails);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                ItemDTO item = findItem(d.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - d.getQty());


                boolean b = itemDAO.update(new Item(item.getCode(), item.getName(), item.getQtyOnHand(),item.getUnitPrice()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemDTO findItem(String code)throws SQLException, ClassNotFoundException {
        try {
            Item i = itemDAO.search(code);
            return new ItemDTO(i.getCode(),i.getName(),i.getQtyOnHand(),i.getUnitPrice());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
