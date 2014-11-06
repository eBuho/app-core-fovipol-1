package pe.gob.fovipol.sifo.service.impl;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pe.gob.fovipol.sifo.dao.TrmMovimientoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.service.TramiteService;

@Stateless
public class TramiteServiceImpl implements TramiteService {
    @EJB
    TrmMovimientoFacade ejbMovimientoFacade;
    @Override
    public boolean darViabilidadExpediente(TrmTramite tramite, List<TrmDocumento> documentos) {
        boolean validar=true;
        for(TrmDocumento trm:documentos){
            if(trm.getFlagFisiDoc()==null || trm.getFlagFisiDoc()=='N')
                validar=false;
        }
        if(validar){
            generarMovimiento(tramite);            
            return true;
        }
        else
            return false;
    }
    
    public void generarMovimiento(TrmTramite tramite){
        TrmMovimiento movimiento=ejbMovimientoFacade.buscarUltimoMovimiento(tramite);
        if(movimiento==null){
            movimiento=new TrmMovimiento(new TrmMovimientoPK(tramite.getIdenExpeTrm().toBigInteger(), 1));
            //movimiento.setIdenProcPrc(null);
            movimiento.setFechCreaAud(new Date());
            movimiento.setFlagSituMvm(new Short("1"));
            movimiento.setTrmTramite(tramite);
            //movimiento.set
        }
        else{
            
        }
    }
    
}
