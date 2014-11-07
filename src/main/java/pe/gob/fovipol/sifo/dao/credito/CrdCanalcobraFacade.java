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
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobra;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdCanalcobraFacade extends AbstractFacade<CrdCanalcobra> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdCanalcobraFacade() {
        super(CrdCanalcobra.class);
    }
    
    public List<CrdCanalcobra> findByCredito(CrdCredito credito) {
        List<CrdCanalcobra> lista = null;
        String sql = "select d from CrdCanalcobra d where d.flagEstaCdc<>0 "
                + "and d.crdCredito =:credito";        
        Query q = em.createQuery(sql);
        q.setParameter("credito", credito);
        lista = q.getResultList();
        return lista;
    }
}
