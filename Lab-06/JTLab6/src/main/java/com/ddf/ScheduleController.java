/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Home pc
 */
@Named(value="scheduleController")
@ApplicationScoped
@Singleton
@Startup
public class ScheduleController 
{
    @EJB
    private ScheduleEJB scheduleEJB;
    @EJB
    private StatefulScheduleEJB statefulScheduleEJB;
    private Schedule schedule = new Schedule();
    private List<Schedule> scheduleList = new ArrayList<>();
    
    public List<Schedule> getScheduleList()
    {
        scheduleList = getScheduleEJB().findSchedules();
        return scheduleList;
    }
    
    public String viewSchedule()
    {
        return "schedule.xhtml";
    }
    
    public void addNewSchedule()
    {
        if (!scheduleEJB.checkAvailableSchedule(schedule))
        {
            return;
        }
        setSchedule(getStatefulScheduleEJB().addNew(getSchedule()));
        // because it would duplicate elements
        schedule = new Schedule();
    }
    
    public String addAllToSchedule()
    {
        getStatefulScheduleEJB().addAll();
        setScheduleList(getScheduleEJB().findSchedules());
        return "schedule.xhtml";
    }

    /**
     * @return the scheduleEJB
     */
    public ScheduleEJB getScheduleEJB() {
        return scheduleEJB;
    }

    /**
     * @param scheduleEJB the scheduleEJB to set
     */
    public void setScheduleEJB(ScheduleEJB scheduleEJB) {
        this.scheduleEJB = scheduleEJB;
    }

    /**
     * @return the schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @param scheduleList the scheduleList to set
     */
    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    /**
     * @return the statefulScheduleEJB
     */
    public StatefulScheduleEJB getStatefulScheduleEJB() {
        return statefulScheduleEJB;
    }

    /**
     * @param statefulScheduleEJB the statefulScheduleEJB to set
     */
    public void setStatefulScheduleEJB(StatefulScheduleEJB statefulScheduleEJB) {
        this.statefulScheduleEJB = statefulScheduleEJB;
    }
    
}
