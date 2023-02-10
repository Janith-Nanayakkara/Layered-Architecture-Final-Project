package lk.ijse.MilkBar.dao.custom.impl;

import lk.ijse.MilkBar.dao.custom.UserDAO;
import lk.ijse.MilkBar.entity.User;
import lk.ijse.MilkBar.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from users";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList<User> userDTOS = new ArrayList<>();
        while (result.next()){
            userDTOS.add(new User(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            ));
        }
        return userDTOS;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Users VALUES (?, ?, ?,?)";
        return CrudUtil.execute(sql, entity.getEmpID(), entity.getUserName(), entity.getUserPassword(), entity.getRole());

    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        String sql = "update users set username=? ,user_password=? user_role=? where emp_id=?";
        return CrudUtil.execute(sql, entity.getUserName(), entity.getUserPassword(), entity.getRole(), entity.getEmpID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from users where emp_id = ?";
        return CrudUtil.execute(sql, id);
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Users WHERE emp_id = ?";
        ResultSet result = CrudUtil.execute(sql, id);
        if(result.next()) {
            return new User(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
