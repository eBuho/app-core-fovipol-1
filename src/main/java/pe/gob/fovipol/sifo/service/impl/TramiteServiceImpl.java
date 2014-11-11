package pe.gob.fovipol.sifo.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
import javax.persistence.PersistenceException;
import pe.gob.fovipol.sifo.bean.sesion.SesionUsuario;
import pe.gob.fovipol.sifo.dao.MaeInmuebleFacade;
import pe.gob.fovipol.sifo.dao.MaeProcesoFacade;
import pe.gob.fovipol.sifo.dao.MaeProcesoestadoFacade;
import pe.gob.fovipol.sifo.dao.TrmDocumentoFacade;
import pe.gob.fovipol.sifo.dao.TrmEstatramHisFacade;
import pe.gob.fovipol.sifo.dao.TrmMovimientoFacade;
import pe.gob.fovipol.sifo.dao.TrmTramiteFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCanalcobraFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoFacade;
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobra;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.maestros.MaeArea;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.model.maestros.MaeProcesoestado;
import pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmEstatramHis;
import pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.service.TramiteService;
import pe.gob.fovipol.sifo.util.Constantes;

@Stateless
public class TramiteServiceImpl implements TramiteService {

    @EJB
    TrmMovimientoFacade ejbMovimientoFacade;
    @EJB
    CrdCreditoFacade ejbCreditoFacade;
    @EJB
    CrdCanalcobraFacade ejbCanalFacade;
    @EJB
    TrmTramiteFacade ejbTramiteFacade;
    @EJB
    MaeProcesoFacade ejbProcesoFacade;
    @EJB
    MaeProcesoestadoFacade ejbProcesoEstadoFacade;
    @EJB
    MaeInmuebleFacade ejbInmuebleFacade;
    @EJB
    TrmEstatramHisFacade ejbEstadoFacade;
    @EJB
    TrmDocumentoFacade ejbDocumentoFacade;
    @ManagedProperty(value="#{sesionUsuario}")
    private SesionUsuario sesionUsuario;

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
                //Area Inicial
                movimientoNuevo.setAreaOrigMvm(new MaeArea(BigDecimal.ONE));
            } else {
                movimientoNuevo = new TrmMovimiento(new TrmMovimientoPK(tramite.getIdenExpeTrm().toBigInteger(), movimiento.getTrmMovimientoPK().getSecuMoviMvm() + 1));
                siguiente = ejbProcesoFacade.buscarSiguienteProceso(movimiento.getIdenProcPrc());
                movimientoNuevo.setAreaOrigMvm(movimiento.getAreaDestMvm());
            }
            if (siguiente != null) {
                if(movimiento!=null){
                    movimiento.setFechEnviMvm(new Date());
                    if(sesionUsuario!=null)
                        movimiento.setUsuaEnviMvm(sesionUsuario.getUsuario().getCodiUsuaUsr());
                    ejbMovimientoFacade.edit(movimiento);
                    MaeProcesoestado estado=ejbProcesoEstadoFacade.findByProcesoSecuencia(movimiento.getIdenProcPrc());
                    cambiarEstadoExpediente(tramite, estado.getNombEstaPre());
                }
                movimientoNuevo.setFlagSituMvm(Constantes.VALOR_ESTADO_ACTIVO);
                movimientoNuevo.setTrmTramite(tramite);
                movimientoNuevo.setIdenProcPrc(siguiente);
                movimientoNuevo.setAreaDestMvm(siguiente.getCodiAreaAre());
                movimientoNuevo.setFechReceMvm(new Date());
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
            estado.setFlagEstaHis(Constantes.VALOR_ESTADO_ACTIVO);
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
            tramite.setIdenSimuSim(null);
            if (tramite.getIdenExpeTrm() == null) {
                tramite.setIdenExpeTrm(ejbTramiteFacade.obtenerCorrelativo());
                tramite.setFechCreaAud(new Date());
                tramite.setFlagEstaTrm(Constantes.VALOR_ESTADO_ACTIVO);
            } else {
                crea = false;
                tramite.setFechModiAud(new Date());
            }
            ejbTramiteFacade.edit(tramite);
            for (TrmDocumento doc : documentos) {
                doc.getTrmDocumentoPK().setIdenExpeTrm(tramite.getIdenExpeTrm().toBigInteger());
                if (!crea) {
                    doc.setFechModiAud(new Date());
                    ejbDocumentoFacade.edit(doc);
                }
                else{
                    doc.setFechEmisDoc(new Date());
                    ejbDocumentoFacade.create(doc);
                }                
            }
            if (crea) {
                generarMovimiento(tramite);                
            }
            return true;
        } 
        catch(PersistenceException  sicve){
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean registrarExpedienteCredito(TrmTramite tramite, List<TrmDocumento> documentos, CrdCredito credito, List<CrdCanalcobra> canales) {
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
                    ejbDocumentoFacade.edit(doc);
                }
                else{
                    doc.setFechEmisDoc(new Date());
                    ejbDocumentoFacade.create(doc);
                }                
            }
            if (crea) {                
                generarMovimiento(tramite);
                credito.getIdenInmuImb().setIdenInmuImb(ejbInmuebleFacade.obtenerCorrelativo());
                credito.getIdenInmuImb().setFlagEstaImb(Constantes.VALOR_ESTADO_ACTIVO);
                credito.setIdenCredCrd(new BigDecimal(ejbCreditoFacade.count()+1));
                credito.setFechCreaAud(new Date());
                credito.setFlagEstaCrd(Constantes.VALOR_ESTADO_ACTIVO);
                ejbInmuebleFacade.edit(credito.getIdenInmuImb());
                ejbCreditoFacade.edit(credito);
                short i=1;
                for(CrdCanalcobra canal:canales){
                    if(canal.getImpoCobrCdc()!=null){
                        canal.getCrdCanalcobraPK().setSecuCanaCdc(i);
                        canal.getCrdCanalcobraPK().setIdenCredCrd(credito.getIdenCredCrd().toBigInteger());
                        canal.setFechModiAud(new Date());
                        ejbCanalFacade.edit(canal);
                        i++;
                    }                    
                }
            } 
            else{
                credito.setFechModiAud(new Date());
                ejbInmuebleFacade.edit(credito.getIdenInmuImb());
                ejbCreditoFacade.edit(credito);
                for(CrdCanalcobra canal:canales){
                    canal.setFechModiAud(new Date());
                    ejbCanalFacade.edit(canal);
                }
            }            
            return true;
        } 
        catch(PersistenceException  sicve){
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * @return the sesionUsuario
     */
    public SesionUsuario getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * @param sesionUsuario the sesionUsuario to set
     */
    public void setSesionUsuario(SesionUsuario sesionUsuario) {
        this.sesionUsuario = sesionUsuario;
    }

}
