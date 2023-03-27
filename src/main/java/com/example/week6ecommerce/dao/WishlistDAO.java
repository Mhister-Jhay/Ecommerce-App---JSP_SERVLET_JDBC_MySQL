package com.example.week6ecommerce.dao;

import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {

    private final DBConnection dbConnection;
    private final Queries queries;

    public WishlistDAO() {
        this.dbConnection = new DBConnection();
        this.queries = new Queries();
    }
    public void addToWishList(int customerID, int productID){
        boolean isAddedToWishlist = false;
        List<Product> wishlist = viewWishlist(customerID);
        for(Product wish: wishlist) {
            isAddedToWishlist = productID != wish.getId();
        }
        if(wishlist.size() == 0){
            isAddedToWishlist = true;
        }
        if(isAddedToWishlist){
            try {
                Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getAddToWishList());
                preparedStatement.setInt(1, customerID);
                preparedStatement.setInt(2, productID);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Exception in adding to wishlist: " + e.getMessage());
            }
        }
    }

    public boolean removeFromWishlist(int productID, int customerID){
        boolean isRemovedFromWishlist = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getRemoveFromWishList());
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2, customerID);
            preparedStatement.executeUpdate();
            isRemovedFromWishlist = true;
        } catch (SQLException e) {
            System.out.println("Exception in removing from wishlist: " + e.getMessage());
        }
        return isRemovedFromWishlist;
    }

    public List<Product> viewWishlist(int customer_id){
        List<Product> wishlist = new ArrayList<>();
        ResultSet resultSet;
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getViewWishList());
            preparedStatement.setInt(1,customer_id);
            resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            wishlist.add(new Product(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("category_id"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("image")));
        }
        }catch(SQLException e){
            System.out.println("Exception in viewing wishlist: "+e.getMessage());
        }
        return wishlist;
    }
}
