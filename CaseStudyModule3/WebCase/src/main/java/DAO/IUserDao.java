package DAO;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    public User login(String userName, String pw);
    public void insertUser(User user);

    public User selectUserByID(int id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;
    public boolean updateUserPassword(String  userName,String newPassword) throws SQLException;

    public boolean updateUser(User user) throws SQLException;
}
