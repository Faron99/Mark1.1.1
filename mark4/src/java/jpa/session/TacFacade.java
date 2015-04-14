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

/**
 *
 * @author cristian
 */
@Stateless
public class TacFacade extends AbstractFacade<Tac> {
    @PersistenceContext(unitName = "mark4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TacFacade() {
        super(Tac.class);
    }

    
    public List<Tac> listaTacsdePM(int idpm){
    List<Tac> lista = null;
    
try {
Query query=em.createQuery("SELECT t from Tac t WHERE t.pMidpm.idpm=:id");
query.setParameter("id", idpm);
lista=query.getResultList();
System.out.println("ISSUE: "+lista);


}
catch(Exception e){
    System.out.println(e);
}
return lista;


}    
}
