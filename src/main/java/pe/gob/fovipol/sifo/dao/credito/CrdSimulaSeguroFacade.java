/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.credito;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.dao.AbstractFacade;
import pe.gob.fovipol.sifo.model.credito.CrdSimulaSeguro;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdSimulaSeguroFacade extends AbstractFacade<CrdSimulaSeguro> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdSimulaSeguroFacade() {
        super(CrdSimulaSeguro.class);
    }
    
    public List<CrdSimulaSeguro> findBySimulacion(CrdSimulacion simulacion) {
        List<CrdSimulaSeguro> lista = null;
        String sql = "select d from CrdSimulaSeguro d where d.flagEstaSsg<>0 "
                + "and d.crdSimulaSeguroPK.idenSimuSim =:simulacion";        
        Query q = em.createQuery(sql);
        q.setParameter("simulacion", simulacion.getIdenSimuSim());
        lista = q.getResultList();
        return lista;
    }
}
