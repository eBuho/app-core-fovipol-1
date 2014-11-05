/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

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
    
    public TrmMovimiento buscarUltimoMovimiento(TrmTramite tramite){        
        Query q = em.createQuery("SELECT a FROM  TrmMovimiento a WHERE a.trmMovimientoPK.idenExpeTrm=:idenExpeTrm AND "
                + "a.trmMovimientoPK.secuMoviMvm=(SELECT MAX(b.trmMovimientoPK.secuMoviMvm) FROM TrmMovimiento b WHERE b.trmMovimientoPK.idenExpeTrm=:idenExpeTrm)");
        q.setParameter("idenExpeTrm", tramite.getIdenExpeTrm());
        TrmMovimiento id=null;
        try{
            id = (TrmMovimiento) q.getSingleResult();
        }
        catch(NoResultException nre){
            return null;
        }
        return id;    
    }
}
