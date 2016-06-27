/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Foreignlanguage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUHAMA
 */
@Stateless
public class ForeignlanguageFacade extends AbstractFacade<Foreignlanguage> {
    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ForeignlanguageFacade() {
        super(Foreignlanguage.class);
    }
    
}
