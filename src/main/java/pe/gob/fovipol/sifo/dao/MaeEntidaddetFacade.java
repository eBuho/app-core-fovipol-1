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
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeEntidaddetFacade extends AbstractFacade<MaeEntidaddet> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeEntidaddetFacade() {
        super(MaeEntidaddet.class);
    }
    
    public List<MaeEntidaddet> findDetalle(MaeEntidad padre) {
        List<MaeEntidaddet> lista = null;
        String sql = "select d from MaeEntidaddet d where d.codiEntiEnt.codiEntiEnt = :codiEntiEnt order by d.secuEntiDet";
        Query q = em.createQuery(sql);
        q.setParameter("codiEntiEnt", padre.getCodiEntiEnt());
        lista = q.getResultList();
        return lista;
    }
    public List<MaeEntidaddet> findDetalleActivo(MaeEntidad padre) {
        List<MaeEntidaddet> lista = null;
        String sql = "select d from MaeEntidaddet d where d.codiEntiEnt.codiEntiEnt = :codiEntiEnt and d.flagEstaDet<>"+
                Constantes.VALOR_ESTADO_INACTIVO+" order by d.secuEntiDet";
        Query q = em.createQuery(sql);
        q.setParameter("codiEntiEnt", padre.getCodiEntiEnt());
        lista = q.getResultList();
        return lista;
    }
    public List<MaeEntidaddet> findDetalleActivoCaja(MaeEntidad padre,BigDecimal caja) {
        List<MaeEntidaddet> lista = null;
        String sql = "select d from MaeEntidaddet d where d.codiEntiEnt.codiEntiEnt = :codiEntiEnt and d.flagEstaDet<>"+
                Constantes.VALOR_ESTADO_INACTIVO+" and d.valoNumuDet=:caja order by d.secuEntiDet";
        Query q = em.createQuery(sql);
        q.setParameter("codiEntiEnt", padre.getCodiEntiEnt());
        q.setParameter("caja", caja);
        lista = q.getResultList();
        return lista;
    }
    
    public BigDecimal obtenerCorrelativo() {
        BigDecimal id = new BigDecimal(0);

        Query q = em.createQuery("SELECT MAX(a.idenEntiDet) FROM MaeEntidaddet a", BigDecimal.class);
        id = (BigDecimal) q.getSingleResult();
        id=id.add(new BigDecimal(1));
        return id;
    }
    public MaeEntidaddet findIdenEntiDet(int clave,String entidad) {
        List<MaeEntidaddet> id;
        Query q = em.createQuery("SELECT a FROM MaeEntidaddet a WHERE a.codiEntiEnt.codiEntiEnt=:entidad AND a.secuEntiDet=:id");
        q.setParameter("id", clave);
        q.setParameter("entidad", entidad);
        id = q.getResultList();
        if(id==null || id.isEmpty())
            return null;
        return id.get(0);
    }
}
