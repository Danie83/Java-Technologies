
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
@Named(value="login")
@ManagedBean
@SessionScoped
public class Login implements Serializable {
    private String user;
    private String password;
    private String type;
    
    @PersistenceContext
    private EntityManager em;
    
    public String validateUsernamePassword() 
    {
        TypedQuery<Users> query = em.createQuery(
                "SELECT u FROM Users u WHERE u.username = ?1 and u.password = ?2", Users.class)
                .setParameter(1, getUser())
                .setParameter(2, password)
                .setMaxResults(1);
        boolean valid = query != null;
        if (valid) 
        {
            List<Users> users = query.getResultList();
            Users current = users.get(0);
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", getUser());
            session.setAttribute("type", current.getType());
            return current.getType();
        } 
        else 
        {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Password",
                                "Please enter correct username and Password"));
            return "login";
        }
    }
    
    @Transactional
    public String register()
    {
        TimeFrame.setTimeFrame(TimeFrame.minValue, TimeFrame.maxValue);
        Calendar current = Calendar.getInstance();
        
        if (TimeFrame.min.getTimeInMillis() > current.getTimeInMillis() || TimeFrame.max.getTimeInMillis() < current.getTimeInMillis())
        {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Can't register at the moment.",
                                "Please register in the 8-20 interval."));
            return "login";
        }    
           
        try {
            em.createNativeQuery(
                    "INSERT into users (username, password, type) VALUES (?,?,?)")
                    .setParameter(1, getUser())
                    .setParameter(2, password)
                    .setParameter(3, "basic") // default
                    .executeUpdate();
            return "login";
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login";
    }
    

    public String logout() 
    {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the passsword
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
