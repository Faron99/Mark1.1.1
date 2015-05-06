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
import jpa.entities.WikiHasIssue;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class WikiHasIssueFacade extends AbstractFacade<WikiHasIssue> {
    @PersistenceContext(unitName = "MARK3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WikiHasIssueFacade() {
        super(WikiHasIssue.class);
    }
    
    
    public List<WikiHasIssue> lista(int a){
      List<WikiHasIssue> lista=null;
      try{
Query query= em.createQuery("SELECT t.tac FROM WikiHasIssue t WHERE t.wiki.idWiki=:a");
query.setParameter("a",a);
lista=query.getResultList();
System.out.println("TACS: "+lista);

}
    catch(Exception e){
 System.out.println(e);
    }
    return lista;
}
}
