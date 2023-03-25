package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.AdminDAO;
import com.example.week6ecommerce.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchProductServlet", value = "/SearchProduct")
public class SearchProductServlet extends HttpServlet {
    AdminDAO adminDAO;
    public  SearchProductServlet(){
        this.adminDAO = new AdminDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        List<Product> product = new ArrayList<>();
        try{
            ResultSet resultSet = adminDAO.selectAProduct(product_id);
            while(resultSet.next()){
                product.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("category_id"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("image")));
            }
            request.setAttribute("product",product);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
            requestDispatcher.forward(request,response);
        }catch(SQLException e){
            System.out.println("Exception in searching for product:"+e.getMessage());
        }

    }

}
