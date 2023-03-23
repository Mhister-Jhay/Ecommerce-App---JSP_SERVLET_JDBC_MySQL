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

}
