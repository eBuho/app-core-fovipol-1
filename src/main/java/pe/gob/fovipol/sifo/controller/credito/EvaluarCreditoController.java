package pe.gob.fovipol.sifo.controller.credito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import pe.gob.fovipol.sifo.controller.util.Cuota;
import pe.gob.fovipol.sifo.dao.credito.CrdSimulacionFacade;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.dao.MaeRequisitoFacade;
import pe.gob.fovipol.sifo.dao.TrmDocumentoFacade;
import pe.gob.fovipol.sifo.dao.TrmTramiteFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCanalcobraFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoCuotaFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdSimulaSeguroFacade;
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobra;
import pe.gob.fovipol.sifo.model.credito.CrdCanalcobraPK;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.credito.CrdCreditoCuota;
import pe.gob.fovipol.sifo.model.credito.CrdSimulaSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeInmueble;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeRequisito;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.service.CreditoService;
import pe.gob.fovipol.sifo.service.TramiteService;
import pe.gob.fovipol.sifo.util.Constantes;

@ManagedBean(name = "evaluarCreditoController")
@ViewScoped
public class EvaluarCreditoController implements Serializable {

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
    private CrdCreditoFacade ejbCreditoService;
    @EJB
    private CreditoService creditoService;
    @EJB
    private CrdCanalcobraFacade ejbCanalFacade;
    @EJB
    private CrdCreditoCuotaFacade ejbCreditoCuotaFacade;
    @EJB
    private CrdSimulaSeguroFacade ejbSimulaSeguroFacade;
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
    private CrdCredito credito;
    private BigDecimal netoGirar;
    private String listaSeguros;
    private BigDecimal totalPago;
    private BigDecimal maximoDescuento;
    private List<Cuota> cuotas;
    private BigDecimal totalAmortizacion;
    private BigDecimal totalInteres;
    private BigDecimal totalSeguro;
    private BigDecimal totalCuota;
    private boolean esPrestamo;
    private boolean verCronograma;
    private String codigoPoliza;
    private BigDecimal montoPoliza;
    private boolean enOtraArea;
    private List<MaeSeguro> listaSeguro;
    private boolean tieneCuotas;
    @PostConstruct
    public void init() {
        esPrestamo = false;
        enOtraArea=false;
        tieneCuotas=false;
        String idTramite = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("idTramite");
        if (idTramite != null && !idTramite.trim().equals("")) {
            tramite = ejbTramiteFacade.find(new BigDecimal(idTramite));
            if (tramite != null) {
                verificarTipoTramite();
                if (esPrestamo) {
                    setSocio(tramite.getCodiPersTrm().getMaeSocio());
                    credito = ejbCreditoService.findByTramite(tramite);
                    if (credito == null) {
                        credito = new CrdCredito();
                        inmueble = new MaeInmueble();
                    } else {
                        inmueble = credito.getIdenInmuImb();
                        tieneCuotas();
                    }
                    if (tramite.getIdenSimuSim() != null) {
                        simulacion = tramite.getIdenSimuSim().getIdenSimuSim();
                        producto = tramite.getIdenSimuSim().getIdenProdPrd();
                        CrdSimulacion simu = tramite.getIdenSimuSim();
                        cargarSeguros();
                        netoGirar = simu.getImpoSoliSim().multiply(new BigDecimal(100).add(simu.getTasaGadmSim().negate())).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                        cargarRequisitos();
                        esPrestamo = true;                        
                    } else {
                        cargarRequisitos();
                    }
                } else {
                    tramite = null;
                }
            }
        } else {
            tramite = null;
        }
        productos = ejbProductoFacade.findAll();
        gradosParentesco = ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_GRADO_PARENTESCO));
        cargarCanalesCobranza();
        //beneficiaria = false;
        verCronograma=false;
    }
    
    public void tieneCuotas(){
        int numCuotas=ejbCreditoCuotaFacade.contarByCredito(credito);
        tieneCuotas = numCuotas != 0;
    }
    public void verSimulacion() {
        listaSeguro = new ArrayList<>();
        List<CrdSimulaSeguro> lista = ejbSimulaSeguroFacade.findBySimulacion(tramite.getIdenSimuSim());
        for (CrdSimulaSeguro simula : lista) {
            listaSeguro.add(simula.getIdenSeguSeg());
        }
        CrdSimulacion simu = tramite.getIdenSimuSim();
        cuotas = creditoService.calcularCuotas(listaSeguro, simu.getPeriCiclSim().intValue(),
                simu.getTasaTeaSim(), simu.getImpoSoliSim(),
                simu.getPlazPresSim(), simu.getImpoCuotSim(), socio.getMaePersona().getFechNaciPer(),tramite.getIdenSimuSim());        
        totalAmortizacion = cuotas.get(0).getTotalAmortizacion();
        totalCuota = cuotas.get(0).getTotalCuota();
        totalInteres = cuotas.get(0).getTotalInteres();
        totalSeguro = cuotas.get(0).getTotalSeguro();
        verCronograma=true;
    }

    public void cargarSeguros() {
        List<CrdSimulaSeguro> lista = ejbSimulaSeguroFacade.findBySimulacion(tramite.getIdenSimuSim());
        listaSeguros = "";
        for (CrdSimulaSeguro simula : lista) {
            if (listaSeguros.equals("")) {
                listaSeguros = listaSeguros + simula.getIdenSeguSeg().getDescNombSeg();
            } else {
                listaSeguros = listaSeguros + ", " + simula.getIdenSeguSeg().getDescNombSeg();
            }
        }
    }

    public void cargarCanalesCobranza() {
        if (tramite == null || tramite.getIdenExpeTrm() == null) {

            canalesCobranza = ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_CANAL_COBRANZA));
            canales = new ArrayList<>();
            short i = 1;
            for (MaeEntidaddet aux : canalesCobranza) {
                //if(aux.getValoNumuDet()!=null && aux.getValoNumuDet().compareTo(new BigDecimal(socio.getEntiPagoSoc()).toBigInteger())==0){
                CrdCanalcobra c = new CrdCanalcobra();
                c.setCrdCanalcobraPK(new CrdCanalcobraPK());
                c.getCrdCanalcobraPK().setSecuCanaCdc(i);
                c.setCodiCanaCob(aux.getSecuEntiDet());
                c.setFlagEstaCdc(Constantes.VALOR_ESTADO_ACTIVO);
                i++;
                canales.add(c);
                //}                    
            }

        } else {
            canales = ejbCanalFacade.findByCredito(credito);
            contarCanalCobranza();
        }
    }

    public void contarCanalCobranza() {
        totalPago = BigDecimal.ZERO;
        for (CrdCanalcobra ccobra : canales) {
            if (ccobra.getImpoCobrCdc() != null) {
                totalPago = totalPago.add(ccobra.getImpoCobrCdc());
            }
        }
    }

    public void nuevoTramite() {
        tramite = new TrmTramite();
        tramite.setIdenSimuSim(new CrdSimulacion());
        socio = null;
    }

    public void mostrarSimulacion() {
        tramite.setIdenSimuSim(ejbSimulacionFacade.find(simulacion));
        cargarSeguros();
        CrdSimulacion simu = tramite.getIdenSimuSim();
        netoGirar = simu.getImpoSoliSim().multiply(new BigDecimal(100).add(simu.getTasaGadmSim().negate())).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

    public void actualizarSimulaciones() {
        simulaciones = ejbSimulacionFacade.findBySocioProducto(socio.getCodiPersPer(), producto.getIdenProdPrd());
    }

    public void cargarRequisitos() {
        if (producto != null) {
            if (socio != null) {
                simulaciones = ejbSimulacionFacade.findBySocioProducto(socio.getCodiPersPer(), producto.getIdenProdPrd());
            }
            if (tramite.getIdenExpeTrm() != null) {
                documentos = ejbDocumentoFacade.findByTramite(tramite);
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
                    doc.setFlagEstaDoc(Constantes.VALOR_ESTADO_ACTIVO);
                    documentos.add(doc);
                }
            }
        } else {
            if (tramite.getIdenExpeTrm() != null) {
                documentos = ejbDocumentoFacade.findByTramite(tramite);
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
                    doc.setFlagEstaDoc(Constantes.VALOR_ESTADO_ACTIVO);
                    documentos.add(doc);
                }
            }
        }
    }

    public void darViabilidad() {
        enOtraArea = tramiteService.darViabilidadExpediente(tramite, documentos);
        if (enOtraArea) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se hizo el movimiento con éxito", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No tiene todos los requisitos", ""));
        }
    }

    public void rechazar() {
        boolean rechazar = tramiteService.cambiarEstadoExpediente(tramite, "RECHAZADO");
        if (rechazar) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Expediente Rechazado", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo rechazar el Expediente", ""));
        }
    }

    public void registrar() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (esPrestamo) {
            contarCanalCobranza();
            if (totalPago.compareTo(tramite.getIdenSimuSim().getImpoCuotSim()) != 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "La Suma de los aportes a los Canales de Cobranza deben ser igual a las cuota " + tramite.getIdenSimuSim().getImpoCuotSim(), ""));
                context.addCallbackParam("error", true);
                return;
            }
        }
        credito.setIdenInmuImb(inmueble);
        credito.setIdenExpeTrm(tramite);
        CrdSimulacion simu = tramite.getIdenSimuSim();
        credito.setPercSociCrd(simu.getIngrBrtoSim());
        credito.setNumeCuotPrd(simu.getPlazPresSim());
        credito.setTasaInteCrd(simu.getTasaTeaSim());
        credito.setTasaGadmCrd(simu.getTasaGadmSim());
        credito.setCodiMoneCrd(simu.getCodiMoneCrd());
        credito.setAutoCdobCrd(simu.getAutoCdobSim());
        credito.setPeriGracCrd(simu.getPeriGracSim());
        credito.setImpoSoliCrd(simu.getImpoSoliSim());
        boolean crea;        
        crea = tramiteService.registrarExpedienteCredito(tramite, documentos, credito, canales);
        verSimulacion();
        List<CrdCreditoCuota> listaCuotas=ejbCreditoCuotaFacade.findByCredito(credito);
        if(listaCuotas==null || listaCuotas.isEmpty()){
            crea= creditoService.generarCuotas(tramite, credito, cuotas,listaSeguro);            
        }
        if (crea) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuotas Generadas", ""));
            context.addCallbackParam("error", false);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo generar las Cuotas", ""));
            context.addCallbackParam("error", true);
        }
    }

    public void verificarTipoTramite() {
        MaeEntidaddet detalle = ejbEntidadDetalleFacade.findIdenEntiDet(tramite.getTipoTramTrm(), Constantes.ENTIDAD_TIPO_TRAMITE);
        if (detalle.getValoNumuDet() != null && detalle.getValoNumuDet().compareTo(BigInteger.ONE) == 0) {
            esPrestamo = true;
        } else {
            esPrestamo = false;
        }
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
            MaeEntidaddet detalle = ejbEntidadDetalleFacade.findIdenEntiDet(socio.getEntiPagoSoc(), Constantes.ENTIDAD_PAGOS_SOCIO);
            if (detalle != null && detalle.getValoDecuDet() != null) {
                maximoDescuento = detalle.getValoDecuDet();
            } else {
                maximoDescuento = BigDecimal.ZERO;
            }
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
            //cargarCanalesCobranza();
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

    /**
     * @return the netoGirar
     */
    public BigDecimal getNetoGirar() {
        return netoGirar;
    }

    /**
     * @param netoGirar the netoGirar to set
     */
    public void setNetoGirar(BigDecimal netoGirar) {
        this.netoGirar = netoGirar;
    }

    /**
     * @return the listaSeguros
     */
    public String getListaSeguros() {
        return listaSeguros;
    }

    /**
     * @param listaSeguros the listaSeguros to set
     */
    public void setListaSeguros(String listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    /**
     * @return the totalPago
     */
    public BigDecimal getTotalPago() {
        return totalPago;
    }

    /**
     * @param totalPago the totalPago to set
     */
    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }

    /**
     * @return the maximoDescuento
     */
    public BigDecimal getMaximoDescuento() {
        return maximoDescuento;
    }

    /**
     * @param maximoDescuento the maximoDescuento to set
     */
    public void setMaximoDescuento(BigDecimal maximoDescuento) {
        this.maximoDescuento = maximoDescuento;
    }

    /**
     * @return the cuotas
     */
    public List<Cuota> getCuotas() {
        return cuotas;
    }
    
    public JRBeanCollectionDataSource getCuotasReporte(){
        verSimulacion();
        return new JRBeanCollectionDataSource(getCuotas());
    }
    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the totalAmortizacion
     */
    public BigDecimal getTotalAmortizacion() {
        return totalAmortizacion;
    }

    /**
     * @param totalAmortizacion the totalAmortizacion to set
     */
    public void setTotalAmortizacion(BigDecimal totalAmortizacion) {
        this.totalAmortizacion = totalAmortizacion;
    }

    /**
     * @return the totalInteres
     */
    public BigDecimal getTotalInteres() {
        return totalInteres;
    }

    /**
     * @param totalInteres the totalInteres to set
     */
    public void setTotalInteres(BigDecimal totalInteres) {
        this.totalInteres = totalInteres;
    }

    /**
     * @return the totalSeguro
     */
    public BigDecimal getTotalSeguro() {
        return totalSeguro;
    }

    /**
     * @param totalSeguro the totalSeguro to set
     */
    public void setTotalSeguro(BigDecimal totalSeguro) {
        this.totalSeguro = totalSeguro;
    }

    /**
     * @return the totalCuota
     */
    public BigDecimal getTotalCuota() {
        return totalCuota;
    }

    /**
     * @param totalCuota the totalCuota to set
     */
    public void setTotalCuota(BigDecimal totalCuota) {
        this.totalCuota = totalCuota;
    }

    /**
     * @return the esPrestamo
     */
    public boolean isEsPrestamo() {
        return esPrestamo;
    }

    /**
     * @param esPrestamo the esPrestamo to set
     */
    public void setEsPrestamo(boolean esPrestamo) {
        this.esPrestamo = esPrestamo;
    }

    /**
     * @return the verCronograma
     */
    public boolean isVerCronograma() {
        return verCronograma;
    }

    /**
     * @param verCronograma the verCronograma to set
     */
    public void setVerCronograma(boolean verCronograma) {
        this.verCronograma = verCronograma;
    }

    /**
     * @return the codigoPoliza
     */
    public String getCodigoPoliza() {
        return codigoPoliza;
    }

    /**
     * @param codigoPoliza the codigoPoliza to set
     */
    public void setCodigoPoliza(String codigoPoliza) {
        this.codigoPoliza = codigoPoliza;
    }

    /**
     * @return the montoPoliza
     */
    public BigDecimal getMontoPoliza() {
        return montoPoliza;
    }

    /**
     * @param montoPoliza the montoPoliza to set
     */
    public void setMontoPoliza(BigDecimal montoPoliza) {
        this.montoPoliza = montoPoliza;
    }

    /**
     * @return the enOtraArea
     */
    public boolean isEnOtraArea() {
        return enOtraArea;
    }

    /**
     * @param enOtraArea the enOtraArea to set
     */
    public void setEnOtraArea(boolean enOtraArea) {
        this.enOtraArea = enOtraArea;
    }

    /**
     * @return the tieneCuotas
     */
    public boolean isTieneCuotas() {
        return tieneCuotas;
    }

    /**
     * @param tieneCuotas the tieneCuotas to set
     */
    public void setTieneCuotas(boolean tieneCuotas) {
        this.tieneCuotas = tieneCuotas;
    }

}