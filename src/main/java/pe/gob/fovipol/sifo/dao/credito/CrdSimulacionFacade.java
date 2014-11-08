/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.credito;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.dao.AbstractFacade;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdSimulacionFacade extends AbstractFacade<CrdSimulacion> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdSimulacionFacade() {
        super(CrdSimulacion.class);
    }
    public BigDecimal obtenerCorrelativo() {
        BigDecimal id = new BigDecimal(0);

        Query q = em.createQuery("SELECT MAX(a.idenSimuSim) FROM CrdSimulacion a", BigDecimal.class);
        id = (BigDecimal) q.getSingleResult();
        if(id==null)
            id=BigDecimal.ONE;
        else
            id=id.add(new BigDecimal(1));
        return id;
    }
    
    public List<CrdSimulacion> findBySocioProducto(BigDecimal idSocio,BigDecimal idProducto) {
        List<CrdSimulacion> lista = null;
        String sql = "select d from CrdSimulacion d where d.flagEstaSim<>0 "
                + "and d.idenPersPer.codiPersPer=:idSocio and d.idenProdPrd.idenProdPrd =:idProducto order by d.fechCreaAud desc";        
        Query q = em.createQuery(sql);
        q.setParameter("idSocio", idSocio);
        q.setParameter("idProducto", idProducto);
        lista = q.getResultList();
        return lista;
    }
}