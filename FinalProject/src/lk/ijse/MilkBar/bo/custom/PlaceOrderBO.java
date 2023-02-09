package lk.ijse.MilkBar.bo.custom;

import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.dto.ItemDTO;
import lk.ijse.MilkBar.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO{
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean existItem(String code) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateOrderID() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    boolean purchaseOrder(OrderDTO dto)throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String code)throws SQLException, ClassNotFoundException;
}
