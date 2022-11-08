/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

/**
 *
 * @author Home pc
 */
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TeamDao {
    // Injected database connection:
    @PersistenceContext 
    private EntityManager em;

    public void persist(Team team) 
    {
        em.persist(team);
    }

    public List<Team> getAllTeams() 
    {
        TypedQuery<Team> query = em.createQuery(
            "SELECT t FROM Team t ORDER BY t.id", Team.class);
        return query.getResultList();
    }
    
    public List<Team> getTeams()
    {
        return em.createNamedQuery("Teams.basicFindAll").getResultList();
    }
}
