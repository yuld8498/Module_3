package com.example.discountcalculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SessionTest", urlPatterns = "/SessionTest")
public class SessionTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("name","Hello Http Session");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.printf("Hello session");
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
