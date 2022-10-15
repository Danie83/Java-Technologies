/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf.jtlab3.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Home pc
 */
public class JDBCConnection {
    private static JDBCConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/JTLab3";
    private String username = "postgres";
    private String password = "postgres";

    private JDBCConnection() throws SQLException {
        try 
        {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("JDBC Connection creation Failed : " + e.getMessage());
        }
    }

    public Connection getConnection() 
    {
        return connection;
    }

    public static JDBCConnection getInstance() throws SQLException 
    {
        if (instance == null) 
        {
            instance = new JDBCConnection();
        } 
        else if (instance.getConnection().isClosed()) 
        {
            instance = new JDBCConnection();
        }
        return instance;
    }
}