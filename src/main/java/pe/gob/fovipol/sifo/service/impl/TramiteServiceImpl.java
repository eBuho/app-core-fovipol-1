package pe.gob.fovipol.sifo.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pe.gob.fovipol.sifo.dao.MaeProcesoFacade;
import pe.gob.fovipol.sifo.dao.TrmDocumentoFacade;
import pe.gob.fovipol.sifo.dao.TrmEstatramHisFacade;
import pe.gob.fovipol.sifo.dao.TrmMovimientoFacade;
import pe.gob.fovipol.sifo.dao.TrmTramiteFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeArea;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmEstatramHis;
import pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.service.TramiteService;

@Stateless
public class TramiteServiceImpl implements TramiteService {

    @EJB
    TrmMovimientoFacade ejbMovimientoFacade;
    @EJB
    TrmTramiteFacade ejbTramiteFacade;
    @EJB
    MaeProcesoFacade ejbProcesoFacade;
    @EJB
    TrmEstatramHisFacade ejbEstadoFacade;
    @EJB
    private TrmDocumentoFacade ejbDocumentoFacade;

    @Override
    public boolean darViabilidadExpediente(TrmTramite tramite, List<TrmDocumento> documentos) {
        boolean validar = true;
        for (TrmDocumento trm : documentos) {
            if (trm.getFlagFisiDoc() == null || trm.getFlagFisiDoc() == 'N') {
                validar = false;
            }
        }
        if (validar) {
            validar = generarMovimiento(tramite);
            return validar;
        } else {
            return false;
        }
    }

    @Override
    public boolean generarMovimiento(TrmTramite tramite) {
        try {
            TrmMovimiento movimiento = ejbMovimientoFacade.buscarUltimoMovimiento(tramite);
            TrmMovimiento movimientoNuevo;
            MaeProceso siguiente;
            if (movimiento == null) {
                movimientoNuevo = new TrmMovimiento(new TrmMovimientoPK(tramite.getIdenExpeTrm().toBigInteger(), 1));
                siguiente = ejbProcesoFacade.buscarSiguienteProceso(tramite.getMaeProceso());
                movimientoNuevo.setAreaOrigMvm(new MaeArea(BigDecimal.ONE));
            } else {
                movimientoNuevo = new TrmMovimiento(new TrmMovimientoPK(tramite.getIdenExpeTrm().toBigInteger(), movimiento.getTrmMovimientoPK().getSecuMoviMvm() + 1));
                siguiente = ejbProcesoFacade.buscarSiguienteProceso(movimiento.getIdenProcPrc());
                movimientoNuevo.setAreaOrigMvm(movimiento.getAreaDestMvm());
            }
            if (siguiente != null) {
                movimientoNuevo.setFechCreaAud(new Date());
                movimientoNuevo.setFlagSituMvm(new Short("1"));
                movimientoNuevo.setTrmTramite(tramite);
                movimientoNuevo.setIdenProcPrc(siguiente);
                movimientoNuevo.setAreaDestMvm(siguiente.getCodiAreaAre());
                ejbMovimientoFacade.create(movimientoNuevo);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean cambiarEstadoExpediente(TrmTramite tramite, String nombreEstado) {
        try {
            int idEstado = ejbEstadoFacade.obtenerCorrelativo(tramite);
            TrmEstatramHis estado = new TrmEstatramHis(new TrmEstatramHisPK(tramite.getIdenExpeTrm().toBigInteger(), idEstado));
            estado.setFlagEstaHis(new Short("1"));
            estado.setFechCreaAud(new Date());
            estado.setCodiEstaHis(nombreEstado);
            ejbEstadoFacade.create(estado);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean registrarExpediente(TrmTramite tramite, List<TrmDocumento> documentos) {
        try {
            boolean crea = true;
            if (tramite.getIdenExpeTrm() == null) {
                tramite.setIdenExpeTrm(ejbTramiteFacade.obtenerCorrelativo());
                tramite.setFechCreaAud(new Date());
                tramite.setFlagEstaTrm(new Short("1"));
            } else {
                crea = false;
                tramite.setFechModiAud(new Date());
            }
            ejbTramiteFacade.edit(tramite);
            for (TrmDocumento doc : documentos) {
                doc.getTrmDocumentoPK().setIdenExpeTrm(tramite.getIdenExpeTrm().toBigInteger());
                if (!crea) {
                    doc.setFechModiAud(new Date());
                }
                ejbDocumentoFacade.edit(doc);
            }
            if (crea) {
                generarMovimiento(tramite);
                cambiarEstadoExpediente(tramite, "REGISTRADO");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
