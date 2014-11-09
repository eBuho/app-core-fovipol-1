/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.service.usuario;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.fovipol.sifo.service.UsuarioServiceImpl;
import java.io.Serializable;

/**
 *
 * @author eBuho
 */
@Service("admUsuarioService")
public class AdmUsuarioService implements UserDetailsService,Serializable {
    @Autowired
    UsuarioServiceImpl usuarioService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AdmUsuario usuario = usuarioService.findByUsername(username);
            if (usuario != null) {
                String password = usuario.getClavUsuaUsr();
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (AdmUsuarioRol rol : usuario.getAdmUsuarioRolList()) {
                    authorities.add(new SimpleGrantedAuthority(rol.getIdenRoleRol().getNombRoleRol()));
                }
                User securityUser = new User(username, password, true, true,
                        true, true, authorities);
                return securityUser;
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
