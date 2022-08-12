package controler;

import DAO.*;
import Model.Account;
import Model.Country;
import Model.Product;
import Model.User;
import com.mysql.cj.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private String errors;
    private IUserDao userDao;
    private IProductDAO productDAO;
    private ICountryDAO countryDAO;
    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        countryDAO = new CountryDAO();
        userDao = new UserDao();
        if (this.getServletContext().getAttribute("countryList")==null){
            this.getServletContext().setAttribute("countryList", countryDAO.selectAllCountry());
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action ="";
        }
        if (!checkLogin(req,resp)){
            System.out.println("begin");
            resp.sendRedirect("/loginpage.jsp");
        }else {
            switch (action){
                case "login":
                    loginUser(req,resp);
                    break;
                case "create":
                    formRegister(req,resp);
                    break;
                case "changepassword":
                    formChangepassword(req,resp);
                    break;
                case "list":
                    System.out.println("hello");
                    break;
                default:
                    resp.sendRedirect("/usermanager.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action ="";
        }
        switch (action){
            case "login":
                loginUser(req,resp);
                break;
            case "create":
                insertUser(req,resp);
                break;
            case "changepassword":
                changePassword(req,resp);
                break;
            case "logout":
                logOut(req,resp);
                break;
            default:
        }
    }
    private void formChangepassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password =null;
        String userName =null;
        for (Cookie cookie : req.getCookies()){
            if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
            if (cookie.getName().equals("userName")){
                userName = cookie.getValue();
            }
        }
        req.setAttribute("password", password);
        req.setAttribute("userName", userName);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/usermanager.jsp");
        requestDispatcher.forward(req,resp);
    }
    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        String password =null;
        String userName =null;
        for (Cookie cookie : req.getCookies()){
            if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
            if (cookie.getName().equals("userName")){
                userName = cookie.getValue();
            }
        }
        String inputPW = req.getParameter("password");
        String newPW =  req.getParameter("newpassword");
        String newPWreaplay =  req.getParameter("newpasswordreaplay");
        if (!(password.equals(inputPW))){
            errors="<ul><li>Current password is incorrect </li></ul>";
            req.setAttribute("errorspw", errors);
            req.setAttribute("password", password);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/usermanager.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }
        if (!(newPWreaplay.equals(newPW))){
            String errors2="<ul><li>Re-enter password is not correct </li></ul>";
            req.setAttribute("errorspw2", errors2);
            req.setAttribute("password", password);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/usermanager.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }
        User user = userDao.login(userName,password);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if (!constraintViolations.isEmpty()){
            req.setAttribute("errors", getErrorFromContraint(constraintViolations));
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/view/usermanager.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            try {
                userDao.updateUserPassword(userName,newPW);
                req.setAttribute("success", "Insert product is success.");
                req.getRequestDispatcher("/users?action=login");
            } catch (SQLException e) {
                errors="<ul><li>Re-enter password is not correct </li></ul>";
                req.setAttribute("errorspw", errors);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/view/usermanager.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("userName");
        String pw = req.getParameter("password");
        if (userDao.login(username,pw)!=null){
            Cookie cookie = new Cookie("userName",username);
            Cookie cookie2 = new Cookie("password",pw);
            resp.addCookie(cookie);
            resp.addCookie(cookie2);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/usermanager.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            resp.sendRedirect("loginpage.jsp");
        }
    }
    private void formRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }
    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        int age = 0;
        if (request.getParameter("age")!=""){
            age = Integer.parseInt(request.getParameter("age"));
        }
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int country = Integer.parseInt(request.getParameter("country"));
        User user = new User(userName,password,fullName,age,email,phone,country);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if (!constraintViolations.isEmpty()){
            request.setAttribute("errors", getErrorFromContraint(constraintViolations));
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
            requestDispatcher.forward(request, response);
        }else{
            userDao.insertUser(user);
            request.setAttribute("success", "Insert product is success.");
            request.getRequestDispatcher("/users?action=login");
        }
    }
    public void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Cookie cookie : req.getCookies()){
            if (cookie.getName().equals("userName")&&cookie.getValue().equals("password")){
                cookie.setMaxAge(0);
                return;
            }
        }
        req.getRequestDispatcher("/users?action=login");
    }
    private HashMap<String, List<String>> getErrorFromContraint(Set<ConstraintViolation<User>> constraintViolations) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for(ConstraintViolation<User> c : constraintViolations){
            if(hashMap.keySet().contains(c.getPropertyPath().toString())){
                hashMap.get(c.getPropertyPath().toString()).add(c.getMessage());
            }else{
                List<String> listMessages = new ArrayList<>();
                listMessages.add(c.getMessage());
                hashMap.put(c.getPropertyPath().toString(), listMessages);
            }
        }
        return hashMap;
    }
    private boolean checkLogin(HttpServletRequest req, HttpServletResponse resp){
        String username=null;
        String password=null;
        for (Cookie cookie : req.getCookies()){
            if (cookie.getName().equals("userName")){
                username = cookie.getValue();
            }
            if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
        }
        return (userDao.login(username,password)!=null);
    }
}
