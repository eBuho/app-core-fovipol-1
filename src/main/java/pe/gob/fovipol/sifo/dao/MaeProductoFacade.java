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
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author probook
 */
@Stateless
public class MaeProductoFacade extends AbstractFacade<MaeProducto> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeProductoFacade() {
        super(MaeProducto.class);
    }
    
    public List<MaeProducto> findByLinea(int linea) {
        List<MaeProducto> lista = null;
        String sql = "select d from MaeProducto d where d.codiLinePrd=:linea and d.flagEstaPrd<>"+Constantes.VALOR_ESTADO_INACTIVO;
        Query q = em.createQuery(sql);
        q.setParameter("linea", linea);
        lista = q.getResultList();
        return lista;
    }
}
