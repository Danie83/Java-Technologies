/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Home pc
 */
@Named(value="team")
@ManagedBean
@RequestScoped
public class Team implements Serializable{
    private int id;
    private Date date;
    private String city;
    ArrayList teamsList;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    Connection connection;

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) 
    {
        this.date = date;
    }

    /**
     * @return the city
     */
    public String getCity() 
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) 
    {
        this.city = city;
    }
    
    public Connection getConnection() 
    {
        try 
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JTLab3", "postgres", "postgres");
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        return connection;
    }

    public ArrayList teamsList() 
    {
        try 
        {
            teamsList = new ArrayList();
            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from teams");
            while (rs.next()) 
            {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setDate(rs.getDate("date"));
                team.setCity(rs.getString("city"));
                teamsList.add(team);
            }
            connection.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        return teamsList;
    }

    public String save() 
    {
        int result = 0;
        try 
        {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "insert into teams(date, city) values(?,?)");
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            stmt.setString(2, city);
            result = stmt.executeUpdate();
            connection.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        
        if (result != 0) 
        {
            return "index.xhtml?faces-redirect=true";
        } 
        else 
        {
            return "create.xhtml?faces-redirect=true";
        }
    }

    public String edit(int id) 
    {
        Team team = null;
        try {
            connection = getConnection();
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from teams where id = " + (id));
            rs.next();
            team = new Team();
            team.setId(rs.getInt("id"));
            team.setDate(rs.getDate("date"));
            team.setCity(rs.getString("city"));
            sessionMap.put("editTeam", team);
            connection.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        return "/edit.xhtml?faces-redirect=true";
    }

    public String update(Team t) 
    {
        try 
        {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "update teams set date=?, city=? where id=?");
            System.out.println(new java.sql.Date(t.getDate().getTime()));
            System.out.println(t.getCity());
            System.out.println(t.getId());
            stmt.setDate(1, new java.sql.Date(t.getDate().getTime()));
            stmt.setString(2, t.getCity());
            stmt.setInt(3, t.getId());
            stmt.executeUpdate();
            connection.close();
        } 
        catch (Exception e) 
        {
            System.out.println();
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void delete(int id) 
    {
        try 
        {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete from teams where id = " + id);
            stmt.executeUpdate();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
    public String redirection(){
        return "index.xhtml?faces-redirect=true";
    } 
}
