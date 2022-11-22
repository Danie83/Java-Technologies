
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
@Stateless
public class LoginDAO {

    @PersistenceContext
    private EntityManager em;
        
    public boolean validate(String username, String password) 
    {
        TypedQuery<Users> query = em.createQuery(
                "SELECT u FROM Users u WHERE u.username = ?1 and u.password = ?2", Users.class)
                .setParameter(1, username)
                .setParameter(2, password);
        return (query.getResultList() != null);
    }
}
