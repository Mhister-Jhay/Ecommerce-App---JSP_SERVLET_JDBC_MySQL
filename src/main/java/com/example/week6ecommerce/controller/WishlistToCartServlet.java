package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.CartDAO;
import com.example.week6ecommerce.dao.ProductDAO;
import com.example.week6ecommerce.dao.WishlistDAO;
import com.example.week6ecommerce.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "WishlistToCartServlet", value = "/WishlistToCart")
public class WishlistToCartServlet extends HttpServlet {
    Product product;
    ProductDAO productDAO;
    CartDAO cartDAO;
    RemoveFromWishlistServlet removeFromWishlistServlet;
    public WishlistToCartServlet(){
        this.product = new Product();
        this.productDAO = new ProductDAO();
        this.cartDAO = new CartDAO();
        this.removeFromWishlistServlet = new RemoveFromWishlistServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customer_id"));
        int productID = (Integer.parseInt(request.getParameter("product_id")));
        int quantity = 1;

        try {
            boolean isAddedToCart = cartDAO.addToCart(customerID,productID,quantity);
            if(isAddedToCart){
                removeFromWishlistServlet.doPost(request,response);
                request.setAttribute("statusCart", "success");

            }else{
                request.setAttribute("statusCart","failed");
            }
        }catch (Exception e) {
            System.out.println("Exception in adding to cart from wishlist: "+e.getMessage());
        }
    }
}
