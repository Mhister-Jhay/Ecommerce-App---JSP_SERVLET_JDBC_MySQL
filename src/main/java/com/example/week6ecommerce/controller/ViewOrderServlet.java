package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.OrderDAO;
import com.example.week6ecommerce.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/orders")
public class ViewOrderServlet extends HttpServlet {
    OrderDAO orderDAO;
    public ViewOrderServlet(){
        this.orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        try{
            List<Order> orderList = orderDAO.viewOrder(customer_id);
            request.setAttribute("orderList",orderList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders.jsp");
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e) {
            System.out.println("Exception in viewing order: "+e.getMessage());
        }
    }
}
