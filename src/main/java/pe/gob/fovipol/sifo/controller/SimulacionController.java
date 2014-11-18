package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.dao.MaeSeguroFacade;
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
import pe.gob.fovipol.sifo.dao.credito.CrdSimulaSeguroFacade;
import pe.gob.fovipol.sifo.model.credito.CrdSimulaSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.service.CreditoService;
import pe.gob.fovipol.sifo.util.Constantes;

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
    private BigDecimal totalAporteAnterior;
    private BigDecimal montoAnteriorPrestamo;
    private BigDecimal saldoPagarAnteriorPrestamo;
    private MaeEntidaddet detalle;
    private int tipoSocio;
    private Date nacimiento;
    private int edad;
    private String polizaNombre;
    private MaeEntidaddet moneda;
    private List<MaeEntidaddet> monedas;
    private List<MaeSeguro> seguros;
    private List<MaeSeguro> segurosSimulacion;
    private MaeSeguro seguro;
    private BigDecimal montoCheque;
    private BigDecimal degravamen;
    private List<CrdSimulacion> simulaciones;
    private List<Cuota> cuotasSimulacion;
    private BigDecimal totalAmortizacion;
    private BigDecimal totalInteres;
    private BigDecimal totalSeguro;
    private BigDecimal totalCuota;
    private BigDecimal gastosAdministrativos;
    private List<String> polizasElegidas;
    public boolean mostrarCabecera;
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
    private CrdSimulaSeguroFacade ejbSimulaSeguroFacade;

    @PostConstruct
    public void init() {        
        mostrarCabecera = true;
        String idSimulacion = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("idSimulacion");
        simulacion = null;
        totalAporteAnterior = BigDecimal.ZERO;
        montoAnteriorPrestamo = BigDecimal.ZERO;
        totalAporte = BigDecimal.ZERO;
        saldoPagarAnteriorPrestamo = BigDecimal.ZERO;
        if (idSimulacion != null && !idSimulacion.trim().equals("")) {
            simulacion = ejbSimulacionFacade.find(new BigDecimal(idSimulacion));
        }
        if (simulacion != null) {            
            mostrarCabecera = false;
            setSocio(simulacion.getIdenPersPer());
            calcularEdad();
            producto = simulacion.getIdenProdPrd();
            calcularGastosAdministrativos();
            cargarSeguros(simulacion.getIdenSimuSim());
            simulacion.setIdenSimuSim(null);
        } else {
            simulacion = new CrdSimulacion();
            tipoSocio = 1;
            segurosSimulacion = new ArrayList<>();
            producto = new MaeProducto();
        }
        simulacion.setCapaMcuoSim(BigDecimal.ZERO);
        simulacion.setImpoMaxpSim(BigDecimal.ZERO);
        simulacion.setIngrBrtoSim(BigDecimal.ZERO);
        simulacion.setDsctOficSim(BigDecimal.ZERO);
        simulacion.setDsctPersSim(BigDecimal.ZERO);
        simulacion.setIngrCombSim(BigDecimal.ZERO);
        simulacion.setDeudOtraSim(BigDecimal.ZERO);
        simulacion.setOtroIngrSim(BigDecimal.ZERO);
    }

    public void cargarSeguros(BigDecimal idSimulacion) {
        List<CrdSimulaSeguro> simulaSeguro = ejbSimulaSeguroFacade.findBySimulacion(simulacion);
        polizasElegidas = new ArrayList<>();
        for (CrdSimulaSeguro aux : simulaSeguro) {
            polizasElegidas.add(aux.getIdenSeguSeg().getIdenSeguSeg().toString());
        }
    }

    public void calcularGastosAdministrativos() {
        if (simulacion.getImpoSoliSim() != null && producto != null && producto.getTasaGadmPrd() != null) {
            gastosAdministrativos = simulacion.getImpoSoliSim().multiply(producto.getTasaGadmPrd()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
            montoCheque = simulacion.getImpoSoliSim().add(gastosAdministrativos.add(saldoPagarAnteriorPrestamo).negate());
        }
    }

    public void nuevaSimulacion() {
        socio = null;
        simulacion = new CrdSimulacion();
        simulacion.setIngrBrtoSim(BigDecimal.ZERO);
        simulacion.setDsctOficSim(BigDecimal.ZERO);
        simulacion.setDsctPersSim(BigDecimal.ZERO);
        simulacion.setIngrCombSim(BigDecimal.ZERO);
        simulacion.setDeudOtraSim(BigDecimal.ZERO);
        simulacion.setOtroIngrSim(BigDecimal.ZERO);
        porcDescuento = BigDecimal.ZERO;
        totalAporteAnterior = BigDecimal.ZERO;
        montoAnteriorPrestamo = BigDecimal.ZERO;
        totalAporte = BigDecimal.ZERO;
        saldoPagarAnteriorPrestamo = BigDecimal.ZERO;
        tipoSocio = 1;
        segurosSimulacion = new ArrayList<>();
        producto = new MaeProducto();
        edad = 0;
        gastosAdministrativos = BigDecimal.ZERO;
        montoCheque = BigDecimal.ZERO;
        polizaNombre = "";
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
            if (producto.getIdenProdPrd().compareTo(new BigDecimal(3)) != 0) {
                totalAporteAnterior = BigDecimal.ZERO;
                montoAnteriorPrestamo = BigDecimal.ZERO;
                saldoPagarAnteriorPrestamo = BigDecimal.ZERO;
            }
            simulacion.setPlazPresSim(producto.getMaxiPeriPrd());
            simulacion.setCapaMcuoSim(creditoService.calcularMaximaCuota(simulacion.getIngrBrtoSim(), porcDescuento,
                    simulacion.getDsctOficSim(), simulacion.getDsctPersSim(), simulacion.getIngrCombSim()));
            simulacion.setImpoMaxpSim(creditoService.calcularMaximoPrestamo(totalAporte,
                    producto.getCantVecePrd(), simulacion.getDeudOtraSim(), producto.getMontDeudPrd(), simulacion.getOtroIngrSim(), montoAnteriorPrestamo));
        }
    }

    public boolean validarIndividual(BigDecimal valorSimulacion) {
        return !(valorSimulacion == null || valorSimulacion.compareTo(BigDecimal.ZERO) == -1);
    }

    public boolean validarMontos() {
        if (!validarIndividual(simulacion.getIngrBrtoSim())) {
            return false;
        }
        if (!validarIndividual(simulacion.getDsctOficSim())) {
            return false;
        }
        if (!validarIndividual(simulacion.getDsctPersSim())) {
            return false;
        }
        if (!validarIndividual(simulacion.getIngrCombSim())) {
            return false;
        }
        if (!validarIndividual(simulacion.getOtroIngrSim())) {
            return false;
        }
        if (!validarIndividual(totalAporte)) {
            return false;
        }
        if (!validarIndividual(totalAporteAnterior)) {
            return false;
        }
        if (!validarIndividual(simulacion.getDeudOtraSim())) {
            return false;
        }
        if (!validarIndividual(montoAnteriorPrestamo)) {
            return false;
        }
        return validarIndividual(saldoPagarAnteriorPrestamo);
    }

    public void simular() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (!validarMontos()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Los Montos ingresados deben ser positivos", ""));
            context.addCallbackParam("error", true);
            return;
        }
        if (simulacion.getImpoSoliSim() == null || simulacion.getImpoSoliSim().compareTo(BigDecimal.ZERO) == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese un monto de préstamo a simular", ""));
            context.addCallbackParam("error", true);
            return;
        }
        if (simulacion.getPlazPresSim() == null || simulacion.getPlazPresSim().compareTo(BigInteger.ONE) == -1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese un número de cuotas mayor a 0", ""));
            context.addCallbackParam("error", true);
            return;
        }
        if (polizasElegidas == null || polizasElegidas.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese Póliza de seguro", ""));
            context.addCallbackParam("error", true);
            return;
        }
        if (simulacion.getImpoSoliSim().compareTo(simulacion.getImpoMaxpSim()) == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El Monto de préstamo es superior al Máximo Préstamo posible", ""));
            context.addCallbackParam("error", true);
            totalAmortizacion = BigDecimal.ZERO;
            totalAporte = BigDecimal.ZERO;
            totalCuota = BigDecimal.ZERO;
            totalInteres = BigDecimal.ZERO;
            cuotasSimulacion = new ArrayList<>();
            return;
        }
        if (simulacion.getPlazPresSim() == null || simulacion.getPlazPresSim().compareTo(producto.getMaxiPeriPrd()) == 1) {
            if (simulacion.getPlazPresSim() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese el número de cuotas o el monto mensual ", ""));
                context.addCallbackParam("error", true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de cuotas debe ser menor a " + producto.getMaxiPeriPrd(), ""));
                context.addCallbackParam("error", true);
            }
            return;
        }

        simulacion.setTasaTeaSim(producto.getTasaIntePrd());
        if (simulacion.getImpoCuotSim() != null) {
            if (simulacion.getPlazPresSim() != null) {
                cuotaPagar = simulacion.getImpoCuotSim();
                simulacion.setImpoSoliSim(creditoService.calcularCuotaMontoTotal(cuotaPagar, simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim()));
                if (simulacion.getImpoSoliSim().compareTo(simulacion.getImpoMaxpSim()) == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El monto de préstamo es superior al Máximo préstamo posible", ""));
                    simulacion.setImpoSoliSim(simulacion.getImpoMaxpSim());
                    cuotaPagar = creditoService.calcularCuotaMontoMensual(simulacion.getImpoSoliSim(), simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim());
                }
            }
        } else {
            if (simulacion.getImpoSoliSim() != null) {
                cuotaPagar = creditoService.calcularCuotaMontoMensual(simulacion.getImpoSoliSim(), simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim());
                if (cuotaPagar.compareTo(simulacion.getCapaMcuoSim()) == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La Cuota a pagar es superior a la Máxima cuota posible", ""));
                    cuotaPagar = simulacion.getCapaMcuoSim();
                    simulacion.setImpoSoliSim(creditoService.calcularCuotaMontoTotal(cuotaPagar, simulacion.getPlazPresSim().intValue(), simulacion.getTasaTeaSim()));
                }
            }
        }
        gastosAdministrativos = simulacion.getImpoSoliSim().multiply(producto.getTasaGadmPrd()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        segurosSimulacion = new ArrayList<>();
        for (String seg : polizasElegidas) {
            MaeSeguro segu = ejbSeguroFacade.find(new BigDecimal(seg));
            segurosSimulacion.add(segu);
        }
        if (creditoService.comprobarEdadFinPago(socio.getMaePersona().getFechNaciPer(), simulacion.getPeriCiclSim().intValue(), simulacion.getPlazPresSim().intValue(), segurosSimulacion)) {
            simulacion.setIdenPersPer(socio);
            simulacion.setIdenProdPrd(producto);
            simulacion.setTasaGadmSim(producto.getTasaGadmPrd());
            simulacion.setImpoCuotSim(cuotaPagar);
            boolean crea = false;
            if (simulacion.getIdenSimuSim() == null) {
                simulacion.setIdenSimuSim(ejbSimulacionFacade.obtenerCorrelativo());
                simulacion.setFlagEstaSim(Constantes.VALOR_ESTADO_ACTIVO);
                crea = true;
            }
            cuotasSimulacion = creditoService.calcularCuotas(segurosSimulacion, simulacion.getPeriCiclSim().intValue(),
                    simulacion.getTasaTeaSim(), simulacion.getImpoSoliSim(), simulacion.getPlazPresSim(), cuotaPagar, socio.getMaePersona().getFechNaciPer(), simulacion);
            ejbSimulacionFacade.edit(simulacion);
            int i = 1;
            for (MaeSeguro seg : segurosSimulacion) {
                CrdSimulaSeguro simSeguro = new CrdSimulaSeguro(simulacion.getIdenSimuSim().toBigInteger(), i);
                simSeguro.setIdenSeguSeg(seg);
                simSeguro.setFlagEstaSsg(Constantes.VALOR_ESTADO_ACTIVO);
                if (crea) {
                    simSeguro.setFechCreaAud(new Date());
                } else {
                    simSeguro.setFechModiAud(new Date());
                }
                ejbSimulaSeguroFacade.edit(simSeguro);
                i++;
            }
            totalAmortizacion = cuotasSimulacion.get(0).getTotalAmortizacion();
            totalCuota = cuotasSimulacion.get(0).getTotalCuota();
            totalInteres = cuotasSimulacion.get(0).getTotalInteres();
            totalSeguro = cuotasSimulacion.get(0).getTotalSeguro();
            montoCheque = simulacion.getImpoSoliSim().add(gastosAdministrativos.add(saldoPagarAnteriorPrestamo).negate());
            context.addCallbackParam("error", false);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "En ese periodo de tiempo no se puede generar pues los seguros no lo cubren", ""));
            context.addCallbackParam("error", true);
        }

    }

    public boolean estaIncluido(int a, BigDecimal b, BigDecimal c) {
        BigDecimal auxBigDecimal = new BigDecimal(a);
        return auxBigDecimal.compareTo(b) != -1 && auxBigDecimal.compareTo(c) != 1;
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

        if (socio != null) {
            this.socio = socio;
            calcularEdad();
            detalle = ejbEntidaddetFacade.findIdenEntiDet(socio.getEntiPagoSoc(), Constantes.ENTIDAD_PAGOS_SOCIO);
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
            monedas = ejbEntidaddetFacade.findDetalleActivo(new MaeEntidad(Constantes.ENTIDAD_TIPO_MONEDA));
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

    /**
     * @return the totalAporteAnterior
     */
    public BigDecimal getTotalAporteAnterior() {
        return totalAporteAnterior;
    }

    /**
     * @param totalAporteAnterior the totalAporteAnterior to set
     */
    public void setTotalAporteAnterior(BigDecimal totalAporteAnterior) {
        this.totalAporteAnterior = totalAporteAnterior;
    }

    /**
     * @return the montoAnteriorPrestamo
     */
    public BigDecimal getMontoAnteriorPrestamo() {
        return montoAnteriorPrestamo;
    }

    /**
     * @param montoAnteriorPrestamo the montoAnteriorPrestamo to set
     */
    public void setMontoAnteriorPrestamo(BigDecimal montoAnteriorPrestamo) {
        this.montoAnteriorPrestamo = montoAnteriorPrestamo;
    }

    /**
     * @return the saldoPagarAnteriorPrestamo
     */
    public BigDecimal getSaldoPagarAnteriorPrestamo() {
        return saldoPagarAnteriorPrestamo;
    }

    /**
     * @param saldoPagarAnteriorPrestamo the saldoPagarAnteriorPrestamo to set
     */
    public void setSaldoPagarAnteriorPrestamo(BigDecimal saldoPagarAnteriorPrestamo) {
        this.saldoPagarAnteriorPrestamo = saldoPagarAnteriorPrestamo;
    }

    /**
     * @return the montoCheque
     */
    public BigDecimal getMontoCheque() {
        return montoCheque;
    }

    /**
     * @param montoCheque the montoCheque to set
     */
    public void setMontoCheque(BigDecimal montoCheque) {
        this.montoCheque = montoCheque;
    }

    public JRBeanCollectionDataSource getCuotasReporte() {
        return new JRBeanCollectionDataSource(getCuotasSimulacion());
    }

    /**
     * @return the polizasElegidas
     */
    public List<String> getPolizasElegidas() {
        return polizasElegidas;
    }

    /**
     * @param polizasElegidas the polizasElegidas to set
     */
    public void setPolizasElegidas(List<String> polizasElegidas) {
        this.polizasElegidas = polizasElegidas;
    }

    /**
     * @return the mostrarCabecera
     */
    public boolean isMostrarCabecera() {
        return mostrarCabecera;
    }

    /**
     * @param mostrarCabecera the mostrarCabecera to set
     */
    public void setMostrarCabecera(boolean mostrarCabecera) {
        this.mostrarCabecera = mostrarCabecera;
    }
}
