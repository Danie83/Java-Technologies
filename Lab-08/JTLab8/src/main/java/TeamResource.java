
import com.ddf.Team;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Home pc
 */
@Path("/teams")
public class TeamResource {

    @PersistenceContext
    EntityManager entityManager;

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        Team team = entityManager.find(Team.class, id);
        entityManager.remove(team);
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Team> getAllTeams() {
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM eteam t", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    @GET
    @Path(value = "/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Team getUserById(@PathParam(value = "id") final Long id) {
        Team team = entityManager.find(Team.class, id);
        return team;
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Team createTeam(final Team team) {
        entityManager.persist(team);
        return team;
    }

    @PUT
    @Path(value = "/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Team updateTeam(@PathParam(value = "id") final Long id, final Team team) {
        Team existingTeam = entityManager.find(Team.class, id);
        existingTeam.setDate(team.getDate());
        existingTeam.setName(team.getName());
        entityManager.persist(existingTeam);
        return existingTeam;
    }
}
