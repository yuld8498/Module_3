package dao;

import Model.Product;

import java.util.List;

public interface IProductDao {
    void insertProduct(Product product);
    boolean deleteProduct(int  productID);
    boolean editProduct(Product product);
    public List<Product> selectAllProduct();
    public boolean deleteProductByID(int ID);
    public Product selectProductByID(int id);
    public boolean updateProductByID(Product product);
     List<Product> selectProductFilter(int categoryID);
}
