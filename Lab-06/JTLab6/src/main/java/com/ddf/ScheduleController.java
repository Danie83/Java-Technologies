/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ddf;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Home pc
 */
@Named(value="scheduleController")
@RequestScoped
public class ScheduleController 
{
    @EJB
    private ScheduleEJB scheduleEJB;
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
    
    public String addNewSchedule()
    {
        setSchedule(getScheduleEJB().addNew(getSchedule()));
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
    
}