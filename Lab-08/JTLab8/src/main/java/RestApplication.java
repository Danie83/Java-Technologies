/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Set;
import javax.ws.rs.ApplicationPath; 
import javax.ws.rs.core.Application;

/**
 *
 * @author Home pc
 */
@ApplicationPath("rest")
public class RestApplication extends Application{
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(TeamResource.class);
    }
    
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

}
