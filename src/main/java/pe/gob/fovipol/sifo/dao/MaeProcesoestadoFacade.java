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
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.model.maestros.MaeProcesoestado;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author probook
 */
@Stateless
public class MaeProcesoestadoFacade extends AbstractFacade<MaeProcesoestado> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeProcesoestadoFacade() {
        super(MaeProcesoestado.class);
    }
    
    public List<MaeProcesoestado> findByProceso(MaeProceso padre) {
        List<MaeProcesoestado> lista = null;
        String sql = "select d from MaeProcesoestado d where d.maeProcesoestadoPK.idenProcPrc=:padre and "
                + "d.flagEstaPrc<>"+Constantes.VALOR_ESTADO_INACTIVO;
        Query q = em.createQuery(sql);
        q.setParameter("padre", padre.getCodiProcPrc().toBigInteger());
        lista = q.getResultList();
        return lista;
    }
    
    public MaeProcesoestado findByProcesoSecuencia(MaeProceso padre) {
        List<MaeProcesoestado> lista = null;
        String sql = "select d from MaeProcesoestado d where d.maeProcesoestadoPK.idenProcPrc=:padre and "
                + "d.maeProcesoestadoPK.secuEstaPre=:secuencia";
        Query q = em.createQuery(sql);
        q.setParameter("padre", padre.getCodiPropPrc().getCodiProcPrc().toBigInteger());
        q.setParameter("secuencia",padre.getSecuEstaPrc());
        lista = q.getResultList();
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista.get(0);
    }
}
