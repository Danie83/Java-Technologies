/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import com.ddf.jtlab3.models.Team;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import services.TeamService;

/**
 *
 * @author Home pc
 */
@Named("dtTeamView")
@ViewScoped
public class TeamView implements Serializable {

    private List<Team> teams;
    private Team selectedTeam;

    @Inject
    private TeamService service;

    @PostConstruct
    public void init() {
        teams = service.getTeams();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setService(TeamService service) {
        this.service = service;
    }
    
    public void delete(Team team)
    {
        this.teams.remove(team);
    }
    
    public void add()
    {
        this.setSelectedTeam(new Team());
    }
    
    public void update()
    {
        
    }

    /**
     * @return the selectedTeam
     */
    public Team getSelectedTeam() {
        return selectedTeam;
    }

    /**
     * @param selectedTeam the selectedTeam to set
     */
    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }
}
