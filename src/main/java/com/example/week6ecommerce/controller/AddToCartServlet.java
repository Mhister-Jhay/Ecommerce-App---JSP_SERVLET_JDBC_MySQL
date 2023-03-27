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
    ProductServlet productServlet;

    public AddToCartServlet() {
        this.product = new Product();
        this.productDAO = new ProductDAO();
        this.cartDAO = new CartDAO();
        this.productServlet = new ProductServlet();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerID = Integer.parseInt(request.getParameter("customer_id"));
        int productID = (Integer.parseInt(request.getParameter("product_id")));
        int quantity = 1;
        RequestDispatcher requestDispatcher;

        try {
            cartDAO.addToCart(customerID,productID,quantity);
            String prevPage = request.getParameter("prevPage");
            if(prevPage.equals("product.jsp")){
                productServlet.doGet(request,response);
            }else{
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
        }catch (ServletException | IOException e) {
            System.out.println("Exception in adding to cart: "+e.getMessage());
        }
    }
}
