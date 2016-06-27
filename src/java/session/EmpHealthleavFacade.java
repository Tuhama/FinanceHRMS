/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmpHealthleav;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUHAMA
 */
@Stateless
public class EmpHealthleavFacade extends AbstractFacade<EmpHealthleav> {
    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpHealthleavFacade() {
        super(EmpHealthleav.class);
    }
    
}
