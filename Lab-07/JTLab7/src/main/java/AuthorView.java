
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
@Named(value="authorViewBean")
@ManagedBean
@ViewScoped
public class AuthorView implements Serializable {
    private static final long serialVersionUID = 4L;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Documents> getDocuments()
    {
        TypedQuery<Documents> query = em.createQuery(
                "SELECT d FROM Documents d", Documents.class);
        return query.getResultList();
    }
}
