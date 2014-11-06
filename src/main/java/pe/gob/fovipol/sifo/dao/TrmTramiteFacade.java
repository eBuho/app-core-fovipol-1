/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Stateless
public class TrmTramiteFacade extends AbstractFacade<TrmTramite> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrmTramiteFacade() {
        super(TrmTramite.class);
    }
    
    public BigDecimal obtenerCorrelativo() {
        BigDecimal id;
        Query q = em.createQuery("SELECT MAX(a.idenExpeTrm) FROM TrmTramite a", BigDecimal.class);
        id = (BigDecimal) q.getSingleResult();
        if(id==null)
            id=BigDecimal.ONE;
        else
            id=id.add(new BigDecimal(1));
        return id;
    }
}
