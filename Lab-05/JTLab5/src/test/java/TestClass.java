
import com.ddf.Team;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
public class TestClass 
{
    @PersistenceContext 
    private EntityManager em;
    
    public void test()
    {
        em.getTransaction().begin();
        Team team = new Team();
        team.setName("Bucuresti");
        team.setDate(new Date(2022, 11, 9));
        em.persist(team);
        
    }
}
