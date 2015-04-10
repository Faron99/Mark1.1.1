/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Wiki;

/**
 *
 * @author mmercadoco
 */
@Stateless
public class WikiFacade extends AbstractFacade<Wiki> {
    @PersistenceContext(unitName = "Mark1.1.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WikiFacade() {
        super(Wiki.class);
    }
    
}
