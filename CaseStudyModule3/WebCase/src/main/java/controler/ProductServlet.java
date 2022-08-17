package controler;

import DAO.*;
import Model.Order;
import Model.Product;
import Model.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "userServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    List<Order> orderList = new ArrayList<>();
    int recordsPerPage = 5;
    //    private static final long serialVersionUID = 1L;
    private String errors;
    private ProductDAO productDAO;
    private TypeDAO typeDAO;
    private IUserDao userDao;
    private IOrderDao orderDao;

    public void init() {
        orderDao = new OrderDao();
        userDao = new UserDao();
        productDAO = new ProductDAO();
        typeDAO = new TypeDAO();
        if (this.getServletContext().getAttribute("listType") == null) {
            this.getServletContext().setAttribute("listType", typeDAO.selectAllType());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "update":
                try {
                    showEditForm(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "filter":
                listProductFilter(req, resp);
                break;
            case "order":
                orderItems(req, resp);
                break;
            default:
                listProductPagging(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    inserProduct(req, resp);
                    break;
                case "update":
                    updateProduct(req, resp);
                    break;
                case "filter":
                    listProductFilter(req, resp);
                    break;
                case "order":
                    break;
                default:
                    listProductPagging(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void listProductFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeID = 1;
        if (request.getParameter("typeID") != null) {
            typeID = Integer.parseInt(request.getParameter("typeID"));
        }
        if (typeID == -1) {
            listProductPagging(request, response);
        } else {
            System.out.println("hehe");
            List<Product> listProduct = productDAO.selectProductFilter(typeID);
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int noOfRecord = productDAO.getNoOfRecord();
            int noOfPage = (int) Math.ceil((noOfRecord * 1.08) / recordsPerPage);
            String userName = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userName")) {
                    userName = cookie.getValue();
                }
            }
            request.setAttribute("typeUser", userDao.typeUser(userName));
            request.setAttribute("logincheck", userName);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("noOfPage", noOfPage);
            request.setAttribute("currentPage", page);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/productlist.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    //order details, check user or not user
    private void orderItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = null;
        String password = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equalsIgnoreCase("userName")) {
                userName = cookie.getValue();
            }
            if (cookie.getName().equalsIgnoreCase("password")) {
                password = cookie.getValue();
            }
        }
        User user = userDao.login(userName, password);
        if (user != null) {
            String type = userDao.typeUser(userName);
            int id =(Integer.parseInt(request.getParameter("productID")));
            Order order = new Order(userName, id, 1, user.getEmail()
                    , user.getPhoneNumber(), "", user.getAddress());
            System.out.println(Integer.parseInt(request.getParameter("productID")));
            orderDao.InsertOrder(order);
            int noOfpage = (int) Math.ceil((Integer.parseInt(request.getParameter("nooflist")) * 1.08) / recordsPerPage);
            String path = "/product?page=" + noOfpage;
            response.sendRedirect(path);
        } else {
            int id =(Integer.parseInt(request.getParameter("productID")));
            int count =0;
            Order order = new Order();
            order.setProductID(id);
            order.setProductQuaility(1);
            for (Order orderInList : orderList){
                if (orderInList.getProductID()==order.getProductID()){
                    int number = orderInList.getProductQuaility();
                    orderInList.setProductQuaility(number+1);
                    count++;
                }
            }
            if (count==0){
                orderList.add(order);
            }
            int noOfpage = (int) Math.ceil((Integer.parseInt(request.getParameter("nooflist")) * 1.08) / recordsPerPage);
            String path = "/product?page=" + noOfpage;
            request.getRequestDispatcher(path);
        }
    }

    private void listProductPagging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regex = "\\d+";
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            if (request.getParameter("page").matches(regex)) {
                page = Integer.parseInt(request.getParameter("page"));
            }
        }
        if (page > (productDAO.getNoOfRecord() / 5) + 1) {
            response.sendRedirect("/product?action=list");
        } else {
            List<Product> listProduct = productDAO.selectProductPagging((page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecord = productDAO.getNoOfRecord();
            int noOfPage = (int) Math.ceil((noOfRecord * 1.0) / recordsPerPage);
            String userName = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userName")) {
                    userName = cookie.getValue();
                }
            }
            request.setAttribute("typeUser", userDao.typeUser(userName));
            request.setAttribute("logincheck", userName);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("noOfPage", noOfPage);
            request.setAttribute("currentPage", page);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/productlist.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Product> listProduct = productDAO.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/productlist.jsp");
        requestDispatcher.forward(request, response);
    }

    private void inserProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, NumberFormatException {
        String regex = "\\d+";
        String regexF = "^[0-9]?([0-9]*[.])?[0-9]+";
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = 0;
        if (request.getParameter("price").matches(regexF)) {
            price = Double.parseDouble(request.getParameter("price"));
        } else {
            errors = "<ul><li>" +
                    "price is a number, please check again" +
                    "</li></ul>";
        }
        int quaility = 0;
        if ((request.getParameter("quaility").matches(regex))) {
            quaility = Integer.parseInt(request.getParameter("quaility"));
        } else {
            errors = "<ul><li>" +
                    "Quantity is a number, please check again" +
                    "</li></ul>";
        }
        int typeID = Integer.parseInt(request.getParameter("typeID"));
        Product product = new Product(productName, productDescription, price, quaility, typeID);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", getErrorFromContraint(constraintViolations));
            request.setAttribute("product", product);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
            requestDispatcher.forward(request, response);
        } else {
            if (productDAO.selectProductByPrice(productName, price, typeID)) {
                String errorsprice = "<ul>" +
                        "<li>Product already exist</li>" +
                        "<li> 1 Product can't have the same name,price and type</li>" +
                        "</ul>";
                request.setAttribute("errorsprice", errorsprice);
                request.setAttribute("product", product);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
                requestDispatcher.forward(request, response);
            } else {
                int noOfRecord = productDAO.getNoOfRecord();
                int noOfPage = (int) Math.ceil((noOfRecord * 1.08) / 5);
                String path;
                if (noOfPage == 0) {
                    path = "/product?page=1";
                } else {
                    path = "/product?page=" + noOfPage;
                }
                productDAO.insertProduct(product);
                //request.setAttribute("success", "Insert product is success.");
                response.sendRedirect(path);
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productID"));
        productDAO.deleteProductByID(id);
        request.setAttribute("delete", "you wan't delete");
        RequestDispatcher dispatcher = request.getRequestDispatcher("product?action=users");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("action", action);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regex = "\\d+";
        String regexF = "^[0-9]?([0-9]*[.])?[0-9]+";
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = 0;
        if (request.getParameter("price").matches(regexF)) {
            price = Double.parseDouble(request.getParameter("price"));
        } else {
            errors = "<ul><li>" +
                    "price is a number, please check again" +
                    "</li></ul>";
        }
        int quaility = 0;
        if ((request.getParameter("quaility").matches(regex))) {
            quaility = Integer.parseInt(request.getParameter("quaility"));
        } else {
            errors = "<ul><li>" +
                    "Quantity is a number, please check again" +
                    "</li></ul>";
        }
        int typeID = Integer.parseInt(request.getParameter("typeID"));
        Product product = new Product(productID, productName, productDescription, price, quaility, typeID);
//        System.out.println(productID + " " + productName + " " + productDescription + " " + price + " " + quaility);
//        productDAO.updateProductByID(product);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product?action=users");
//        requestDispatcher.forward(request, response);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", getErrorFromContraint(constraintViolations));
            request.setAttribute("product", product);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
            requestDispatcher.forward(request, response);
        } else {
            productDAO.updateProductByID(product);
            request.setAttribute("success", "Insert product is success.");
            request.getRequestDispatcher("/product?action=list").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String regex = "\\d+";
        String action = "edit'";
        request.setAttribute("action", action);
        int id = 0;
        if (request.getParameter("productID").matches(regex)) {
            id = Integer.parseInt(request.getParameter("productID"));
            Product product = productDAO.selectProductByID(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
            request.setAttribute("product", product);
            typeDAO = new TypeDAO();
            //request.setAttribute("listType",typeDAO.selectAllType() );

            //getServletContext().setAttribute("listType",typeDAO.selectAllType() );
            dispatcher.forward(request, response);
        } else {
            errors = "<ul><li>" +
                    "price is a number, please check again" +
                    "</li></ul>";
            response.sendRedirect("/product?action=list");
        }

    }

    private HashMap<String, List<String>> getErrorFromContraint(Set<ConstraintViolation<Product>> constraintViolations) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (ConstraintViolation<Product> c : constraintViolations) {
            if (hashMap.keySet().contains(c.getPropertyPath().toString())) {
                hashMap.get(c.getPropertyPath().toString()).add(c.getMessage());
            } else {
                List<String> listMessages = new ArrayList<>();
                listMessages.add(c.getMessage());
                hashMap.put(c.getPropertyPath().toString(), listMessages);
            }
        }
        return hashMap;
    }

    private boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) {
        String username = null;
        String password = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("userName")) {
                username = cookie.getValue();
            }
            if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
        return (userDao.login(username, password) != null);
    }
}
