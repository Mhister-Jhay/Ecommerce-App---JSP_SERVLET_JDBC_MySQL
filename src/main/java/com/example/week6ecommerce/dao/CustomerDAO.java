package com.example.week6ecommerce.dao;


import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Customer;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Data
public class CustomerDAO {
    private Customer customer;
    private Queries queries;

    private DBConnection dbConnection;

    public CustomerDAO(Customer customer) {
        this.customer = customer;
        this.queries = new Queries();
        this.dbConnection = new DBConnection();
    }


    public boolean getCustomerRegistered(){
        List<Customer> customerList = getAllCustomers();
        boolean isRegistered;
        boolean newCustomer = false;
        if(customerList.size()==0){
            newCustomer = true;
        }else {
            for (Customer value : customerList) {
                newCustomer = !customer.getEmail().equals(value.getEmail())
                        || !customer.getPhoneNumber().equals(value.getPhoneNumber());
            }
        }

        if (newCustomer) {
            try{
                Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getRegisterCustomer());
                preparedStatement.setString(1, customer.getFirstName());
                preparedStatement.setString(2, customer.getLastName());
                preparedStatement.setString(3, customer.getPhoneNumber());
                preparedStatement.setString(4, customer.getEmail());
                preparedStatement.setString(5, customer.getPassword());
                int rowCount = preparedStatement.executeUpdate();

                customerList.add(new Customer(customer.getFirstName(),
                        customer.getLastName(), customer.getPhoneNumber(),
                        customer.getEmail(), customer.getPassword()));
                isRegistered = rowCount > 0;

                return isRegistered;
            }catch (SQLException e) {
                System.out.println("Exception in registering customers: "+e.getMessage());

            }
        }

        return false;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customerList = new ArrayList<>();
        try{
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queries.getGetAllUsers());
            while(resultSet.next()){
                customerList.add(new Customer(resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
        }catch(SQLException e){
            System.out.println("Exception in getting all customers: "+e.getMessage());
        }
        return customerList;
    }

    public ResultSet loginCustomer() {
        ResultSet resultSet = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getLoginCustomer());
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Exception in login: " + e.getMessage());
        }
        return resultSet;
    }

}
