package Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Product {
    private int productID;
    private String productName;
    private double price;
    private  int quantity;
    private String color;
    private String description;
    private int categoryID;

    public Product() {
    }

    public Product(String productName, double price, int quantity, String color, String description, int categoryID) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
        this.categoryID = categoryID;
    }

    public Product(int productID, String productName, double price, int quantity, String color, String description) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
    }

    public Product(int productID, String productName, double price, int quantity, String color, String description, int categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
        this.categoryID = categoryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @NotEmpty
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @NotNull
    @Min(1000)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull
    @Min(1)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @NotEmpty
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @NotEmpty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategory(int categoryID) {
        this.categoryID = categoryID;
    }
}
