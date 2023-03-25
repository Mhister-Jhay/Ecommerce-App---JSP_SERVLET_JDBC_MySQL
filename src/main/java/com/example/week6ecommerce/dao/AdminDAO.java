package com.example.week6ecommerce.dao;

import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@Data
public class AdminDAO {
    private ProductDAO productDAO;
    private Queries queries;

    private DBConnection dbConnection;
    public AdminDAO() {
        this.dbConnection = new DBConnection();
        this.queries = new Queries();

    }

    public boolean createNewProduct(String name, int categoryID, double price, int quantity, String image){
        boolean isProductCreated = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getAddNewProduct());
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, categoryID);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setString(5, image);
            preparedStatement.executeUpdate();
            isProductCreated = true;
        }catch (SQLException e) {
            System.out.println("Exception in creating new product: "+e.getMessage());
        }
        return isProductCreated;
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
    public boolean updateProductQuantity(int productID, int quantity){
        boolean isQuantityUpdated = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getUpdateProductQuantity());
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
            isQuantityUpdated = true;
        }catch (SQLException e) {
            System.out.println("Exception in updating product quantity: "+e.getMessage());
        }
        return isQuantityUpdated;
    }
    public boolean updateProductPrice(int productID, double price){
        boolean isQuantityUpdated = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getUpdateProductPrice());
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
            isQuantityUpdated = true;
        }catch (SQLException e) {
            System.out.println("Exception in updating product price: "+e.getMessage());
        }
        return isQuantityUpdated;
    }

    public boolean deleteProduct(int productID){
        boolean isProductDeleted = false;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getDeleteProduct());
            preparedStatement.setInt(1, productID);
            preparedStatement.executeUpdate();
            isProductDeleted = true;
        }catch (SQLException e) {
            System.out.println("Exception in deleting product: "+e.getMessage());
        }
        return isProductDeleted;
    }

}
