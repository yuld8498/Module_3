package com.codygym.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "TimeServer", urlPatterns = "/TimeServer")
public class TimeServer extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Created servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        Date today = new Date();
        printWriter.println("<h1>" + today+"</h1>");
        printWriter.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
