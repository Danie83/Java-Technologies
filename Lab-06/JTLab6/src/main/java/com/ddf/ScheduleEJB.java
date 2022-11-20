/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Home pc
 */
@Stateless
public class ScheduleEJB 
{
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Schedule> findSchedules()
    {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("findAllSchedules", Schedule.class);
        return query.getResultList();
    }
    
    public Schedule addNew(Schedule schedule)
    {
        entityManager.persist(schedule);
        return schedule;
    }
}
