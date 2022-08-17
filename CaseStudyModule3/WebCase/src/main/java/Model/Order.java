package Model;

public class Order {
    private int ID;
    private String userName;
    private int productID;
    private int productQuaility;
    private String email;
    private String phoneNumber;
    private String orther_option;

    private int cityID;


    public Order() {
    }

    public Order(String userName, int productID, int productQuaility, String email, String phoneNumber, String orther_option, int cityID) {
        this.userName = userName;
        this.productID = productID;
        this.productQuaility = productQuaility;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orther_option = orther_option;
        this.cityID = cityID;
    }

    public Order(int ID, String userName, int productName, int productQuaility, String email, String phoneNumber, String orther_option, int cityID) {
        this.ID = ID;
        this.userName = userName;
        this.productID = productID;
        this.productQuaility = productQuaility;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orther_option = orther_option;
        this.cityID = cityID;
    }

    public Order(String userName, String productName, int productQuaility) {
        this.userName = userName;
        this.productID = productID;
        this.productQuaility = productQuaility;
    }

    public int getCity() {
        return cityID;
    }

    public void setCity(int cityID) {
        this.cityID = cityID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrther_option() {
        return orther_option;
    }

    public void setOrther_option(String orther_option) {
        this.orther_option = orther_option;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productName) {
        this.productID = productID;
    }

    public int getProductQuaility() {
        return productQuaility;
    }

    public void setProductQuaility(int productQuaility) {
        this.productQuaility = productQuaility;
    }

}
