package controler;

import DAO.IProductDAO;
import DAO.ITypeDAO;
import DAO.ProductDAO;
import DAO.TypeDAO;
import Model.Product;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "userServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    //    private static final long serialVersionUID = 1L;
    private String errors;
    private ProductDAO productDAO;
    private TypeDAO typeDAO;

    public void init() {
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
                default:
                    listProductPagging(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProductFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeID = Integer.parseInt(request.getParameter("typeID"));
        System.out.println("hehe");
        List<Product> listProduct = productDAO.selectProductFilter(typeID);
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int noOfRecord = productDAO.getNoOfRecord();
        int noOfPage = (int) Math.ceil(noOfRecord * 1.08) / recordsPerPage;
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("noOfPage", noOfPage);
        request.setAttribute("currentPage", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/productlist.jsp");
        requestDispatcher.forward(request, response);
    }

    private void listProductPagging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Product> listProduct = productDAO.selectProductPagging((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecord = productDAO.getNoOfRecord();
        int noOfPage = (int) Math.ceil(noOfRecord * 1.08) / recordsPerPage;
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("noOfPage", noOfPage);
        request.setAttribute("currentPage", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/productlist.jsp");
        requestDispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Product> listProduct = productDAO.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/productlist.jsp");
        requestDispatcher.forward(request, response);
    }

    private void inserProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, NumberFormatException {
        String regex = "\\d+";
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = 0;
        if (request.getParameter("price") != "") {
            String input = request.getParameter("price");
            try {
                Integer.parseInt(input);
                price = Double.parseDouble(request.getParameter("price"));
            } catch (NumberFormatException e) {
                errors = "<ul><li>" +
                        "price is a number, please check again" +
                        "</li></ul>";
            }
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
                productDAO.insertProduct(product);
                request.setAttribute("success", "Insert product is success.");
                request.getRequestDispatcher("/product?action=list").forward(request, response);
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productID"));
//        productDAO.deleteProductByID(id);
        request.setAttribute("delete", "you wan't delete");
        RequestDispatcher dispatcher = request.getRequestDispatcher("product?action=users");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = 0;
        if (request.getParameter("price") != "") {
            String input = request.getParameter("price");
            try {
                Integer.parseInt(input);
                price = Double.parseDouble(request.getParameter("price"));
            } catch (NumberFormatException e) {
                price = 0;
            }
        }
        int quaility = 0;
        if (request.getParameter("quaility") != "") {
            quaility = Integer.parseInt(request.getParameter("quaility"));
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
        int id = Integer.parseInt(request.getParameter("productID"));
        Product product = productDAO.selectProductByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/createproduct.jsp");
        request.setAttribute("product", product);
        typeDAO = new TypeDAO();
        //request.setAttribute("listType",typeDAO.selectAllType() );

        //getServletContext().setAttribute("listType",typeDAO.selectAllType() );
        dispatcher.forward(request, response);

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
