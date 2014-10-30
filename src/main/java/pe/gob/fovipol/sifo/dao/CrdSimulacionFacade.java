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
import pe.gob.fovipol.sifo.model.simulacion.CrdSimulacion;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdSimulacionFacade extends AbstractFacade<CrdSimulacion> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdSimulacionFacade() {
        super(CrdSimulacion.class);
    }
    public BigDecimal obtenerCorrelativo() {
        BigDecimal id = new BigDecimal(0);

        Query q = em.createQuery("SELECT MAX(a.idenSimuSim) FROM CrdSimulacion a", BigDecimal.class);
        id = (BigDecimal) q.getSingleResult();
        if(id==null)
            id=BigDecimal.ONE;
        else
            id=id.add(new BigDecimal(1));
        return id;
    }
}
