package com.example.week6ecommerce.dao;

import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Product;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Data
public class AdminDAO {
    private ProductDAO productDAO;
    private Queries queries;

    private DBConnection dbConnection;
    public AdminDAO() {
        this.productDAO =new ProductDAO();
        this.queries = new Queries();
        this.dbConnection = getDbConnection();
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
            System.out.println("Exception in creating new product: "+e.getMessage());
        }
        if(isProductCreated){
            productDAO.getAllProducts();
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
            System.out.println("Exception in updating product quantity: "+e.getMessage());
        }
        if(isQuantityUpdated){
            productDAO.getAllProducts();
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
            System.out.println("Exception in updating product price: "+e.getMessage());
        }
        if(isQuantityUpdated){
            productDAO.getAllProducts();
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
            System.out.println("Exception in deleting product: "+e.getMessage());
        }
        return isProductDeleted;
    }

}
