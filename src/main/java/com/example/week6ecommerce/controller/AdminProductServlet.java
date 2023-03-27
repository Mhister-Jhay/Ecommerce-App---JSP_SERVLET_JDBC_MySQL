package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.ProductDAO;
import com.example.week6ecommerce.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminProductServlet", value = "/AdminProduct")
public class AdminProductServlet extends HttpServlet {
    Product product;
    ProductDAO productDAO;
    public AdminProductServlet(){
        this.product = new Product();
        this.productDAO = new ProductDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultSet resultSet = productDAO.getAllProducts();
        List<Product> productList = new ArrayList<>();
        try{
            while(resultSet.next()){
                productList.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("category_id"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("image")
                ));
            }
            request.setAttribute("productList",productList);
            RequestDispatcher requestDispatcher = null;
            String prevPage = request.getParameter("prevPage");
            switch (prevPage) {
                case "updateProduct.jsp":
                    requestDispatcher = request.getRequestDispatcher("updateProduct.jsp");
                    break;
                case "deleteProduct.jsp":
                    requestDispatcher = request.getRequestDispatcher("deleteProduct.jsp");
                    break;
                case "AdminProduct.jsp":
                    requestDispatcher = request.getRequestDispatcher("AdminProduct.jsp");
                    break;
            }
            assert requestDispatcher != null;
            requestDispatcher.forward(request, response);
        }catch (ServletException | IOException | SQLException e) {
            System.out.println("Exception in viewing all products: "+e.getMessage());
        }
    }

}
