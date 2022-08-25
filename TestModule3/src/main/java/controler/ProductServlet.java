package controler;

import Model.Product;
import dao.CategoryDao;
import dao.ICategoryDao;
import dao.IProductDao;
import dao.ProductDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@WebServlet(name = "userServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private String errors;
    private IProductDao productDao;
    private ICategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        productDao = new ProductDao();
        categoryDao = new CategoryDao();
        if (this.getServletContext().getAttribute("listCategory")==null){
this.getServletContext().setAttribute("listCategory", categoryDao.selectAllCategory());
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
            default:
                showListProduct(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "edit":
                break;
            case "update":
                updateProduct(req,resp);
                break;
            case "create":
                inserProduct(req,resp);
                break;
            case "filter":
                listProductFilter(req, resp);
                break;
            default:
                showListProduct(req,resp);
        }
    }

    public void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listProduct = productDao.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/productlist.jsp");
        requestDispatcher.forward(request,response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String action = "edit'";
        request.setAttribute("action", action);
        int id = Integer.parseInt(request.getParameter("productID"));
        Product product = productDao.selectProductByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
        request.setAttribute("product", product);
        categoryDao = new CategoryDao();
        //request.setAttribute("listType",typeDAO.selectAllType() );

        //getServletContext().setAttribute("listType",typeDAO.selectAllType() );
        dispatcher.forward(request, response);

    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regex = "\\d+";
        String regexF = "^[0-9]?([0-9]*[.])?[0-9]+";
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String Color = request.getParameter("color");
        double price = 0;
        if (request.getParameter("price").matches(regexF)) {
            price = Double.parseDouble(request.getParameter("price"));
        } else {
            errors = "<ul><li>" +
                    "price is a number, please check again" +
                    "</li></ul>";
        }
        int quantity = 0;
        if ((request.getParameter("quantity").matches(regex))) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } else {
            errors = "<ul><li>" +
                    "Quantity is a number, please check again" +
                    "</li></ul>";
        }
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Product product = new Product(productID,productName,price,quantity,  Color,productDescription, categoryID);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", getErrorFromContraint(constraintViolations));
            request.setAttribute("product", product);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
            requestDispatcher.forward(request, response);
        } else {
            productDao.updateProductByID(product);
            request.setAttribute("success", "Insert product is success.");
            request.getRequestDispatcher("/product?action=list.jsp").forward(request, response);
        }
    }
    private void inserProduct(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException, NumberFormatException {
        String regex = "\\d+";
        String regexF = "^[0-9]?([0-9]*[.])?[0-9]+";
//        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String Color = request.getParameter("color");
        double price = 0;
        if (request.getParameter("price").matches(regexF)) {
            price = Double.parseDouble(request.getParameter("price"));
        } else {
            errors = "<ul><li>" +
                    "price is a number, please check again" +
                    "</li></ul>";
        }
        int quantity = 0;
        if ((request.getParameter("quantity").matches(regex))) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } else {
            errors = "<ul><li>" +
                    "Quantity is a number, please check again" +
                    "</li></ul>";
        }
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Product product = new Product(productName,price,quantity, Color, productDescription, categoryID);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", getErrorFromContraint(constraintViolations));
            request.setAttribute("product", product);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
            requestDispatcher.forward(request, response);
        } else {
                productDao.insertProduct(product);
                request.setAttribute("success", "Insert product is success.");
                request.getRequestDispatcher("/product?action=list").forward(request, response);
            }
        }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productID"));
        productDao.deleteProductByID(id);
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
    private void listProductFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        List<Product> listProduct = productDao.selectProductFilter(categoryID);
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("currentPage", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/productlist.jsp");
        requestDispatcher.forward(request, response);
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
}
