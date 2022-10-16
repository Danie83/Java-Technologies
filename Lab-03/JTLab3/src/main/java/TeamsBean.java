/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import com.ddf.jtlab3.dao.TeamDao;
import com.ddf.jtlab3.models.Team;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Home pc
 */
@Named(value = "teamsBean")
@ViewScoped
public class TeamsBean implements Serializable {

    private List<Team> ateams;
    
    /**
     * Creates a new instance of TeamsBean
     */
    public TeamsBean()
    {
        try
        {
            TeamDao td = new TeamDao();
            this.ateams = td.getAll();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
    }
    
    @PostConstruct
    public void init() {
        try
        {
            TeamDao td = new TeamDao();
            this.ateams = td.getAll();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    /**
     * @return the ateams
     */
    public List<Team> getAteams() {
        return ateams;
    }

    /**
     * @param ateams the ateams to set
     */
    public void setAteams(List<Team> ateams) {
        this.ateams = ateams;
    }
    
}
