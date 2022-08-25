package com.example.discountcalculator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Discount", value = "/Discount")
public class Discount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        System.out.println("welcome to my store discount");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        float price = Float.parseFloat(request.getParameter("list-price"));
        float discount = Float.parseFloat(request.getParameter("discount-percent"));
        int count = Integer.parseInt(request.getParameter("number-product"));
        float result = (float) (price*discount*0.01);
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h1>");
        printWriter.printf("%s%.0f%s","Your discount ",result," vnd");
        printWriter.println("</h1>");
    }
}
