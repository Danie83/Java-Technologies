/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf.jtlab3.models;

import java.sql.Date;

/**
 *
 * @author Home pc
 */
public class Team 
{
    private int id;
    private String team;
    private Date date;
    
    public Team(int id, String team, Date date)
    {
        this.id = id;
        this.team = team;
        this.date = date;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getTeam() {
        return team;
    }

    /**
     * @param name the name to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
