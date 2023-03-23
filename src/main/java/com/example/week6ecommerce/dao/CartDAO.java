package com.example.week6ecommerce.dao;

import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Cart;
import jakarta.servlet.RequestDispatcher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private DBConnection dbConnection;
    private Queries queries;

    public CartDAO() {
        this.dbConnection = new DBConnection();
        this.queries = new Queries();
    }
    private int getCartProductQuantity(int product_id,int customer_id){
        ResultSet resultSet ;
        int oldQuantity = 0;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getSelectCartProductQuantity());
            preparedStatement.setInt(1,product_id );
            preparedStatement.setInt(2,customer_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                oldQuantity = resultSet.getInt("quantity");
            }
        }catch(SQLException e){
            System.out.println("Exception in getting cart product quantity: "+e.getMessage());
        }
        return oldQuantity;
    }

    public boolean addToCart(int customerID, int productID, int quantity){
        boolean isAddedToCart = false;
        int newQuantity = 0;
        try {
            int oldQuantity =getCartProductQuantity(productID,customerID);
            if(oldQuantity == 0){
                Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getAddToCart());
                preparedStatement.setInt(1,customerID);
                preparedStatement.setInt(2, productID);
                preparedStatement.setInt(3,quantity);
                preparedStatement.executeUpdate();
                isAddedToCart = true;
            } else{
                newQuantity = oldQuantity + quantity;
                Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getUpdateCartProductQuantity());
                preparedStatement.setInt(1, newQuantity);
                preparedStatement.setInt(2,productID);
                preparedStatement.setInt(3, customerID);
                preparedStatement.executeUpdate();
                isAddedToCart = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception in adding to cart: " + e.getMessage());
        }

        return isAddedToCart;
    }
    public List<Cart> viewCart(int customer_id){
        List<Cart> cartList = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getGetCart());
            preparedStatement.setInt(1,customer_id);
            resultSet = preparedStatement.executeQuery();

        }catch(SQLException e){
            System.out.println("Exception in viewing cart: "+e.getMessage());
        }

        try {
            while (resultSet.next()) {
                cartList.add(new Cart(resultSet.getInt("id"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Exception in reading cart: "+e.getMessage());
        }
        return cartList;
    }

    public boolean removeFromCart(int productID,int customID){
        boolean isRemovedFromCart = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getRemoveFromCart());
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2,customID);
            preparedStatement.executeUpdate();
            isRemovedFromCart = true;
        } catch (SQLException e) {
            System.out.println("Exception in removing from cart: " + e.getMessage());
        }

        return isRemovedFromCart;
    }

    public boolean updateCartQuantity(int quantity, int product_id, int customer_id){
        boolean isQuantityUpdated = false;
        int oldQuantity = getCartProductQuantity(product_id,customer_id);
        System.out.println(oldQuantity);
        int newQuantity = oldQuantity +quantity;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getUpdateCartProductQuantity());
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2,product_id);
            preparedStatement.setInt(3,customer_id);
            preparedStatement.executeUpdate();
            isQuantityUpdated = true;
            if(newQuantity<=0){
                removeFromCart(product_id,customer_id);
                System.out.println("Successfully removed");
            }
        } catch (SQLException e) {
            System.out.println("Exception in updating cart: " + e.getMessage());
        }
        return isQuantityUpdated;
    }
}
