
import com.ddf.Team;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
    
    private List<Team> cache = new ArrayList<>();

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        Team team = entityManager.find(Team.class, id);
        entityManager.remove(team);
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM eteam t", Team.class);
        List<Team> teams = query.getResultList();
        cache = teams;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Team> getAllTeams() {
        if (cache.size() > 0)
        {
            return cache;
        }
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM eteam t", Team.class);
        List<Team> teams = query.getResultList();
        if (cache.size() == 0)
        {
            cache = teams;
        }
        return teams;
    }

    @GET
    @Path(value = "/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Team getUserById(@PathParam(value = "id") final Long id) {
        Team team = entityManager.find(Team.class, id);
        return team;
    }

    @Transactional
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Team createTeam(final Team team) {
        entityManager.persist(team);
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM eteam t", Team.class);
        List<Team> teams = query.getResultList();
        cache = teams;
        return team;
    }

    @Transactional
    @PUT
    @Path(value = "/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Team updateTeam(@PathParam(value = "id") final Long id, final Team team) {
        Team existingTeam = entityManager.find(Team.class, id);
        existingTeam.setDate(team.getDate());
        existingTeam.setName(team.getName());
        entityManager.persist(existingTeam);
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM eteam t", Team.class);
        List<Team> teams = query.getResultList();
        cache = teams;
        return existingTeam;
    }

    /**
     * @return the cache
     */
    public List<Team> getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(List<Team> cache) {
        this.cache = cache;
    }
}
