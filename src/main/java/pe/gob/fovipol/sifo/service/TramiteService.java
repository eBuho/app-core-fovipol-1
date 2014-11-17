/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.service;

import java.util.List;
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobra;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author probook
 */
public interface TramiteService {
    public boolean darViabilidadExpediente(TrmTramite tramite,List<TrmDocumento> documentos);
    public boolean generarMovimiento(TrmTramite tramite);
    public boolean cambiarEstadoExpediente(TrmTramite tramite,String nombreEstado);
    public boolean registrarExpediente(TrmTramite tramite,List<TrmDocumento> documentos);
    public boolean registrarExpedienteCredito(TrmTramite tramite,List<TrmDocumento> documentos,CrdCredito credito,
            List<CrdCanalcobra> canales);
}
