/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Workstatus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUHAMA
 */
@Stateless
public class WorkstatusFacade extends AbstractFacade<Workstatus> {
    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkstatusFacade() {
        super(Workstatus.class);
    }
    
}
