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
import jpa.entities.Tac;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class TacFacade extends AbstractFacade<Tac> {
    @PersistenceContext(unitName = "MARK3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TacFacade() {
        super(Tac.class);
    }
    
   public List<Tac>listaTAC(int tac){
    List<Tac> lista = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t FROM Tac t WHERE t.idtac=:id");
        query.setParameter("id",tac);
        lista=query.getResultList();
         System.out.println("CoreTeam: "+lista);
     System.out.println("no debo entrar ");
     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return lista;
    }  
   
   public Tac tacselec(int tac){
    Tac selec = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t.name FROM Tac t WHERE t.idtac=:id");
        query.setParameter("id",tac);
        selec=(Tac) query.getSingleResult();
         System.out.println("Tac seleccionado: "+selec);

     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return selec;
    }  
    
    
}
