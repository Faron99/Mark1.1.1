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
import jpa.entities.CoreTeam;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class CoreTeamFacade extends AbstractFacade<CoreTeam> {
    @PersistenceContext(unitName = "MARK3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoreTeamFacade() {
        super(CoreTeam.class);
    }
    
    
    public List<CoreTeam>listact(int ct){
    List<CoreTeam> lista = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
         Query query=em.createQuery("SELECT t FROM CoreTeam t WHERE t.tACidtac.idtac=:ct");
        query.setParameter("ct",ct);
        lista=query.getResultList();
         System.out.println("CoreTeam: "+lista);

     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return lista;
    }
    
    public List<CoreTeam>listaaddct(int ct){
    List<CoreTeam> lista = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
         Query query=em.createQuery("SELECT t FROM CoreTeam t WHERE t.tACidtac.idtac=:ct");
        query.setParameter("ct",ct);
        lista=query.getResultList();
         System.out.println("CoreTeam: "+lista);

     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return lista;
    }
}
