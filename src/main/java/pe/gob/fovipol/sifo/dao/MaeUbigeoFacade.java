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
import pe.gob.fovipol.sifo.model.maestros.MaeUbigeo;

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
        String sql = "FROM MaeUbigeo a WHERE a.niveUbigUbi=1 and a.flagEstaUbi<>0  ORDER BY a.nombUbigUbi";

        Query q = em.createQuery(sql);
        lista = q.getResultList();
        return lista;
    }
    
    public List<MaeUbigeo> findHijos(MaeUbigeo padre) {
        List<MaeUbigeo> lista = null;
        String sql = "FROM MaeUbigeo a WHERE a.idenUbipUbi=:padre and a.flagEstaUbi<>0 ORDER BY a.nombUbigUbi";
        
        Query q = em.createQuery(sql);
        q.setParameter("padre", padre);
        lista = q.getResultList();
        return lista;
    }
    
    public MaeUbigeo findIdenUbigUbi(String idb) {
        List<MaeUbigeo> id;
        Query q = em.createQuery("SELECT a FROM MaeUbigeo a WHERE a.idenUbigUbi=:id");
        q.setParameter("id", idb);
        id = q.getResultList();
        if(id==null || id.isEmpty())
            return null;
        return id.get(0);
    }
}