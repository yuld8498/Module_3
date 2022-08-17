package DAO;

import Model.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements IOrderDao{
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlisanpham?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String INSERT_ORDER_SQL =  "INSERT INTO order_details" +
            " (person_order, phoneNumber, city, orther_option, email, quantity, idProduct)" +
            " VALUES"+ " (?,?,?,?,?,?,?);";
    @Override
    public void InsertOrder(Order order) {
        try(Connection connection = getConnection(); PreparedStatement preparedStatement =connection.prepareStatement(INSERT_ORDER_SQL)){
            preparedStatement.setString(1,order.getUserName());
            preparedStatement.setString(2,order.getPhoneNumber());
            preparedStatement.setInt(3,order.getCity());
            preparedStatement.setString(4,order.getOrther_option());
            preparedStatement.setString(5,order.getEmail());
            preparedStatement.setInt(6,order.getProductQuaility());
            preparedStatement.setInt(7,order.getProductID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> showOrderList() {
        return null;
    }

    @Override
    public Order selectOrderByID(int ID) {
        return null;
    }

    @Override
    public void deleteOrderByID(int ID) {

    }
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
