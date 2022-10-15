/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf.jtlab3.models;

/**
 *
 * @author Home pc
 */
public class City {
    private int id;
    private String city;
    
    public City(int id, String city)
    {
        this.id = id;
        this.city = city;
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
    public String getCity() {
        return city;
    }

    /**
     * @param name the name to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}
