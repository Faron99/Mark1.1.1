/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import jpa.entities.Issue;
import jpa.entities.Pm;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class PmFacade extends AbstractFacade<Pm> {
    @PersistenceContext(unitName = "MARK3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PmFacade() {
        super(Pm.class);
    }
    
   public List<Pm> listapm(int pm){
    List<Pm> lista = null;
    
try {
    //Query query=em.createQuery("SELECT t from Tac t WHERE t.pMidpm.idpm=:id");
//Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
//Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");   
Query query=em.createQuery("SELECT t FROM Pm t WHERE t.idpm=:id");
query.setParameter("id", pm);
lista=query.getResultList();
System.out.println("PM: "+lista);


}
catch(Exception e){
    System.out.println(e);
}
return lista;


}      
    
}
