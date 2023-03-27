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

@WebServlet(name = "UpdateProductServlet", value = "/UpdateProduct")
public class UpdateProductServlet extends HttpServlet {
    AdminDAO adminDAO;
    AdminProductServlet adminProductServlet;
    ProductDAO productDAO;

    public UpdateProductServlet(){
        this.adminDAO =  new AdminDAO();
        this.adminProductServlet = new AdminProductServlet();
        this.productDAO = new ProductDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        double price = Double.parseDouble(request.getParameter("price"));
        String prevPage = request.getParameter("prevPage");
        try{
            if(price == 0.0){
               adminDAO.updateProductQuantity(product_id,quantity);
            }else if(quantity == 0){
               adminDAO.updateProductPrice(product_id,price);
            }
            RequestDispatcher requestDispatcher;
            ResultSet resultSet;
            List<Product> productList = new ArrayList<>();
            if(prevPage == null){
                resultSet = productDAO.getAllProducts();
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
                resultSet = adminDAO.selectAProduct(product_id);
                while(resultSet.next()){
                    productList.add(new Product(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("category_id"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("image")));
                }
                request.setAttribute("product",productList);
                requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
            }

            requestDispatcher.forward(request,response);
        }catch(SQLException|IOException | ServletException e){
            System.out.println("Error in updating product: "+e.getMessage());
        }

    }
}
