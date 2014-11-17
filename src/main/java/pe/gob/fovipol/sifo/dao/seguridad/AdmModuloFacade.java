/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.seguridad;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;

/**
 *
 * @author eBuho
 */
@Stateless
public class AdmModuloFacade extends AbstractFacade<AdmModulo> {

    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdmModuloFacade() {
        super(AdmModulo.class);
    }

    public List<AdmModulo> findByUsuario(AdmUsuario usuario) {
        List<AdmModulo> lista = null;
        String sql = "SELECT m.* FROM ADM_USUARIO u,ADM_ROL r,ADM_USUARIO_ROL ur,ADM_MODULO_ROL mr,"
                + "ADM_MODULO m WHERE u.IDEN_PERS_PER=? AND u.FLAG_ESTA_USR<>0 AND r.FLAG_ESTA_ROL<>0 "
                + "AND ur.FLAG_ESTA_URO<>0 AND mr.FLAG_ESTA_MDR<>0"
                + "AND m.FLAG_ESTA_MOD<>0"
                + " AND ur.IDEN_USUA_URO=u.IDEN_PERS_PER AND ur.IDEN_ROLE_ROL=r.IDEN_ROLE_ROL"
                + " AND r.IDEN_ROLE_ROL=mr.IDEN_ROLE_ROL AND mr.IDEN_MODU_MOD=m.IDEN_MODU_MOD";        
        Query q = em.createNativeQuery(sql,AdmModulo.class);
        q.setParameter(1, usuario.getIdenPersPer());
        lista = q.getResultList();
        return lista;
    }
}
