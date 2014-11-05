package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.dao.MaeSeguroFacade;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.primefaces.context.RequestContext;
import pe.gob.fovipol.sifo.controller.util.Cuota;
import pe.gob.fovipol.sifo.dao.CrdSimulacionFacade;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.dao.MaeSeguroRangoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.service.CreditoService;

@ManagedBean(name = "simulacionController")
@ViewScoped
public class SimulacionController implements Serializable {

    private MaeSocio socio;
    private List<MaeProducto> productos;
    private MaeProducto producto;
    private CrdSimulacion simulacion;
    private BigDecimal porcDescuento;
    private BigDecimal totalAporte;
    private BigDecimal cuotaPagar;
    private MaeEntidaddet detalle;
    private int ciclica;
    private int tipoSocio;
    private Date nacimiento;
    private int edad;
    private String polizaNombre;
    private MaeEntidaddet moneda;
    private List<MaeEntidaddet> monedas;
    private List<MaeSeguro> seguros;
    private List<MaeSeguro> segurosSimulacion;
    private MaeSeguro seguro;
    private BigDecimal monto;
    private BigDecimal liq;
    private BigDecimal cuota;
    private BigDecimal degravamen;
    private List<CrdSimulacion> simulaciones;
    private List<Cuota> cuotasSimulacion;
    private BigDecimal totalAmortizacion;
    private BigDecimal totalInteres;
    private BigDecimal totalSeguro;
    private BigDecimal totalCuota;
    private BigDecimal gastosAdministrativos;
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @EJB
    private CreditoService creditoService;
    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;
    @EJB
    private MaeSeguroFacade ejbSeguroFacade;
    @EJB
    private CrdSimulacionFacade ejbSimulacionFacade;
    @EJB
    private MaeSeguroRangoFacade ejbSeguroRangoFacade;

    @PostConstruct
    public void init() {
        simulacion = new CrdSimulacion();
        simulacion.setIngrBrtoSim(BigDecimal.ZERO);
        simulacion.setDsctOficSim(BigDecimal.ZERO);
        simulacion.setDsctPersSim(BigDecimal.ZERO);
        simulacion.setIngrCombSim(BigDecimal.ZERO);
        simulacion.setDeudOtraSim(BigDecimal.ZERO);
        simulacion.setOtroIngrSim(BigDecimal.ZERO);
        //simulacion.setTasaTeaSim(new BigDecimal(3));
        totalAporte = BigDecimal.ZERO;
        tipoSocio = 1;
        segurosSimulacion = new ArrayList<>();
        producto = new MaeProducto();
    }
    
    public void calcularGastosAdministrativos(){
        if(monto!=null && producto!=null && producto.getTasaGadmPrd()!=null)
            gastosAdministrativos=monto.multiply(producto.getTasaGadmPrd()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }
    public SimulacionController() {
    }

    public void calcularEdad() {
        edad = creditoService.calcularEdad(socio.getMaePersona().getFechNaciPer(), new Date());
    }

    public void agregarPoliza() {
        if (seguro != null) {
            segurosSimulacion.add(seguro);
        }
        polizaNombre = "";
        for (MaeSeguro seg : segurosSimulacion) {
            if (polizaNombre.equals("")) {
                polizaNombre = seg.getDescNombSeg();
            } else {
                polizaNombre = polizaNombre + ", " + seg.getDescNombSeg();
            }
        }
    }

    public void calcular() {
        if (producto != null && socio != null) {
            simulacion.setCapaMcuoSim(creditoService.calcularMaximaCuota(simulacion.getIngrBrtoSim(), porcDescuento,
                    simulacion.getDsctOficSim(), simulacion.getDsctPersSim(), simulacion.getIngrCombSim()));
            simulacion.setImpoMaxpSim(creditoService.calcularMaximoPrestamo(totalAporte,
                    producto.getCantVecePrd(), simulacion.getDeudOtraSim(), producto.getMontDeudPrd(),simulacion.getOtroIngrSim()));
        }
    }

    public void simular() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (segurosSimulacion != null && !segurosSimulacion.isEmpty()) {
            if (monto == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese un monto a simular", ""));
                context.addCallbackParam("error", true);
            } else {
                if (monto.compareTo(simulacion.getImpoMaxpSim()) == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El Monto es superior al Máximo Préstamo", ""));
                    context.addCallbackParam("error", true);
                    totalAmortizacion = BigDecimal.ZERO;
                    totalAporte = BigDecimal.ZERO;
                    totalCuota = BigDecimal.ZERO;
                    totalInteres = BigDecimal.ZERO;
                    cuotasSimulacion = new ArrayList<>();
                } else {
                    if (simulacion.getPlazPresSim() != null && simulacion.getPlazPresSim().compareTo(producto.getMaxiPeriPrd()) == -1) {
                        simulacion.setTasaTeaSim(producto.getTasaIntePrd());
                        if (cuota != null) {
                            if (simulacion.getPlazPresSim() != null) {
                                cuotaPagar = cuota;
                                monto = creditoService.calcularCuotaMontoTotal(cuotaPagar, simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim());
                                if (monto.compareTo(simulacion.getImpoMaxpSim()) == 1) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La monto de préstamo es superior al Máximo préstamo posible", ""));
                                    monto = simulacion.getImpoMaxpSim();
                                    cuotaPagar = creditoService.calcularCuotaMontoMensual(monto, simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim());
                                }
                            }
                        } else {
                            if (monto != null) {
                                cuotaPagar = creditoService.calcularCuotaMontoMensual(monto, simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim());
                                if (cuotaPagar.compareTo(simulacion.getCapaMcuoSim()) == 1) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La Cuota es superior a la Máxima cuota posible", ""));
                                    cuotaPagar = simulacion.getCapaMcuoSim();
                                    monto = creditoService.calcularCuotaMontoTotal(cuotaPagar, simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim());
                                }
                            }
                        }
                        gastosAdministrativos = monto.multiply(producto.getTasaGadmPrd()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                        if (creditoService.comprobarEdadFinPago(socio.getMaePersona().getFechNaciPer(), ciclica, simulacion.getPlazPresSim().intValue(), segurosSimulacion)) {
                            cuotasSimulacion = creditoService.calcularCuotas(segurosSimulacion, ciclica,
                                    simulacion.getTasaTeaSim(), monto, simulacion.getPlazPresSim(), cuotaPagar, socio.getMaePersona().getFechNaciPer());
                            if (cuotasSimulacion != null && !cuotasSimulacion.isEmpty()) {
                                simulacion.setIdenSimuSim(ejbSimulacionFacade.obtenerCorrelativo());
                                simulacion.setFechCreaAud(new Date());
                                simulacion.setFlagEstaSim(new Short("1"));
                                simulacion.setIdenPersPer(socio);
                                simulacion.setIdenProdPrd(producto);
                                simulacion.setTasaGadmSim(producto.getTasaGadmPrd());
                                ejbSimulacionFacade.create(simulacion);
                                totalAmortizacion = cuotasSimulacion.get(0).getTotalAmortizacion();
                                totalCuota = cuotasSimulacion.get(0).getTotalCuota();
                                totalInteres = cuotasSimulacion.get(0).getTotalInteres();
                                totalSeguro = cuotasSimulacion.get(0).getTotalSeguro();
                                context.addCallbackParam("error", false);
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudieron generar cuotas", ""));
                                context.addCallbackParam("error", true);
                            }
                        }
                        else{
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "En ese periodo de tiempo no puede por los seguros", ""));
                            context.addCallbackParam("error", true);
                        }
                    } else {
                        if(simulacion.getPlazPresSim() == null){
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese el número de cuotas o el monto mensual ", ""));
                            context.addCallbackParam("error", true);
                        }                           
                        else{
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de cuotas debe ser menor a " + producto.getMaxiPeriPrd(), ""));
                            context.addCallbackParam("error", true);
                        }
                    }
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese Póliza de seguro", ""));
            context.addCallbackParam("error", true);
        }
    }

    public boolean estaIncluido(int a, BigDecimal b, BigDecimal c) {
        BigDecimal auxBigDecimal = new BigDecimal(a);
        if (auxBigDecimal.compareTo(b) != -1 && auxBigDecimal.compareTo(c) != 1) {
            return true;
        } else {
            return false;
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
        if (socio != null) {
            calcularEdad();
            detalle = ejbEntidaddetFacade.findIdenEntiDet(socio.getEntiPagoSoc(), "ENTIPAGOSOC");
            porcDescuento = detalle.getValoDecuDet();
            if (detalle.getValoDecuDet().compareTo(new BigDecimal(30)) == 0) {
                tipoSocio = 2;
            } else {
                tipoSocio = 1;
                simulacion.setIngrCombSim(BigDecimal.ZERO);
            }
        }

    }

    /**
     * @return the productos
     */
    public List<MaeProducto> getProductos() {
        if (productos == null) {
            productos = ejbProductoFacade.findAll();
        }
        return productos;
    }

    /**
     * @param productos the productos to set
     */
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
     * @return the simulacion
     */
    public CrdSimulacion getSimulacion() {
        return simulacion;
    }

    /**
     * @param simulacion the simulacion to set
     */
    public void setSimulacion(CrdSimulacion simulacion) {
        this.simulacion = simulacion;
    }

    /**
     * @return the porcDescuento
     */
    public BigDecimal getPorcDescuento() {
        return porcDescuento;
    }

    /**
     * @param porcDescuento the porcDescuento to set
     */
    public void setPorcDescuento(BigDecimal porcDescuento) {
        this.porcDescuento = porcDescuento;
    }

    /**
     * @return the totalAporte
     */
    public BigDecimal getTotalAporte() {
        return totalAporte;
    }

    /**
     * @param totalAporte the totalAporte to set
     */
    public void setTotalAporte(BigDecimal totalAporte) {
        this.totalAporte = totalAporte;
    }

    /**
     * @return the tipoSocio
     */
    public int getTipoSocio() {
        return tipoSocio;
    }

    /**
     * @param tipoSocio the tipoSocio to set
     */
    public void setTipoSocio(int tipoSocio) {
        this.tipoSocio = tipoSocio;
    }

    /**
     * @return the moneda
     */
    public MaeEntidaddet getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(MaeEntidaddet moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the monedas
     */
    public List<MaeEntidaddet> getMonedas() {
        if (monedas == null) {
            monedas = ejbEntidaddetFacade.findDetalleActivo(new MaeEntidad("TIPOMONECRD"));
        }
        return monedas;
    }

    /**
     * @param monedas the monedas to set
     */
    public void setMonedas(List<MaeEntidaddet> monedas) {
        this.monedas = monedas;
    }

    /**
     * @return the seguros
     */
    public List<MaeSeguro> getSeguros() {
        if (seguros == null) {
            seguros = ejbSeguroFacade.findAll();
        }
        return seguros;
    }

    /**
     * @param seguros the seguros to set
     */
    public void setSeguros(List<MaeSeguro> seguros) {
        this.seguros = seguros;
    }

    /**
     * @return the seguro
     */
    public MaeSeguro getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(MaeSeguro seguro) {
        this.seguro = seguro;
    }

    /**
     * @return the ciclica
     */
    public int getCiclica() {
        return ciclica;
    }

    /**
     * @param ciclica the ciclica to set
     */
    public void setCiclica(int ciclica) {
        this.ciclica = ciclica;
    }

    /**
     * @return the nacimiento
     */
    public Date getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
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
     * @return the polizaNombre
     */
    public String getPolizaNombre() {
        return polizaNombre;
    }

    /**
     * @param polizaNombre the polizaNombre to set
     */
    public void setPolizaNombre(String polizaNombre) {
        this.polizaNombre = polizaNombre;
    }

    /**
     * @return the segurosSimulacion
     */
    public List<MaeSeguro> getSegurosSimulacion() {
        return segurosSimulacion;
    }

    /**
     * @param segurosSimulacion the segurosSimulacion to set
     */
    public void setSegurosSimulacion(List<MaeSeguro> segurosSimulacion) {
        this.segurosSimulacion = segurosSimulacion;
    }

    /**
     * @return the monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * @return the liq
     */
    public BigDecimal getLiq() {
        return liq;
    }

    /**
     * @param liq the liq to set
     */
    public void setLiq(BigDecimal liq) {
        this.liq = liq;
    }

    /**
     * @return the cuota
     */
    public BigDecimal getCuota() {
        return cuota;
    }

    /**
     * @param cuota the cuota to set
     */
    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    /**
     * @return the degravamen
     */
    public BigDecimal getDegravamen() {
        return degravamen;
    }

    /**
     * @param degravamen the desagravamen to set
     */
    public void setDegravamen(BigDecimal degravamen) {
        this.degravamen = degravamen;
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
     * @return the cuotasSimulacion
     */
    public List<Cuota> getCuotasSimulacion() {
        return cuotasSimulacion;
    }

    /**
     * @param cuotasSimulacion the cuotasSimulacion to set
     */
    public void setCuotasSimulacion(List<Cuota> cuotasSimulacion) {
        this.cuotasSimulacion = cuotasSimulacion;
    }

    /**
     * @return the cuotaPagar
     */
    public BigDecimal getCuotaPagar() {
        return cuotaPagar;
    }

    /**
     * @param cuotaPagar the cuotaPagar to set
     */
    public void setCuotaPagar(BigDecimal cuotaPagar) {
        this.cuotaPagar = cuotaPagar;
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
     * @return the gastosAdministrativos
     */
    public BigDecimal getGastosAdministrativos() {
        return gastosAdministrativos;
    }

    /**
     * @param gastosAdministrativos the gastosAdministrativos to set
     */
    public void setGastosAdministrativos(BigDecimal gastosAdministrativos) {
        this.gastosAdministrativos = gastosAdministrativos;
    }

}
