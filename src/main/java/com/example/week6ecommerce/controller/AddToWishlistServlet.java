package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.WishlistDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "WishlistServlet", value = "/AddToWishlist")
public class AddToWishlistServlet extends HttpServlet {
    WishlistDAO wishlistDAO;
    ProductServlet productServlet;
    public AddToWishlistServlet() {
        this.wishlistDAO = new WishlistDAO();
        this.productServlet = new ProductServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int customerID = Integer.parseInt(request.getParameter("customer_id"));
        int productID = (Integer.parseInt(request.getParameter("product_id")));
        RequestDispatcher requestDispatcher;

        try {
            wishlistDAO.addToWishList(customerID,productID);
            String prevPage = request.getParameter("prevPage");
            if(prevPage.equals("product.jsp")){
                productServlet.doGet(request,response);
            }else{
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
        }catch (ServletException | IOException e) {
            System.out.println("Exception in adding to wishlist: "+e.getMessage());
        }
    }
}
