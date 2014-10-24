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
import pe.gob.fovipol.model.MaePersona;
import pe.gob.fovipol.model.MaeSocio;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeSocioFacade extends AbstractFacade<MaeSocio> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeSocioFacade() {
        super(MaeSocio.class);
    }
    public List<MaeSocio> findHijos(MaePersona padre) {
        List<MaeSocio> lista = null;
        String sql = "FROM MaeSocio a WHERE a.maePersona=:padre";        
        Query q = em.createQuery(sql);
        q.setParameter("padre", padre);
        lista = q.getResultList();
        return lista;
    }
}
