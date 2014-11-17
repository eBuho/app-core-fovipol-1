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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author ebuho
 */
@Stateless
public class TrmMovimientoFacade extends AbstractFacade<TrmMovimiento> {

    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrmMovimientoFacade() {
        super(TrmMovimiento.class);
    }

    public List<TrmMovimiento> findItemsActivos(BigDecimal idArea) {
        String sql="SELECT * FROM TRM_MOVIMIENTO N  WHERE N.SECU_MOVI_MVM =(SELECT MAX(M.SECU_MOVI_MVM)"
                + " FROM TRM_MOVIMIENTO M WHERE M.FLAG_SITU_MVM<>"+Constantes.VALOR_ESTADO_INACTIVO
                + " AND N.IDEN_EXPE_TRM=M.IDEN_EXPE_TRM) AND N.AREA_DEST_MVM=? "
                + " ORDER BY N.FECH_RECE_MVM";
        Query q = em.createNativeQuery(sql,TrmMovimiento.class);
        q.setParameter(1, idArea); 
        return q.getResultList();
    }

    public List<TrmMovimiento> findItemsHistoricos() {
        String sql = "select m from TrmMovimiento m "
                + "where m.flagSituMvm = 0 order by m.fechReceMvm desc";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    public TrmMovimiento buscarUltimoMovimiento(TrmTramite tramite) {
        Query q = em.createQuery("SELECT a FROM  TrmMovimiento a WHERE a.trmMovimientoPK.idenExpeTrm=:idenExpeTrm AND "
                + "a.trmMovimientoPK.secuMoviMvm=(SELECT MAX(b.trmMovimientoPK.secuMoviMvm) FROM TrmMovimiento b WHERE b.trmMovimientoPK.idenExpeTrm=:idenExpeTrm)");
        q.setParameter("idenExpeTrm", tramite.getIdenExpeTrm());
        TrmMovimiento id = null;
        try {
            id = (TrmMovimiento) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return id;
    }
}
