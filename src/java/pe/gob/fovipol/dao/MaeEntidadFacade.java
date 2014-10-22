/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.gob.fovipol.model.MaeEntidad;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeEntidadFacade extends AbstractFacade<MaeEntidad> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeEntidadFacade() {
        super(MaeEntidad.class);
    }
    
}