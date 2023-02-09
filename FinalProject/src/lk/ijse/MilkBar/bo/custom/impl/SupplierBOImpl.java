package lk.ijse.MilkBar.bo.custom.impl;

import lk.ijse.MilkBar.bo.custom.SupplierBO;
import lk.ijse.MilkBar.dao.DAOFactory;
import lk.ijse.MilkBar.dao.DAOTypes;
import lk.ijse.MilkBar.dao.custom.SupplierDAO;
import lk.ijse.MilkBar.dto.SupplierDTO;
import lk.ijse.MilkBar.entity.Supplier;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
       ArrayList<Supplier> all = supplierDAO.getAll();
       ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier s:all) {
            supplierDTOS.add(new SupplierDTO(s.getId(),s.getName(),s.getContact(),s.getEmail(),s.getCompany()));
        }return supplierDTOS;
    }
    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
       return supplierDAO.exist(id);
    }
    @Override
    public boolean save(SupplierDTO entity) throws SQLException, ClassNotFoundException {
      return supplierDAO.save(new Supplier(entity.getId(),entity.getName(),entity.getContact(),entity.getEmail(), entity.getCompany()));
    }

    @Override
    public boolean update(SupplierDTO entity) throws SQLException, ClassNotFoundException {
       return supplierDAO.update(new Supplier(entity.getId(),entity.getName(),entity.getContact(),entity.getEmail(), entity.getCompany()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        Supplier search = supplierDAO.search(id);
        return new SupplierDTO(search.getId(),search.getName(),search.getContact(),search.getEmail(),search.getCompany());
    }

    @Override
    public String generateNewIDSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }
    public static ResultSet getAllIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT sup_id FROM Supplier");
    }
}
