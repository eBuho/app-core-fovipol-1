/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguroRango;

/**
 *
 * @author probook
 */
@Stateless
public class MaeSeguroRangoFacade extends AbstractFacade<MaeSeguroRango> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeSeguroRangoFacade() {
        super(MaeSeguroRango.class);
    }
    
}
