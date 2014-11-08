/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao.seguridad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;

/**
 *
 * @author eBuho
 */
@Stateless
public class AdmUsuarioFacade extends AbstractFacade<AdmUsuario> {

    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdmUsuarioFacade() {
        super(AdmUsuario.class);
    }

    public AdmUsuario findByUsername(String username) {
        AdmUsuario usuario = null;
        String sql = "FROM AdmUsuario a WHERE a.admUsuario.codiUsuaUsr = :username";
        Query q = em.createQuery(sql);
        q.setParameter("username", "%" + username + "%");
        usuario = (AdmUsuario) q.getSingleResult();
        return usuario;
    }
}
