/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.seguridad;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenu;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenuModulo;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author eBuho
 */
@Stateless
public class AdmMenuModuloFacade extends AbstractFacade<AdmMenuModulo> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdmMenuModuloFacade() {
        super(AdmMenuModulo.class);
    }
    
    public void updateByModuloMenu(AdmModulo modulo,BigDecimal menu) {
        String sql = "UPDATE AdmMenuModulo d SET d.flagEstaMmd="+ Constantes.VALOR_ESTADO_INACTIVO 
                + " where d.idenMenuMnu.idenMenuMnu =:menu and d.idenModuMod=:modulo";
        Query q = em.createQuery(sql);
        q.setParameter("modulo", modulo);
        q.setParameter("menu", menu);
        q.executeUpdate();
    }
}
