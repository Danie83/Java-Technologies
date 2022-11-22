/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Home pc
 */
@Stateful
public class StatefulScheduleEJB 
{
    @PersistenceContext
    private EntityManager entityManager;
    
    public Schedule addNew(Schedule schedule)
    {
        entityManager.persist(schedule);
        return schedule;
    }
}
