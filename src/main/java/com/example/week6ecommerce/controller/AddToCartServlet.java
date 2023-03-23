package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.CartDAO;
import com.example.week6ecommerce.dao.ProductDAO;
import com.example.week6ecommerce.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "CartServlet", value = "/addToCart")
public class AddToCartServlet extends HttpServlet {
    Product product;
    ProductDAO productDAO;
    CartDAO cartDAO;

    public AddToCartServlet() {
        this.product = new Product();
        this.productDAO = new ProductDAO();
        this.cartDAO = new CartDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customer_id"));
        int productID = (Integer.parseInt(request.getParameter("product_id")));
        int quantity = 1;
        RequestDispatcher requestDispatcher;

        try {
            boolean isAddedToCart = cartDAO.addToCart(customerID,productID,quantity);
            requestDispatcher = request.getRequestDispatcher("index.jsp");
            if(isAddedToCart){
                request.setAttribute("statusCart", "success");
            }else{
                request.setAttribute("statusCart","failed");
            }
            System.out.println(request.getAttribute("statusCart"));
            requestDispatcher.forward(request, response);
        }catch (ServletException | IOException e) {
            System.out.println("Exception in sending all products: "+e.getMessage());
        }
    }
}
