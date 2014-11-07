package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pe.gob.fovipol.sifo.dao.CrdSimulacionFacade;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeInmuebleFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.dao.MaeRequisitoFacade;
import pe.gob.fovipol.sifo.dao.TrmDocumentoFacade;
import pe.gob.fovipol.sifo.dao.TrmTramiteFacade;
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobra;
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobraPK;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeInmueble;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeRequisito;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.service.CreditoService;
import pe.gob.fovipol.sifo.service.TramiteService;
import pe.gob.fovipol.sifo.util.Constantes;

@ManagedBean(name = "registrarExpedienteController")
@ViewScoped
public class RegistrarExpedienteController implements Serializable {

    @EJB
    private TrmTramiteFacade ejbTramiteFacade;
    @EJB
    private TrmDocumentoFacade ejbDocumentoFacade;
    @EJB
    private MaeEntidaddetFacade ejbEntidadDetalleFacade;
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @EJB
    private MaeRequisitoFacade ejbRequisitoFacade;
    @EJB
    private CrdSimulacionFacade ejbSimulacionFacade;
    @EJB
    private MaeInmuebleFacade ejbInmuebleFacade;
    @EJB
    private CreditoService creditoService;
    @EJB
    private TramiteService tramiteService;
    private MaeSocio socio;
    private TrmTramite tramite;
    private List<MaeProducto> productos;
    private List<MaeEntidaddet> tiposTramite;
    private List<MaeEntidaddet> tiposPrioridad;
    private List<MaeEntidaddet> modalidadesTramite;
    private List<MaeEntidaddet> gradosParentesco;
    private List<MaeEntidaddet> canalesCobranza;
    private List<TrmDocumento> documentos;
    private List<CrdSimulacion> simulaciones;
    private MaeProducto producto;
    private BigDecimal simulacion;
    private MaeInmueble inmueble;
    private MaePersona pareja;
    private int edad;
    private boolean beneficiaria;
    private List<CrdCanalcobra> canales;
    private List<CrdCanalcobra> canalesSeleccionados;

    @PostConstruct
    public void init() {
        String idTramite = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("idTramite");
        if (idTramite != null && !idTramite.trim().equals("")) {
            tramite = ejbTramiteFacade.find(new BigDecimal(idTramite));
            if (tramite == null) {
                tramite = new TrmTramite();
                tramite.setIdenSimuSim(new CrdSimulacion());
            } else {
                setSocio(tramite.getCodiPersTrm().getMaeSocio());
                if (tramite.getIdenSimuSim() != null) {
                    simulacion = tramite.getIdenSimuSim().getIdenSimuSim();
                    producto = tramite.getIdenSimuSim().getIdenProdPrd();
                    cargarRequisitos();
                }
            }
        } else {
            tramite = new TrmTramite();
            tramite.setIdenSimuSim(new CrdSimulacion());
        }
        productos = ejbProductoFacade.findAll();
        inmueble = new MaeInmueble();
        gradosParentesco = ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_GRADO_PARENTESCO));
        canalesCobranza=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_CANAL_COBRANZA));
        canales=new ArrayList<>();
        short i=1;
        for(MaeEntidaddet aux:canalesCobranza){
            CrdCanalcobra c=new CrdCanalcobra();
            c.setCrdCanalcobraPK(new CrdCanalcobraPK());
            c.getCrdCanalcobraPK().setSecuCanaCdc(i);
            c.setCodiCanaCob(aux.getSecuEntiDet());
            c.setFlagEstaCdc(new Short("1"));
            i++;
            canales.add(c);
        }
        beneficiaria = false;
    }

    public void nuevoTramite() {
        tramite = new TrmTramite();
        tramite.setIdenSimuSim(new CrdSimulacion());
        socio = null;
    }

    public void mostrarSimulacion() {
        tramite.setIdenSimuSim(ejbSimulacionFacade.find(simulacion));
    }

    public void cargarRequisitos() {
        if (producto != null) {
            if (socio != null) {
                simulaciones = ejbSimulacionFacade.findBySocioProducto(socio.getCodiPersPer(), producto.getIdenProdPrd());
            }
            if (tramite.getIdenExpeTrm()!= null) {
                documentos=ejbDocumentoFacade.findByTramite(tramite);
            } else {
                List<MaeRequisito> reqs = ejbRequisitoFacade.findByProcesoActivo(producto.getIdenProcPrc().getCodiProcPrc());
                documentos = new ArrayList<>();
                int i = 1;
                for (MaeRequisito aux : reqs) {
                    TrmDocumento doc = new TrmDocumento();
                    doc.setTrmDocumentoPK(new TrmDocumentoPK());
                    doc.getTrmDocumentoPK().setSecuDocuDoc(i);
                    doc.setMaeRequisito(aux);
                    i++;
                    doc.setDescNombDoc(aux.getNombRequReq());
                    doc.setFlagEstaDoc(new Short("1"));
                    documentos.add(doc);
                }
            }
        }
    }
    
    public void darViabilidad(){        
        boolean validarCampos=tramiteService.darViabilidadExpediente(tramite, documentos);        
        if(validarCampos)
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Se hizo el movimiento con éxito", ""));        
        else
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "No tiene todos los requisitos", ""));
    }
    
    public void rechazar(){
        boolean rechazar=tramiteService.cambiarEstadoExpediente(tramite, "RECHAZADO");
        if(rechazar)
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Expediente Rechazado", ""));        
        else
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo rechazar el Expediente", ""));
    }
    public void registrar() {
        if (socio == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione Socio", ""));
            return;
        }
        if (tramite.getNombTramTrm() == null || tramite.getNombTramTrm().trim().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese nombre de Tramitante", ""));
            return;
        }
        if (tramite.getNumeFolioTrm() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese el número de folios", ""));
            return;
        }
        if (tramite.getNumeFolioTrm().compareTo(BigInteger.ZERO) != 1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de folios debe ser Mayor que 0", ""));
            return;
        }
        if (tramite.getDescAsunTrm() == null || tramite.getDescAsunTrm().trim().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese el Asunto del Expediente", ""));
            return;
        }               
        tramite.setCodiPersTrm(socio.getMaePersona());
        tramite.setMaeProceso(producto.getIdenProcPrc());
        boolean crea=tramiteService.registrarExpediente(tramite, documentos);
        if(crea)
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Trámite grabado con Éxito", ""));
        else
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo grabar el Trámite", ""));
        /*inmueble.setIdenInmuImb(ejbInmuebleFacade.obtenerCorrelativo());
         inmueble.setFlagEstaImb(new Short("1"));
         ejbInmuebleFacade.create(inmueble);*/
    }

    /**
     * @return the socio
     */
    public MaeSocio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(MaeSocio socio) {
        this.socio = socio;
        if (this.socio != null) {
            edad = creditoService.calcularEdad(socio.getMaePersona().getFechNaciPer(), new Date());
            if (this.socio.getMaePersona().getCodiPerpPer() != null) {
                MaePersona aux = this.socio.getMaePersona().getCodiPerpPer();
                if (aux.getFechFallPer() != null) {
                    if (this.socio.getMaePersona().getGradParePer() == 1) {
                        beneficiaria = true;
                        pareja = this.socio.getMaePersona().getCodiPerpPer();
                        edad = creditoService.calcularEdad(pareja.getFechNaciPer(), new Date());
                    }
                } else {
                    beneficiaria = false;
                }
            } else {
                beneficiaria = false;
            }

        }
    }

    /**
     * @return the tramite
     */
    public TrmTramite getTramite() {
        return tramite;
    }

    /**
     * @param tramite the tramite to set
     */
    public void setTramite(TrmTramite tramite) {
        this.tramite = tramite;
    }

    /**
     * @return the tiposTramite
     */
    public List<MaeEntidaddet> getTiposTramite() {
        if (tiposTramite == null) {
            tiposTramite = ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_TIPO_TRAMITE));
        }
        return tiposTramite;
    }

    /**
     * @param tiposTramite the tiposTramite to set
     */
    public void setTiposTramite(List<MaeEntidaddet> tiposTramite) {
        this.tiposTramite = tiposTramite;
    }

    /**
     * @return the modalidadesTramite
     */
    public List<MaeEntidaddet> getModalidadesTramite() {
        if (modalidadesTramite == null) {
            modalidadesTramite = ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_MODALIDAD_TRAMITE));
        }
        return modalidadesTramite;
    }

    /**
     * @param modalidadesTramite the modalidadesTramite to set
     */
    public void setModalidadesTramite(List<MaeEntidaddet> modalidadesTramite) {
        this.modalidadesTramite = modalidadesTramite;
    }

    /**
     * @return the tiposPrioridad
     */
    public List<MaeEntidaddet> getTiposPrioridad() {
        if (tiposPrioridad == null) {
            tiposPrioridad = ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_PRIORIDAD_TRAMITE));
        }
        return tiposPrioridad;
    }

    /**
     * @param tiposPrioridad the tiposPrioridad to set
     */
    public void setTiposPrioridad(List<MaeEntidaddet> tiposPrioridad) {
        this.tiposPrioridad = tiposPrioridad;
    }

    /**
     * @return the procesos
     */
    public List<MaeProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<MaeProducto> productos) {
        this.productos = productos;
    }

    /**
     * @return the producto
     */
    public MaeProducto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(MaeProducto producto) {
        this.producto = producto;
    }

    /**
     * @return the documentos
     */
    public List<TrmDocumento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<TrmDocumento> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the simulaciones
     */
    public List<CrdSimulacion> getSimulaciones() {
        return simulaciones;
    }

    /**
     * @param simulaciones the simulaciones to set
     */
    public void setSimulaciones(List<CrdSimulacion> simulaciones) {
        this.simulaciones = simulaciones;
    }

    /**
     * @return the simulacion
     */
    public BigDecimal getSimulacion() {
        return simulacion;
    }

    /**
     * @param simulacion the simulacion to set
     */
    public void setSimulacion(BigDecimal simulacion) {
        this.simulacion = simulacion;
    }

    /**
     * @return the inmueble
     */
    public MaeInmueble getInmueble() {
        return inmueble;
    }

    /**
     * @param inmueble the inmueble to set
     */
    public void setInmueble(MaeInmueble inmueble) {
        this.inmueble = inmueble;
    }

    /**
     * @return the pareja
     */
    public MaePersona getPareja() {
        return pareja;
    }

    /**
     * @param pareja the pareja to set
     */
    public void setPareja(MaePersona pareja) {
        this.pareja = pareja;
    }

    /**
     * @return the gradosParentesco
     */
    public List<MaeEntidaddet> getGradosParentesco() {
        return gradosParentesco;
    }

    /**
     * @param gradosParentesco the gradosParentesco to set
     */
    public void setGradosParentesco(List<MaeEntidaddet> gradosParentesco) {
        this.gradosParentesco = gradosParentesco;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the beneficiaria
     */
    public boolean isBeneficiaria() {
        return beneficiaria;
    }

    /**
     * @param beneficiaria the beneficiaria to set
     */
    public void setBeneficiaria(boolean beneficiaria) {
        this.beneficiaria = beneficiaria;
    }

    /**
     * @return the canalesCobranza
     */
    public List<MaeEntidaddet> getCanalesCobranza() {
        return canalesCobranza;
    }

    /**
     * @param canalesCobranza the canalesCobranza to set
     */
    public void setCanalesCobranza(List<MaeEntidaddet> canalesCobranza) {
        this.canalesCobranza = canalesCobranza;
    }

    /**
     * @return the canales
     */
    public List<CrdCanalcobra> getCanales() {
        return canales;
    }

    /**
     * @param canales the canales to set
     */
    public void setCanales(List<CrdCanalcobra> canales) {
        this.canales = canales;
    }

    /**
     * @return the canalesSeleccionados
     */
    public List<CrdCanalcobra> getCanalesSeleccionados() {
        return canalesSeleccionados;
    }

    /**
     * @param canalesSeleccionados the canalesSeleccionados to set
     */
    public void setCanalesSeleccionados(List<CrdCanalcobra> canalesSeleccionados) {
        this.canalesSeleccionados = canalesSeleccionados;
    }

    
}
