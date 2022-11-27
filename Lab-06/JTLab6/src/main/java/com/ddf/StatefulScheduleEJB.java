/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Home pc
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class StatefulScheduleEJB 
{
    @PersistenceContext
    private EntityManager entityManager;
    
    private List<Schedule> schedules;
    
    public Schedule addNew(Schedule schedule)
    {
        if (schedules == null)
        {
            schedules = new ArrayList<>();
        }
        getSchedules().add(schedule);
        return schedule;
    }
    
    @Lock(LockType.WRITE)
    public void addAll()
    {
        for (Schedule schedule : getSchedules())
        {
            entityManager.persist(schedule);
            entityManager.flush();
            entityManager.clear();
        }
        getSchedules().clear();
    }

    /**
     * @return the schedules
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * @param schedules the schedules to set
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
