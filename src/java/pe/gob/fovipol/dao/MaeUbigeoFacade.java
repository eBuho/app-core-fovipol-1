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
import pe.gob.fovipol.model.MaeUbigeo;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeUbigeoFacade extends AbstractFacade<MaeUbigeo> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeUbigeoFacade() {
        super(MaeUbigeo.class);
    }
    
    public List<MaeUbigeo> findDepartamentos() {
        List<MaeUbigeo> lista = null;
        String sql = "FROM MaeUbigeo a WHERE a.niveUbigUbi=1 ORDER BY a.nombUbigUbi";

        Query q = em.createQuery(sql);
        lista = q.getResultList();
        return lista;
    }
    
    public List<MaeUbigeo> findHijos(MaeUbigeo padre) {
        List<MaeUbigeo> lista = null;
        String sql = "FROM MaeUbigeo a WHERE a.idenUbipUbi=:padre ORDER BY a.nombUbigUbi";
        
        Query q = em.createQuery(sql);
        q.setParameter("padre", padre);
        lista = q.getResultList();
        return lista;
    }
}
