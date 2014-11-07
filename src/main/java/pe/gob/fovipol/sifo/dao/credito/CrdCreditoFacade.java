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
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdCreditoFacade extends AbstractFacade<CrdCredito> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdCreditoFacade() {
        super(CrdCredito.class);
    }
    
    public CrdCredito findByTramite(TrmTramite tramite) {
        List<CrdCredito> lista = null;
        String sql = "select d from CrdCredito d where d.flagEstaCrd<>0 "
                + "and d.idenExpeTrm =:tramite";        
        Query q = em.createQuery(sql);
        q.setParameter("tramite", tramite);
        lista = q.getResultList();
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista.get(0);
    }
}
