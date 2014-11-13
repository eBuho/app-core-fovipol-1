/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.credito;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.dao.AbstractFacade;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Stateless
public class CrdCreditoFacade extends AbstractFacade<CrdCredito> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CrdCreditoFacade() {
        super(CrdCredito.class);
    }
    
    public CrdCredito findByTramite(TrmTramite tramite) {
        List<CrdCredito> lista = null;
        String sql = "select d from CrdCredito d where d.flagEstaCrd<>0 "
                + "and d.idenExpeTrm =:tramite";        
        Query q = em.createQuery(sql);
        q.setParameter("tramite", tramite);
        lista = q.getResultList();
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista.get(0);
    }
    
    public List<CrdCredito> findByFiltros1(Date fechaInicio, Date fechaFinal,
            MaeEntidaddet moneda, Integer estado) {
        List<CrdCredito> lista = null;
        boolean filtroMoneda = true;
        if (moneda == null) filtroMoneda = false;
        String sql = "select c from CrdCredito c "
                + "where c.flagEstaCrd<>0 "
                + "and c.fechAproCrd BETWEEN :fechaInicio AND :fechaFinal ";
        if (filtroMoneda){
                sql = sql + "and c.codiMoneCrd = :monedaId";        
        }
        sql = sql + " ORDER BY c.fechAproCrd, c.codiMoneCrd";
        Query q = em.createQuery(sql);
        q.setParameter("fechaInicio", fechaInicio);
        q.setParameter("fechaFinal", fechaFinal);
        if (filtroMoneda){
            q.setParameter("monedaId", moneda.getSecuEntiDet());
        }
        //q.setParameter("estado", estado);
        lista = q.getResultList();
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista;
    }

    public List<CrdCredito> findByFiltros2(Date fechaInicio, Date fechaFinal, MaeProducto producto, MaeEntidaddet moneda, Integer estado) {
        List<CrdCredito> lista = null;
        boolean filtroProducto = true;
        boolean filtroMoneda = true;
        if (producto == null) filtroProducto = false;
        if (moneda == null) filtroMoneda = false;
        String sql = "select c from CrdCredito c "
                + "where c.flagEstaCrd<>0 "
                + "and c.fechAproCrd BETWEEN :fechaInicio AND :fechaFinal ";
        if (filtroProducto){
                sql = sql + "and c.idenExpeTrm.idenSimuSim.idenProdPrd = :productoId ";        
        }
        if (filtroMoneda){
                sql = sql + "and c.codiMoneCrd = :monedaId ";        
        }
        sql = sql + " ORDER BY c.idenExpeTrm.idenSimuSim.idenProdPrd, c.fechAproCrd";
        Query q = em.createQuery(sql);
        q.setParameter("fechaInicio", fechaInicio);
        q.setParameter("fechaFinal", fechaFinal);
        if (filtroProducto){
            q.setParameter("productoId", producto.getIdenProdPrd());
        }
        if (filtroMoneda){
            q.setParameter("monedaId", moneda.getSecuEntiDet());
        }
        //q.setParameter("estado", estado);
        lista = q.getResultList();
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista;
    }

    public List<CrdCredito> findByFiltros3(Date fechaInicial, Date fechaFinal, MaeEntidaddet linea, MaeEntidaddet moneda, int estado) {
        List<CrdCredito> lista = null;
        boolean filtroLinea = true;
        boolean filtroMoneda = true;
        if (linea == null) filtroLinea = false;
        if (moneda == null) filtroMoneda = false;
        String sql = "select c from CrdCredito c "
                + "where c.flagEstaCrd<>0 "
                + "and c.fechAproCrd BETWEEN :fechaInicial AND :fechaFinal ";
        if (filtroLinea){
                sql = sql + "and c.idenExpeTrm.idenSimuSim.idenProdPrd.codiLinePrd = :lineaId ";        
        }
        if (filtroMoneda){
                sql = sql + "and c.codiMoneCrd = :monedaId";        
        }
        sql = sql + " ORDER BY c.idenExpeTrm.idenSimuSim.idenProdPrd.codiLinePrd, c.codiMoneCrd";
        Query q = em.createQuery(sql);
        q.setParameter("fechaInicial", fechaInicial);
        q.setParameter("fechaFinal", fechaFinal);
        if (filtroLinea){
            q.setParameter("lineaId", linea.getSecuEntiDet());
        }
        if (filtroMoneda){
            q.setParameter("monedaId", moneda.getSecuEntiDet());
        }
        //q.setParameter("estado", estado);
        lista = q.getResultList();
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista;
    }
}
