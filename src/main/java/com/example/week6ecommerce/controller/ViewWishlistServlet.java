package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.WishlistDAO;
import com.example.week6ecommerce.model.Order;
import com.example.week6ecommerce.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewWishlistServlet", value = "/wishlist")
public class ViewWishlistServlet extends HttpServlet {
    WishlistDAO wishlistDAO;
    public ViewWishlistServlet() {
        this.wishlistDAO = new WishlistDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        try{
            List<Product> wishList = wishlistDAO.viewWishlist(customer_id);
            request.setAttribute("wishList",wishList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("wishlist.jsp");
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e) {
            System.out.println("Exception in viewing wishlist: "+e.getMessage());
        }
    }

}
