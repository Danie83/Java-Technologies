/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import com.ddf.jtlab3.dao.TeamDao;
import com.ddf.jtlab3.models.Team;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.annotation.ManagedProperty;

/**
 *
 * @author Home pc
 */
@Named(value = "teamsBean")
@Dependent
public class TeamsBean {

    private List<Team> ateams = new ArrayList<>();
    
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

    /**
     * @return the ateams
     */
    public List<Team> getAteams() {
        if (ateams == null)
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
        return ateams;
    }

    /**
     * @param ateams the ateams to set
     */
    public void setAteams(List<Team> ateams) {
        this.ateams = ateams;
    }
    
}
