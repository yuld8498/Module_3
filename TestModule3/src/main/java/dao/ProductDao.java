package dao;

import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao{
    private String jdbcURL = "jdbc:mysql://localhost:3306/module3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String SELECT_ALL_PRODUCT_SQL = "select * from product;";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "select * from product where productID = ?;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product" +
            "(productName, price, quantity, color, description,categoryID) VALUES" + "(?,?,?,?,?,?);";
    private static final String SELECT_ALL_PRODUCT_INNER_JOIN= "select p.productID, p.productName, p.price, p.quantity, p.color, p.description, " +
            "c.categoryID, c.categoryName" +
            " from product as p inner join category as c where p.categoryID = c.categoryID order by p.productID";
    private static final String DELETE_PRODUCT_SQL = "delete from product where productID = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update product" +
            " set productName = ?, price= ? ,quantity =?, color=?, description =?,categoryID =? where productID=?;";

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
    public void insertProduct(Product product) {
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public boolean deleteProduct(int  productID) {
        boolean rowDelete;
        try(Connection connection =getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, productID);
            rowDelete = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public boolean editProduct(Product product) {
        boolean updateProduct = true;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.setInt(7, product.getProductID());
            updateProduct = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateProduct;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> listProduct = new ArrayList<>();
        try(Connection connection =getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_INNER_JOIN)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color =rs.getString("color");
                String description = rs.getString("description");
                int categoryID = rs.getInt("categoryID");
                String categoryName = rs.getString("categoryName");
                listProduct.add(new Product(productID,productName,price,quantity,color, description,categoryID));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listProduct;
    }

    @Override
    public boolean deleteProductByID(int ID) {
        boolean rowDelete;
        try(Connection connection =getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, ID);
            rowDelete = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public Product selectProductByID(int id) {
        Product product = null;
        try(Connection connection =getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color =resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryID = resultSet.getInt("categoryID");
                product = new Product(productID,productName,price,quantity,color,description);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public boolean updateProductByID(Product product) {
        boolean updateProduct = true;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.setInt(7, product.getProductID());
            updateProduct = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateProduct;
    }
    public List<Product> selectProductFilter(int categoryID){
        String query = "select SQL_CALC_FOUND_ROWS p.productID, p.productName, p.Description,p.color, p.price, p.quantity,p.categoryID," +
                " c.categoryID, c.categoryName" +
                " from product as p inner join category as c on c.categoryID = p.categoryID where p.categoryID = "+categoryID;
        System.out.println("hello");
        List<Product> listProdcut = new ArrayList<>();
        Product product =null;
        Connection connection =null;
        Statement statement =null;
        try{
            connection =getConnection();
            statement= connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                product =new Product();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getDouble("price"));
                product.setColor(rs.getString("color"));
                product.setQuantity(Integer.parseInt(rs.getString("quantity")));
                product.setCategory(rs.getInt("categoryID"));
                listProdcut.add(product);
            }
            rs.close();
        } catch (SQLException e) {
            printSQLException(e);
        }finally {
            try {
                if (statement!=null){
                    statement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
        return listProdcut;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
