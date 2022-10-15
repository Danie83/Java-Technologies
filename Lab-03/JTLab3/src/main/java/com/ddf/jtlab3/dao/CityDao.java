/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf.jtlab3.dao;

import com.ddf.jtlab3.jdbc.JDBCConnection;
import com.ddf.jtlab3.models.City;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home pc
 */
public class CityDao implements Dao<City> {
    private List<City> cities = new ArrayList<>();
    private final Connection connection;
    
    public CityDao() throws SQLException
    {
        this.connection = JDBCConnection.getInstance().getConnection();
    }

    @Override
    public List<City> getAll() 
    {
        List<City> allCities = new ArrayList<>();
        String sql = "SELECT * FROM public.cities";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) 
        {

            if (resultSet.next()) 
            {
                int cityId = resultSet.getInt("id");
                String cityName = resultSet.getString("city");

                allCities.add(new City(cityId, cityName));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }

        return allCities;
    }
}
