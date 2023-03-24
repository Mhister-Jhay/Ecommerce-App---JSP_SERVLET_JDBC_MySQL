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
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int quantity = 0;
        String submit_type = request.getParameter("submit_type");
        boolean isQuantityUpdated = false;
        try{
            if(submit_type != null){
                if(submit_type.equals("minus")){
                    quantity = -1;
                    isQuantityUpdated = cartDAO.updateCartQuantity(quantity,product_id,customer_id);
                }else if(submit_type.equals("plus")){
                    quantity = 1;
                    isQuantityUpdated = cartDAO.updateCartQuantity(quantity,product_id,customer_id);
                }else if (submit_type.equals("delete")){
                    isQuantityUpdated = cartDAO.removeFromCart(product_id,customer_id);
                }
            }
            if(isQuantityUpdated){
                cartServlet.doGet(request, response);
            } else{
                request.setAttribute("status","failed");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Product");
                requestDispatcher.forward(request,response);
            }
        }catch(ServletException | IOException e){
            System.out.println("Exception in Updating Cart Quantity: "+e.getMessage());
        }

    }
}
