/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.service.usuario;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pe.gob.fovipol.sifo.dao.seguridad.AdmUsuarioFacade;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;

/**
 *
 * @author eBuho
 */
public class AdmUsuarioService implements UserDetailsService{
    @Autowired
    private AdmUsuarioFacade admUsuarioFacade; 
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdmUsuario user = admUsuarioFacade.findByUsername(username);
        return user;
    }

}
