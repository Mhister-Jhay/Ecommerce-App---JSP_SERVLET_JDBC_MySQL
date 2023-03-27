package com.example.week6ecommerce.dao;


import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Product;
import lombok.Data;

import java.sql.*;


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
            System.out.println("Exception in getting all products by customers: "+e.getMessage());
        }
        return resultSet;
    }
}
