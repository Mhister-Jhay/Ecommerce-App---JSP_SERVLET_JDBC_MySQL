package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.CartDAO;
import com.example.week6ecommerce.model.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    CartDAO cartDAO;

    public CartServlet(){
        this.cartDAO = new CartDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        try{
        List<Cart> cartList = cartDAO.viewCart(customer_id);
        request.setAttribute("cartList",cartList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
        }catch (ServletException | IOException e) {
            System.out.println("Exception in viewing cart: "+e.getMessage());
        }
    }
}
