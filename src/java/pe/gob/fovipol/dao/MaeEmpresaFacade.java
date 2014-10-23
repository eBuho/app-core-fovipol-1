/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.model.MaeEmpresa;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeEmpresaFacade extends AbstractFacade<MaeEmpresa> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeEmpresaFacade() {
        super(MaeEmpresa.class);
    }
    
    public BigDecimal obtenerCorrelativo() {
        BigDecimal id = new BigDecimal(0);

        Query q = em.createQuery("SELECT MAX(a.codiEmprEmp) FROM MaeEmpresa a", BigDecimal.class);
        id = (BigDecimal) q.getSingleResult();
        if(id==null)
            id=new BigDecimal(BigInteger.ONE);
        else
            id=id.add(new BigDecimal(1));
        return id;
    }
}
