/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.seguridad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;

/**
 *
 * @author eBuho
 */
@Stateless
public class MaePersonaFacade extends AbstractFacade<MaePersona> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaePersonaFacade() {
        super(MaePersona.class);
    }
    
}
