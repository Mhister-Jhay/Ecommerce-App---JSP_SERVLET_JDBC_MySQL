package com.example.week6ecommerce.controller;

import com.example.week6ecommerce.dao.CustomerDAO;
import com.example.week6ecommerce.model.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private Customer customer;
    private CustomerDAO customerDAO;
    public RegistrationServlet() {
        this.customer = new Customer();
        this.customerDAO = new CustomerDAO(customer);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        customer.setFirstName(request.getParameter("first_name"));
        customer.setLastName(request.getParameter("last_name"));
        customer.setPhoneNumber(request.getParameter("phone_number"));
        customer.setEmail(request.getParameter("email"));
        customer.setPassword(request.getParameter("password"));
        String password = request.getParameter("re_password");

        RequestDispatcher requestDispatcher;
        try {
            if(!password.equals(customer.getPassword())){
                request.setAttribute("status1","failed");
            }else{

                boolean registeredStatus = customerDAO.getCustomerRegistered();
                if(registeredStatus){
                    request.setAttribute("status","success");
                }else{
                    request.setAttribute("status","failed");
                }
            }
            requestDispatcher = request.getRequestDispatcher("registration.jsp");
            requestDispatcher.forward(request,response);
        }catch(Exception e)
        {
            System.out.println("Exception in Registration: "+e.getMessage());
        }
    }
}
