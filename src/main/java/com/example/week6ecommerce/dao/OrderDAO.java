package com.example.week6ecommerce.dao;

import com.example.week6ecommerce.connection.DBConnection;
import com.example.week6ecommerce.constant.Queries;
import com.example.week6ecommerce.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class OrderDAO {
    private final DBConnection dbConnection;
    private final Queries queries;
    private final LocalDate date;
    public OrderDAO(){
        this.dbConnection = new DBConnection();
        this.queries = new Queries();
        this.date = LocalDate.now();
    }
    public ResultSet getOrderSet(int customer_id){
        ResultSet resultSet = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getGetCart());
            preparedStatement.setInt(1, customer_id);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Exception in getting customer order: " + e.getMessage());
        }
        return resultSet;
    }
    public List<Order> viewOrder(int customer_id) {
        List<Order> orderList = new ArrayList<>();
        ResultSet resultSet;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getViewOrder());
            preparedStatement.setInt(1, customer_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderList.add(new Order(resultSet.getInt("id"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("order_quantity"),
                        resultSet.getDate("order_date")));
            }
        } catch (SQLException e) {
            System.out.println("Exception in viewing customer order: " + e.getMessage());

        }
        return orderList;
    }

    public void deleteCart(int customer_id){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getDeleteCart());
            preparedStatement.setInt(1, customer_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception in deleting cart: " + e.getMessage());
        }
    }

    public boolean checkOut(int customer_id){
        boolean isAddedToOrder = false;
        ResultSet resultSet = getOrderSet(customer_id);
        try {
            while (resultSet.next()) {
                Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queries.getOrderProduct());
                preparedStatement.setInt(1,customer_id);
                preparedStatement.setInt(2,resultSet.getInt("id"));
                preparedStatement.setInt(3,resultSet.getInt("quantity"));
                preparedStatement.setDouble(4,resultSet.getDouble("price"));
                preparedStatement.setDate(5, Date.valueOf(date));
                preparedStatement.executeUpdate();
                deleteCart(customer_id);
                isAddedToOrder = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception in checking out: " + e.getMessage());

        }
        return isAddedToOrder;
    }
    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.viewOrder(1);
    }
}
