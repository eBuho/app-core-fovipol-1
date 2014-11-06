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
import pe.gob.fovipol.sifo.model.maestros.MaeArea;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeAreaFacade extends AbstractFacade<MaeArea> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeAreaFacade() {
        super(MaeArea.class);
    }
    
    public List<MaeArea> findAllActivo() {
        List<MaeArea> lista = null;
        String sql = "select d from MaeArea d where d.flagEstaAre<>0";

        Query q = em.createQuery(sql);
        lista = q.getResultList();
        return lista;
    }
    public List<MaeArea> findAllOrdenado() {
        List<MaeArea> lista = null;
        String sql = "select d from MaeArea d order by d.codiAreaAre";

        Query q = em.createQuery(sql);
        lista = q.getResultList();
        return lista;
    }
}
