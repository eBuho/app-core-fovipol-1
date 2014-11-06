/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;

/**
 *
 * @author ebuho
 */
@Stateless
public class TrmMovimientoFacade extends AbstractFacade<TrmMovimiento> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrmMovimientoFacade() {
        super(TrmMovimiento.class);
    }
    
    public List<TrmMovimiento> findItemsActivos() {
        String sql = "select m from TrmMovimiento m "
                + "where m.flagSituMvm = 1 order by m.fechReceMvm desc";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }
    
    public List<TrmMovimiento> findItemsHistoricos() {
        String sql = "select m from TrmMovimiento m "
                + "where m.flagSituMvm = 0 order by m.fechReceMvm desc";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }
}
