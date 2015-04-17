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
import jpa.entities.Tac;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class IssueFacade extends AbstractFacade<Issue> {
    @PersistenceContext(unitName = "MARK3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IssueFacade() {
        super(Issue.class);
    }
    
    
    public List<Issue> listaissues(int idpm){
    List<Issue> lista = null;
    
try {
    //Query query=em.createQuery("SELECT t from Tac t WHERE t.pMidpm.idpm=:id");
//Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
//Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");   
Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id ORDER BY t.issue");
query.setParameter("id", idpm);
lista=query.getResultList();
System.out.println("ISSUE: "+lista);


}
catch(Exception e){
    System.out.println(e);
}
return lista;


}    
    
    public List<Issue> listafilter(){
    List<Issue> lista = null;
    
try {
    //Query query=em.createQuery("SELECT t from Tac t WHERE t.pMidpm.idpm=:id");
//Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
//Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");   
Query query=em.createQuery("SELECT t.description FROM Issue t");
lista=query.getResultList();
System.out.println("ISSUEfilterlist: "+lista);


}
catch(Exception e){
    System.out.println(e);
}
return lista;


}    
    
}
