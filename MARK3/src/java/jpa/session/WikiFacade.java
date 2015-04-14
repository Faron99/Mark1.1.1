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
import jpa.entities.Tac;
import jpa.entities.Wiki;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class WikiFacade extends AbstractFacade<Wiki> {
    @PersistenceContext(unitName = "MARK3PU")
    private EntityManager em;

    int IDW;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WikiFacade() {
        super(Wiki.class);
    }
    
    
    public String Descripion(int idWiki){  
         String listad = null;
    
try {
   // Query query=em.createQuery("SELECT t from Tac t WHERE t.pMidpm.idpm=:id");
//Query query=em.createQuery("SELECT ALL FROM Wiki");
//Query query=em.createQuery("SELECT * FROM Wiki WHERE idWiki=:id");

Query query=em.createQuery("SELECT t.description FROM Wiki t WHERE t.idWiki=:id");
query.setParameter("id", idWiki);
listad=query.toString();
System.out.println("Datos: "+listad);


}
catch(Exception e){
    System.out.println(e);
}
return listad;


}    
            
            
     public List<Wiki> listaDescripion(int idWiki){  
         List<Wiki> lista = null;
    
try {
   // Query query=em.createQuery("SELECT t from Tac t WHERE t.pMidpm.idpm=:id");
//Query query=em.createQuery("SELECT ALL FROM Wiki");
//Query query=em.createQuery("SELECT * FROM Wiki WHERE idWiki=:id");

Query query=em.createQuery("SELECT c.description FROM Wiki c WHERE c.idWiki=:id");
query.setParameter("id", idWiki);
lista=query.getResultList();
System.out.println("Datos: "+lista);


}
catch(Exception e){
    System.out.println(e);
}
return lista;


}    
}
