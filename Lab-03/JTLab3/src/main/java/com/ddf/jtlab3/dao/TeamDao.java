/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf.jtlab3.dao;

import com.ddf.jtlab3.jdbc.JDBCConnection;
import com.ddf.jtlab3.models.Team;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home pc
 */
public class TeamDao implements Dao<Team> {
    private List<Team> teams;
    private final Connection connection;
    
    public TeamDao() throws SQLException
    {
        this.connection = JDBCConnection.getInstance().getConnection();
    }

    @Override
    public List<Team> getAll() 
    {
        List<Team> allTeams = new ArrayList<>();
        String sql = "SELECT * FROM teams";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) 
        {
            while (resultSet.next()) 
            {
                int teamId = resultSet.getInt("id");
                String teamName = resultSet.getString("team");
                Date teamDate = resultSet.getDate("date");
                allTeams.add(new Team(teamId, teamName, teamDate));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return allTeams;
    }

    /**
     * @return the teams
     */
    public List<Team> getTeams() {
        return getAll();
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
