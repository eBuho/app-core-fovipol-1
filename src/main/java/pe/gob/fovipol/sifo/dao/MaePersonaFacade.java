/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;

/**
 *
 * @author ebuho
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
    public BigDecimal obtenerCorrelativo() {
        BigDecimal id = new BigDecimal(0);

        Query q = em.createQuery("SELECT MAX(a.codiPersPer) FROM MaePersona a", BigDecimal.class);
        id = (BigDecimal) q.getSingleResult();
        if(id==null)
            id=new BigDecimal(BigInteger.ONE);
        else
            id=id.add(new BigDecimal(1));
        return id;
    }
    
    public List<MaePersona> findFamilia(MaePersona padre) {
        List<MaePersona> lista = null;
        String sql = "select d from MaePersona d where d.codiPerpPer.codiPersPer = :codiPerpPer";
        Query q = em.createQuery(sql);
        q.setParameter("codiPerpPer", padre.getCodiPersPer());
        lista = q.getResultList();
        return lista;
    }
}
