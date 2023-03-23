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


    private boolean createNewProduct(String name, int categoryID, double price, int quantity, String image){
        boolean isProductCreated = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getAddNewProduct());
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, categoryID);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setString(5, image);
            preparedStatement.executeQuery();
            isProductCreated = true;
        }catch (SQLException e) {
            System.out.println("Exception in registering customers: "+e.getMessage());
        }
        if(isProductCreated){
            getAllProducts();
        }
        return isProductCreated;
    }

    private boolean updateProductQuantity(int productID, int quantity){
        boolean isQuantityUpdated = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getUpdateProductQuantity());
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
            isQuantityUpdated = true;
        }catch (SQLException e) {
            System.out.println("Exception in registering customers: "+e.getMessage());
        }
        if(isQuantityUpdated){
            getAllProducts();
        }
        return isQuantityUpdated;
    }
    private boolean updateProductPrice(int productID, double price){
        boolean isQuantityUpdated = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getUpdateProductPrice());
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
            isQuantityUpdated = true;
        }catch (SQLException e) {
            System.out.println("Exception in registering customers: "+e.getMessage());
        }
        if(isQuantityUpdated){
            getAllProducts();
        }
        return isQuantityUpdated;
    }

    private boolean deleteProduct(int productID){
        boolean isProductDeleted = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getDeleteProduct());
            preparedStatement.setInt(1, productID);
            preparedStatement.executeQuery();
            isProductDeleted = true;
        }catch (SQLException e) {
            System.out.println("Exception in registering customers: "+e.getMessage());
        }
        return isProductDeleted;
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

    public ResultSet selectAProduct(int productID){
        ResultSet resultSet = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getSelectProduct());
            preparedStatement.setInt(1,productID);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Exception in login: " + e.getMessage());
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
