/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeProcesoFacade extends AbstractFacade<MaeProceso> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeProcesoFacade() {
        super(MaeProceso.class);
    }
    
    public List<MaeProceso> findProcesosActivos() {
        List<MaeProceso> lista = null;
        Query q = em.createNamedQuery("MaeProceso.findByFlagEstaPrc");
        q.setParameter("flagEstaPrc", new Short("1"));
        lista = q.getResultList();
        return lista;
    }
}
