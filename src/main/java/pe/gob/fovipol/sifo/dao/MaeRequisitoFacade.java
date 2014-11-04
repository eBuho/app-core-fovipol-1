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
import pe.gob.fovipol.sifo.model.maestros.MaeRequisito;

/**
 *
 * @author probook
 */
@Stateless
public class MaeRequisitoFacade extends AbstractFacade<MaeRequisito> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeRequisitoFacade() {
        super(MaeRequisito.class);
    }
    
    public List<MaeRequisito> findByProceso(BigDecimal idenProcPrc) {
        List<MaeRequisito> lista = null;
        Query q = em.createNamedQuery("MaeRequisito.findByIdenProcPrc");
        q.setParameter("idenProcPrc", idenProcPrc);
        lista = q.getResultList();
        return lista;
    }
    
    public List<MaeRequisito> findByProcesoActivo(BigDecimal idenProcPrc) {
        List<MaeRequisito> lista = null;
        Query q=em.createQuery("SELECT a FROM MaeRequisito a where a.maeRequisitoPK.idenProcPrc=:idenProcPrc and a.flagEstaReq<>0");
        q.setParameter("idenProcPrc", idenProcPrc);
        lista = q.getResultList();
        return lista;
    }
    
    public BigInteger obtenerCorrelativo(BigDecimal idenProcPrc) {
        BigInteger id = new BigInteger("0");
        Query q = em.createQuery("SELECT MAX(a.maeRequisitoPK.secuMaeReq) FROM MaeRequisito a where a.maeRequisitoPK.idenProcPrc=:idenProcPrc", BigDecimal.class);
        q.setParameter("idenProcPrc", idenProcPrc.toBigInteger());
        id = (BigInteger) q.getSingleResult();
        id=id.add(new BigInteger("1"));
        return id;
    }
}
