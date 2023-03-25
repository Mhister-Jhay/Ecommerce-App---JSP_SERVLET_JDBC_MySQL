package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.AdminDAO;
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

@WebServlet(name = "DeleteProductServlet", value = "/DeleteProduct")
public class DeleteProductServlet extends HttpServlet {
    AdminDAO adminDAO;
    ProductDAO productDAO;
    public DeleteProductServlet(){
        this.adminDAO = new AdminDAO();
        this.productDAO = new ProductDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        String prevPage = request.getParameter("prevPage");
        try{
            boolean isDeleted = adminDAO.deleteProduct(product_id);
            RequestDispatcher requestDispatcher = null;
            if(prevPage == null){
                    ResultSet resultSet = productDAO.getAllProducts();
                    List<Product> productList = new ArrayList<>();
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
                requestDispatcher = request.getRequestDispatcher("updateProduct.jsp");
            }else{
                requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
            }

            requestDispatcher.forward(request,response);
        }catch(SQLException|ServletException|IOException e){
            System.out.println("Exception in deleting product: "+e.getMessage());
        }
    }
}
