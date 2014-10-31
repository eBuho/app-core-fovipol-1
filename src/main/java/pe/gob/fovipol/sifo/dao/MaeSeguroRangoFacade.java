/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public List<MaeSeguroRango> findBySeguro(BigDecimal idenSeguSeg) {
        List<MaeSeguroRango> lista = null;
        String sql = "select d from MaeSeguroRango d where d.flagEstaSgr<>0 and "
                + "d.maeSeguroRangoPK.idenSeguSeg=:idenSeguSeg order by d.ragnEdaiSgr";
        Query q = em.createQuery(sql);
        q.setParameter("idenSeguSeg", idenSeguSeg);
        lista = q.getResultList();
        return lista;
    }
    public boolean findByEdad(BigDecimal idenSeguSeg,int edad) {
        String sql = "select count(d) from MaeSeguroRango d where d.flagEstaSgr<>0 and "
                + "d.maeSeguroRangoPK.idenSeguSeg=:idenSeguSeg and "
                + "d.ragnEdaiSgr<=:edad and d.rangEdafSgr>=:edad";
        Query q = em.createQuery(sql);
        q.setParameter("idenSeguSeg", idenSeguSeg);
        q.setParameter("edad", edad);
        long cantidad = (long) q.getSingleResult();
        if(cantidad>0)
            return true;
        else
            return false;
    }
}
