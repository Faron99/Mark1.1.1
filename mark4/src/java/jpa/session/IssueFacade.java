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

/**
 *
 * @author cristian
 */
@Stateless
public class IssueFacade extends AbstractFacade<Issue> {
    @PersistenceContext(unitName = "mark4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IssueFacade() {
        super(Issue.class);
    }
    
    
    
 public List<Issue> listaIssuesdeTac(int idtac){
    List<Issue> lista = null;
try {
Query query=em.createQuery("SELECT t from Issue t WHERE t.tACidtac.idtac=:id");
query.setParameter("id", idtac);
lista=query.getResultList();
System.out.println("ISSUE: "+lista);


}
catch(Exception e){
    System.out.println(e);
}
return lista;


} 
}
