package com.example.dictionary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Translation", value = "/translate")
public class Translation  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("hello", "Xin Chào");
        dictionary.put("bye", "Tạm Biệt");
        dictionary.put("book", "Cuốn Sách");
        dictionary.put("computer", "Máy Tính");
        dictionary.put("how", "Thế Nào");
        String search = req.getParameter("txtSearch");
        String searchLowCase = search.toLowerCase();
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");

        String result = dictionary.get(searchLowCase);
        if(result != null){
            writer.println("Word: " + search);
            writer.println("Result: " + result);
        } else {
            writer.println("Not found");
        }

        writer.println("</html>");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("hello Dictionary");
    }
}
