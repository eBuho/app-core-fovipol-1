/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;

/**
 *
 * @author probook
 */
@Stateless
public class MaeSeguroFacade extends AbstractFacade<MaeSeguro> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeSeguroFacade() {
        super(MaeSeguro.class);
    }
    
}
