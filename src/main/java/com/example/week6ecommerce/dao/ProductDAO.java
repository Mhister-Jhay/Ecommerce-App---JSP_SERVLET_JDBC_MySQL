package com.example.week6ecommerce.dao;


import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Product;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDAO {
    private Product product;
    private static Queries queries;
    private DBConnection dbConnection;

    public ProductDAO() {
        queries = new Queries();
        dbConnection = new DBConnection();
    }




    public ResultSet getAllProducts(){
        ResultSet resultSet = null;
        try{
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(queries.getGetAllProducts());

        }catch(SQLException e){
            System.out.println("Exception in getting all customers: "+e.getMessage());
        }
        return resultSet;
    }

    public boolean orderProduct(String orderID, int customerID, int productID, int quantity, double amount, Timestamp orderDate) {
        boolean isOrderSuccessful = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getAddToCart());
            preparedStatement.setString(1, orderID);
            preparedStatement.setInt(2, customerID);
            preparedStatement.setInt(3, productID);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, amount);
            preparedStatement.setTimestamp(6, orderDate);
            isOrderSuccessful = true;
        } catch (SQLException e) {
            System.out.println("Exception in login: " + e.getMessage());
        }
        return isOrderSuccessful;
    }
}
