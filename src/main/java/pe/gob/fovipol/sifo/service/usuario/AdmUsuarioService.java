/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.service.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author eBuho
 */
@Service("admUsuarioService")
public class AdmUsuarioService implements UserDetailsService{
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        return null;
    }
    
    
}
