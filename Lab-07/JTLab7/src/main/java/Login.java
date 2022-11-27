
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

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
                .setParameter(2, password);
        boolean valid = query != null;
        if (valid) 
        {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", getUser());
            return "admin";
        } 
        else 
        {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Passowrd",
                                "Please enter correct username and Password"));
            return "login";
        }
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
