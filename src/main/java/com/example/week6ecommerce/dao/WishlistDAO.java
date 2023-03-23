package com.example.week6ecommerce.dao;

import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WishlistDAO {

    private DBConnection dbConnection;
    private Queries queries;

    public WishlistDAO() {
        this.dbConnection = new DBConnection();
        this.queries = new Queries();
    }
    public boolean addToWishList(int customerID, int productID, String wishlistID){
        boolean isAddedToWishlist = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getAddToWishList());
            preparedStatement.setString(1,wishlistID);
            preparedStatement.setInt(2, customerID);
            preparedStatement.setInt(3,productID);
            preparedStatement.executeQuery();
            isAddedToWishlist = true;
        } catch (SQLException e) {
            System.out.println("Exception in login: " + e.getMessage());
        }

        return isAddedToWishlist;
    }

    public boolean removeFromWishlist(int productID){
        boolean isRemovedFromWishlist = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getRemoveFromWishList());
            preparedStatement.setInt(1, productID);
            preparedStatement.executeQuery();
            isRemovedFromWishlist = true;
        } catch (SQLException e) {
            System.out.println("Exception in login: " + e.getMessage());
        }

        return isRemovedFromWishlist;
    }
}
