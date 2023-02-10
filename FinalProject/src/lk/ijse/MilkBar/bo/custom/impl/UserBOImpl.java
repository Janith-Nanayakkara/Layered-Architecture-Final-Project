package lk.ijse.MilkBar.bo.custom.impl;

import lk.ijse.MilkBar.bo.custom.UserBO;
import lk.ijse.MilkBar.dao.DAOFactory;
import lk.ijse.MilkBar.dao.DAOTypes;
import lk.ijse.MilkBar.dao.custom.UserDAO;
import lk.ijse.MilkBar.dto.UserDTO;
import lk.ijse.MilkBar.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.USER);
    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserDTO> userDTOS=new ArrayList<>();
        for (User u : all) {
            userDTOS.add(new UserDTO(u.getEmpID(),u.getUserName(),u.getUserName()));
        }
        return userDTOS;
    }

    @Override
    public boolean save(UserDTO entity) throws SQLException, ClassNotFoundException {
       return userDAO.save(new User(entity.getEmpID(), entity.getUserName(), entity.getUserPassword(), entity.getRole()));
    }

    @Override
    public boolean update(UserDTO entity) throws SQLException, ClassNotFoundException {
       return userDAO.update(new User(entity.getEmpID(), entity.getUserName(), entity.getUserPassword(), entity.getRole()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       User user=new User();
       user.setEmpID(id);
        return userDAO.update(user);
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(id);
        return new UserDTO(user.getEmpID(), user.getUserName(), user.getUserPassword(), user.getRole());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       return userDAO.generateNewID();
    }
}
