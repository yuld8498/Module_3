package dao;

import Model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao{
    private String jdbcURL = "jdbc:mysql://localhost:3306/module3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String selectAllCategory = "select * from category;";
    private static final String selectCategoryByID = "select categoryName from category where categoryID = ?;";

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
    @Override
    public List<Category> selectAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectAllCategory)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int categoryID = rs.getInt("categoryID");
                String categoryName = rs.getString("categoryName");
                listCategory.add(new Category(categoryID,categoryName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCategory;
    }

    @Override
    public Category selectTypeByID(int id) {
        Category category = null;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectCategoryByID)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int typeID = rs.getInt("categoryID");
                String typeName = rs.getString("categoryName");
                category = new Category(typeID, typeName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}
