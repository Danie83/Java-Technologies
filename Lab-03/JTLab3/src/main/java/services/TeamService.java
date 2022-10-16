/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.ddf.jtlab3.dao.TeamDao;
import com.ddf.jtlab3.models.Team;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Home pc
 */
@Named
@ApplicationScoped
public class TeamService {

    private List<Team> teams;

    @PostConstruct
    public void init() {
        try {
            teams = new ArrayList<>();
            TeamDao td = new TeamDao();
            teams = td.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }
}
