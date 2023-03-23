package com.example.week6ecommerce.controller;


import com.example.week6ecommerce.dao.ProductDAO;
import com.example.week6ecommerce.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/Product")
public class ProductServlet extends HttpServlet {
    Product product;
    ProductDAO productDAO;
    public ProductServlet() {
        this.product = new Product();
        this.productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");

            dispatcher.forward(request, response);
        }catch (ServletException | IOException | SQLException e) {
            System.out.println("Exception in sending all products: "+e.getMessage());
        }
    }
}
