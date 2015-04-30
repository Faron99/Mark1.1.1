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
import javax.persistence.TypedQuery;
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
     public List<Wiki> newquery(String patron1){
    List<Wiki> selec = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t.name FROM Wiki t WHERE t.name=:name");
        query.setParameter("name",patron1);
        selec=query.getResultList();
         System.out.println("Wiki autocompletes: "+selec);
     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return selec;
    }  
     
     public List<Wiki> autoQueryName2(String patron) {
//TypedQuery<Tac> query = em.createQuery("SELECT NEW com.server.entity.beans.TblMaterial( c.noParte,c.nombre) FROM TblMaterial c WHERE c.nombre LIKE :patron", Tac.class);
        
        Query query = em.createQuery("SELECT c FROM Wiki c WHERE c.name LIKE :patron");
        query.setParameter("patron", patron.toLowerCase() + "%");

        List<Wiki> res = query.getResultList();
       
System.out.println("Tacs autocompletes2: "+res);
        return res;

    }
      /*public List<Wiki> autoQueryName(String patron) {
//TypedQuery<Tac> query = em.createQuery("SELECT NEW com.server.entity.beans.TblMaterial( c.noParte,c.nombre) FROM TblMaterial c WHERE c.nombre LIKE :patron", Tac.class);
        
        TypedQuery<Wiki> query = em.createQuery("SELECT NEW jpa.entities.Wiki(c.description) FROM Wiki c WHERE c.description LIKE :patron",Wiki.class);
        query.setParameter("patron", patron.toLowerCase() + "%");

        List<Wiki> res = query.getResultList();
       res.add(new Wiki(patron));
System.out.println("Tacs autocompletes2: "+res);
        return res;

    }*/
     
     
}
