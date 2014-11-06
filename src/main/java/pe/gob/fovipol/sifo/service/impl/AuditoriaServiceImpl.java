
package pe.gob.fovipol.sifo.service.impl;

import javax.ejb.Stateless;
import pe.gob.fovipol.sifo.service.AuditoriaService;
import pe.gob.fovipol.sifo.util.Auditoria;

@Stateless
public class AuditoriaServiceImpl implements AuditoriaService{

    @Override
    public String obtenerIP() {
        return Auditoria.obtenerIP();
    }

    @Override
    public String obtenerEquipo() {
        return Auditoria.obtenerEquipo();
    }
    
}
