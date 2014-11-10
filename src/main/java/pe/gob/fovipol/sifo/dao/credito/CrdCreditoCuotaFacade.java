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
import pe.gob.fovipol.sifo.model.credito.CrdCreditoCuota;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdCreditoCuotaFacade extends AbstractFacade<CrdCreditoCuota> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdCreditoCuotaFacade() {
        super(CrdCreditoCuota.class);
    }
    
    public List<CrdCreditoCuota> findByCredito(CrdCredito credito) {
        List<CrdCreditoCuota> lista = null;
        String sql = "select d from CrdCreditoCuota d where d.flagEstaCuo<>0 "
                + "and d.crdCreditoCuotaPK.idenCredCrd =:credito";        
        Query q = em.createQuery(sql);
        q.setParameter("credito", credito.getIdenCredCrd());
        lista = q.getResultList();
        return lista;
    }
}
