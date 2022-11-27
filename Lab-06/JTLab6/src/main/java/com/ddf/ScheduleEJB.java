/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Home pc
 */
@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ScheduleEJB 
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @EJB
    private StatefulScheduleEJB statefulScheduleEJB;
    
    @Resource
    private TimerService timerService;
    public void createTimer(long milliseconds) {
        Date timeout = new Date(new Date().getTime() + milliseconds);
        timerService.createTimer(timeout, "Invoked timer");
    }
    
    @Timeout
    public void timeoutHandler(Timer timer) {
        System.out.println("Timer event: " + timer.getInfo());
        statefulScheduleEJB.addAll();
    }
    
    @Lock(LockType.READ)
    public List<Schedule> findSchedules()
    {
        TypedQuery<Schedule> query = entityManager.createNamedQuery("findAllSchedules", Schedule.class);
        return query.getResultList();
    }
    
    public boolean checkAvailableSchedule(Schedule sch)
    {
        List<Schedule> schedules = findSchedules();
        for (Schedule schedule : schedules)
        {
            if (!Objects.equals(schedule.getWeek(), sch.getWeek()))
            {
                continue;
            }
            
            if (sch.getStart() >= schedule.getStart() && sch.getEnd() <= schedule.getEnd())
            {
                return false;
            }
            
            if (sch.getStart() < schedule.getStart() && sch.getEnd() <= schedule.getEnd())
            {
                return false;
            }
            
            if (sch.getStart() >= schedule.getStart() && sch.getStart() <= schedule.getEnd())
            {
                return false;
            }
        }
        return true;
    }
}
