package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.AdminDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "AddProductServlet", value = "/AddProduct")
public class AddProductServlet extends HttpServlet {
    AdminDAO adminDAO;
    public AddProductServlet(){
        this.adminDAO = new AdminDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int categoryID = Integer.parseInt(request.getParameter("category_id"));
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String image = request.getParameter("image");
        String prevPage = request.getParameter("prevPage");
        RequestDispatcher requestDispatcher;
        try{
            adminDAO.createNewProduct(name,categoryID,price,quantity,image);
            if(prevPage == null){
                requestDispatcher = request.getRequestDispatcher("addProduct.jsp");
            }else{
                requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
            }

            requestDispatcher.forward(request,response);
        }catch (ServletException | IOException e) {
            System.out.println("Exception in adding new product: "+e.getMessage());
        }
    }

}
