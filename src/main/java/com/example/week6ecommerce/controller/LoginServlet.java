package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.CustomerDAO;
import com.example.week6ecommerce.model.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Customer customer;
    CustomerDAO customerDAO;
    public LoginServlet() {
        this.customer = new Customer();
        this.customerDAO = new CustomerDAO(customer);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customer.setEmail(request.getParameter("email"));
        customer.setPassword(request.getParameter("password"));
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        try{
            ResultSet resultSet = customerDAO.loginCustomer();
            if(resultSet.next()){
                session.setAttribute("customer_id",resultSet.getInt("customer_id"));
                session.setAttribute("firstName", resultSet.getString("first_name"));
                session.setAttribute("logged_in",true);
                request.setAttribute("status","success");
                if(resultSet.getString("email").equals("admin@decagon.dev")){
                    requestDispatcher = request.getRequestDispatcher("AdminHome.jsp");
                }else{
                    requestDispatcher = request.getRequestDispatcher("index.jsp");
                }
            }else{
                request.setAttribute("status","failed");
                requestDispatcher = request.getRequestDispatcher("login.jsp");
            }
            requestDispatcher.forward(request,response);
        }catch(SQLException e){
            System.out.println("Exception in logging in: "+e.getMessage());
        }
    }
}
