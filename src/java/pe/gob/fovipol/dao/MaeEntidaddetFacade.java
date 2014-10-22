/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.model.MaeEntidad;
import pe.gob.fovipol.model.MaeEntidaddet;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeEntidaddetFacade extends AbstractFacade<MaeEntidaddet> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeEntidaddetFacade() {
        super(MaeEntidaddet.class);
    }
    
    public List<MaeEntidaddet> findDetalle(MaeEntidad padre) {
        List<MaeEntidaddet> lista = null;
        String sql = "select d from MaeEntidaddet d where d.codiEntiEnt = :codiEntiEnt";

        Query q = em.createQuery(sql);
        q.setParameter("codiEntiEnt", padre);
        lista = q.getResultList();
        return lista;
    }
    public Integer obtenerCorrelativo() {
        Integer id = new Integer(0);

        Query q = em.createQuery("SELECT MAX(a.idenEntiDet) FROM MaeEntidaddet a", Integer.class);
        id = (Integer) q.getSingleResult();
        return ++id;
    }
}
