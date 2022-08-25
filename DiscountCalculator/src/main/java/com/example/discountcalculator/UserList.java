package com.example.discountcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserList", urlPatterns = "/UserList")
public class UserList  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
//        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("UserName");
        String pw = req.getParameter("password");
        RequestDispatcher requestDispatcher = null;
        if (username.equals("admin")&&pw.equals("admin")){
            List<User> list = new ArrayList<>();
            list.add(new User("Mai Văn Hoàn","1983/08/20","Hà Nội","https://i.pravatar.cc/150?img=3"));
            list.add(new User("Nguyễn Văn Nam","1983/08/21","Hà Nội","https://i.pravatar.cc/150?img=4"));
            list.add(new User("Nguyễn Thái Hòa","1983/08/22","Hà Nội","https://i.pravatar.cc/150?img=5"));
            list.add(new User("Trần Đăng Khoa","1983/08/17","Hà Nội","https://i.pravatar.cc/150?img=6"));
            list.add(new User("Trần Đình Thi","1983/08/16","Hà Nội","https://i.pravatar.cc/150?img=7"));

            req.setAttribute("listUser", list);
            requestDispatcher= req.getRequestDispatcher("/ShowUser.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            req.setAttribute("error", "username or password is wrong");
            requestDispatcher = req.getRequestDispatcher("/Login.jsp");
            req.setAttribute("UserName", username);
            req.setAttribute("password", pw);
            requestDispatcher.forward(req, resp);
        }
    }
}
