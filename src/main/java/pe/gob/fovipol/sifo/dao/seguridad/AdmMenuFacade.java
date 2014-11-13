/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.seguridad;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenu;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author eBuho
 */
@Stateless
public class AdmMenuFacade extends AbstractFacade<AdmMenu> {

    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdmMenuFacade() {
        super(AdmMenu.class);
    }

    public List<AdmMenu> listarPorModuloExcepcion(BigDecimal idModulo, BigDecimal idUsuario){
        List<AdmMenu> lista;
        String sql = "SELECT m.* FROM ADM_MENU m,ADM_MENU_MODULO mm WHERE mm.IDEN_MODU_MOD=? "
                + "AND mm.IDEN_MENU_MNU=m.IDEN_MENU_MNU AND mm.FLAG_ESTA_MMD<>0 AND m.FLAG_ESTA_MNU<>0 "
                + "ORDER BY M.IDEN_MENU_MNU";           
        Query q = em.createNativeQuery(sql,AdmMenu.class);
        //q.setParameter("idUsuario", idUsuario);
        q.setParameter(1, idModulo);
        lista = q.getResultList();
        return lista;
    }
    
    public List<AdmMenu> findByModulo(AdmModulo modulo) {
        List<AdmMenu> lista = null;
        String sql = "select d.idenMenuMnu from AdmMenuModulo d where d.idenModuMod=:modulo AND d.flagEstaMmd<>"+Constantes.VALOR_ESTADO_INACTIVO;
        Query q = em.createQuery(sql);
        q.setParameter("modulo", modulo);
        lista = q.getResultList();
        return lista;
    }
    
    public List<AdmMenu> findDisponibleByModulo(AdmModulo modulo) {
        List<AdmMenu> lista = null;
        String sql = "select d from AdmMenu d where d.idenMenuMnu not in ( "
                + "SELECT e.idenMenuMnu.idenMenuMnu from AdmMenuModulo e WHERE e.idenModuMod=:modulo"
                + " and e.flagEstaMmd<>"+Constantes.VALOR_ESTADO_INACTIVO+")";
        Query q = em.createQuery(sql);
        q.setParameter("modulo", modulo);
        lista = q.getResultList();
        return lista;
    }
}
