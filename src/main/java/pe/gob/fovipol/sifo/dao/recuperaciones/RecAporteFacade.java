/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.recuperaciones;

import java.math.BigDecimal;
import pe.gob.fovipol.sifo.dao.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.recuperaciones.RecAporte;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author ebuho
 */
@Stateless
public class RecAporteFacade extends AbstractFacade<RecAporte> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecAporteFacade() {
        super(RecAporte.class);
    }
    public BigDecimal totalAportesSocio(MaeSocio socio) {
        BigDecimal lista = null;
        String sql = "SELECT SUM(a.impoCobrApo) FROM RecAporte a WHERE a.idenPersPer=:socio and a.flagEstaApo<>"+Constantes.VALOR_ESTADO_INACTIVO;        
        Query q = em.createQuery(sql, BigDecimal.class);
        q.setParameter("socio", socio);
        lista = (BigDecimal) q.getSingleResult();
        if(lista==null)
            return BigDecimal.ZERO;
        return lista;
    }
}