
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */

/**
 * Web application lifecycle listener.
 *
 * @author Home pc
 */
public class ReadInitParameter implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) 
    {
        ServletContext ctx = sce.getServletContext();
        
        String category = ctx.getInitParameter("category");
        InitParameters.setDefaultCategory(category);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) 
    {
        
    }
}
