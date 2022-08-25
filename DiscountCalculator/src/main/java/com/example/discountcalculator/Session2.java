package com.example.discountcalculator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Session2", value = "/Session2")
public class Session2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        String name = "";
        HttpSession httpSession =request.getSession();
        Object obj = httpSession.getAttribute("name");
        if (obj!=null){
            name= String.valueOf(obj);
        }
        else {
            response.sendRedirect("/SessionTest");
        }
        printWriter.println("xin chào " + name);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
