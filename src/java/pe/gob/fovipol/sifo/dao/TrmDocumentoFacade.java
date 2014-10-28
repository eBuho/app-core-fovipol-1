/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;

/**
 *
 * @author ebuho
 */
@Stateless
public class TrmDocumentoFacade extends AbstractFacade<TrmDocumento> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrmDocumentoFacade() {
        super(TrmDocumento.class);
    }
    
}
