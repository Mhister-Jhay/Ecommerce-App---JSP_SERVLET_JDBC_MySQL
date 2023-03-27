package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.WishlistDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "RemoveFromWishlistServlet", value = "/removeFromWishlist")
public class RemoveFromWishlistServlet extends HttpServlet {
    WishlistDAO wishlistDAO;
    ViewWishlistServlet viewWishlistServlet;
    public RemoveFromWishlistServlet() {
        this.wishlistDAO = new WishlistDAO();
        this.viewWishlistServlet = new ViewWishlistServlet();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int productID = (Integer.parseInt(request.getParameter("product_id")));
        int customerID = Integer.parseInt(request.getParameter("customer_id"));

        try {
            boolean isRemovedFromWishlist = wishlistDAO.removeFromWishlist(productID,customerID);
            if(isRemovedFromWishlist){
                viewWishlistServlet.doGet(request,response);
            }
        }catch (Exception e) {
            System.out.println("Exception in removing from wishlist: "+e.getMessage());
        }
    }
}
