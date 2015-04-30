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
   
    public Tac nametac(int tac){
    Tac selec = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t.name FROM Tac t WHERE t.idtac=:id");
        query.setParameter("id",tac);
        selec=(Tac) query.getSingleResult();
         System.out.println("Tac seleccionado2: "+selec);

     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return selec;
    }  
    
    
    public List<Tac> nametacpm(int tacpm){
    List<Tac> selec = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t FROM Tac t WHERE t.pMidpm.idpm=:id");
        query.setParameter("id",tacpm);
        selec=query.getResultList();
         System.out.println("Tac de pm seleccionado: "+selec);

     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return selec;
    }  
    
    
    public List<Tac> newquery(String patron1){
    List<Tac> selec = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t.name FROM Tac t WHERE t.name=:name");
        query.setParameter("name",patron1);
        selec=query.getResultList();
         System.out.println("Tacs autocompletes: "+selec);
     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return selec;
    }  
    
    
   /*  public List<Tac> autoNamequery(String patron1){
    List<Tac> selec = null;
        
     try{
        // Query query=em.createQuery("SELECT t FROM Issue t WHERE t.tACidtac.idtac=:id");
    
        Query query=em.createQuery("SELECT t.name FROM Tac t WHERE t.name=:name");
        query.setParameter("name",patron1);
        selec=query.getResultList();
         System.out.println("Tacs autocompletes: "+selec);
     } 
     
     catch(Exception e){
         System.out.println(e);
     }  
        
    return selec;
    }  
    
     public List<Tac> autoQueryName(String patron) {
//TypedQuery<Tac> query = em.createQuery("SELECT NEW com.server.entity.beans.TblMaterial( c.noParte,c.nombre) FROM TblMaterial c WHERE c.nombre LIKE :patron", Tac.class);
        
        TypedQuery<Tac> query = em.createQuery("SELECT NEW jpa.entities.Tac(c.name) FROM Tac c WHERE c.name LIKE: patron",Tac.class);
        query.setParameter("patron", patron.toLowerCase() + "%");

        List<Tac> res = query.getResultList();
       // res.add(new Tac(patron, "Buscar: "));
System.out.println("Tacs autocompletes2: "+res);
        return res;

    }*/

    
    
}
