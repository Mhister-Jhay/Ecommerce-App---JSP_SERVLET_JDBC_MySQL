package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.CartDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateCartQuantityServlet", value = "/updateCartQuantity")
public class UpdateCartQuantityServlet extends HttpServlet {

    CartDAO cartDAO;
    CartServlet cartServlet;

    public UpdateCartQuantityServlet() {
        this.cartDAO = new CartDAO();
        this.cartServlet = new CartServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("product_id parameter value: " + request.getParameter("product_id"));
        System.out.println("customer_id parameter value: " + request.getParameter("customer_id"));

        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int quantity = 0;
        String submit_type = request.getParameter("submit_type");
        System.out.println(submit_type);
        if(submit_type != null){
            if(submit_type.equals("minus")){
                quantity = -1;
            }else{
                quantity = 1;
            }
        }
        boolean isQuantityUpdated = cartDAO.updateCartQuantity(quantity,product_id,customer_id);
        if(isQuantityUpdated){
            cartServlet.doPost(request, response);
        }
    }
}
