/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Typepunishment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TUHAMA
 */
@Stateless
public class TypepunishmentFacade extends AbstractFacade<Typepunishment> {
    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypepunishmentFacade() {
        super(Typepunishment.class);
    }
    
}
