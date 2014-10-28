/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.gob.fovipol.sifo.model.tramite.TrmEstatramHis;

/**
 *
 * @author ebuho
 */
@Stateless
public class TrmEstatramHisFacade extends AbstractFacade<TrmEstatramHis> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrmEstatramHisFacade() {
        super(TrmEstatramHis.class);
    }
    
}
