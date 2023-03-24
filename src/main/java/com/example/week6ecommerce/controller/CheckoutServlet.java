package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.OrderDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CheckoutServlet", value = "/Checkout")
public class CheckoutServlet extends HttpServlet {
    OrderDAO orderDAO;
    ViewOrderServlet viewOrderServlet;

    public  CheckoutServlet(){
        this.orderDAO = new OrderDAO();
        this.viewOrderServlet = new ViewOrderServlet();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        boolean isOrderAdded = false;
        try{
            isOrderAdded = orderDAO.checkOut(customer_id);
            if(isOrderAdded){
                viewOrderServlet.doGet(request,response);
            }else{
                viewOrderServlet.doGet(request,response);
            }
        }catch(Exception e){
            System.out.println("Exception in checking out: "+e.getMessage());
        }
    }
}
