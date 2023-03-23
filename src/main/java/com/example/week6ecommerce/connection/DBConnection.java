package com.example.week6ecommerce.connection;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class DBConnection {
    private String jdbcURL = "jdbc:mysql://localhost:3306/e_commerce";
    private String userName = "root";
    private String password = "joshua12";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, userName, password);
        } catch (SQLException e) {
            System.out.println("Error in connecting to database "+e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
