package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.dao.MaeSeguroFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pe.gob.fovipol.sifo.controller.util.Cuota;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.dao.CrdSimulacionFacade;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.simulacion.CrdSimulacion;

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
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;
    @EJB
    private MaeSeguroFacade ejbSeguroFacade;
    @EJB
    private CrdSimulacionFacade ejbSimulacionFacade;
    @PostConstruct
    public void init() {
        simulacion = new CrdSimulacion();
        simulacion.setIngrBrtoSim(BigDecimal.ZERO);
        simulacion.setDsctOficSim(BigDecimal.ZERO);
        simulacion.setDsctPersSim(BigDecimal.ZERO);
        simulacion.setIngrCombSim(BigDecimal.ZERO);
        simulacion.setDeudOtraSim(BigDecimal.ZERO);
        simulacion.setTasaTeaSim(new BigDecimal(3));
        totalAporte = BigDecimal.ZERO;
        tipoSocio = 1;
        segurosSimulacion = new ArrayList<>();
        producto = new MaeProducto();
    }

    public SimulacionController() {
    }

    public void calcularEdad() {
        Calendar dob = Calendar.getInstance();
        dob.setTime(nacimiento);
        Calendar today = Calendar.getInstance();
        edad = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            edad--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            edad--;
        }
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
            BigDecimal valor1, valor2;
            valor1 = simulacion.getIngrBrtoSim().multiply(porcDescuento).divide(new BigDecimal(100));
            valor1 = valor1.add(simulacion.getDsctOficSim().negate());
            valor1 = valor1.add(simulacion.getDsctPersSim().negate()).add(simulacion.getIngrCombSim());
            simulacion.setCapaMcuoSim(valor1);
            valor2 = totalAporte.multiply(new BigDecimal(producto.getCantVecePrd())).add(simulacion.getDeudOtraSim().negate());
            simulacion.setImpoMaxpSim(valor2);
        } else {
            //JsfUtil.addErrorMessage("Seleccione un Producto");
        }
    }

    public void calcularDegravamen() {
        degravamen = BigDecimal.ZERO;
        if (edad < 66) {
            degravamen = new BigDecimal("0.36565");
        } else {
            if (edad < 71) {
                degravamen = new BigDecimal("1.52440");
            } else {
                if (edad < 80) {
                    degravamen = new BigDecimal("2.82220");
                }
            }
        }
    }

    public BigDecimal calcularMontoMensual(BigDecimal m, int plazo) {
        BigDecimal aPagar;
        BigDecimal tasa = simulacion.getTasaTeaSim().divide(new BigDecimal(100));
        aPagar = m.multiply(tasa);
        BigDecimal auxi = tasa.add(BigDecimal.ONE);
        auxi = auxi.pow(plazo);
        aPagar = aPagar.multiply(auxi).divide(auxi.add(BigDecimal.ONE.negate()), 2, RoundingMode.UP);
        return aPagar;
    }

    public BigDecimal calcularMontoTotal(BigDecimal a, int plazo) {
        BigDecimal totPagar;
        BigDecimal tasa = simulacion.getTasaTeaSim().divide(new BigDecimal(100));
        BigDecimal auxi = tasa.add(BigDecimal.ONE);
        auxi = auxi.pow(plazo);
        totPagar = a.multiply(auxi.add(BigDecimal.ONE.negate()));
        totPagar = totPagar.divide(auxi.multiply(tasa), 2, RoundingMode.UP);
        return totPagar;
    }

    public void simular() {
        if (monto.compareTo(simulacion.getImpoMaxpSim()) == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"El Monto es superior al Máximo Préstamo",""));
            totalAmortizacion = BigDecimal.ZERO;
            totalAporte = BigDecimal.ZERO;
            totalCuota = BigDecimal.ZERO;
            totalInteres = BigDecimal.ZERO;
            cuotasSimulacion=new ArrayList<>();
        } else {
            calcularDegravamen();
            if(cuota!=null){
                if(simulacion.getPlazPresSim()!=null){
                    cuotaPagar=cuota;
                    monto = calcularMontoTotal(cuotaPagar, simulacion.getPlazPresSim().intValue());
                    if(monto.compareTo(simulacion.getImpoMaxpSim()) == 1){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"La monto de préstamo es superior al Máximo préstamo posible",""));            
                        monto = simulacion.getImpoMaxpSim();
                        cuotaPagar = calcularMontoMensual(monto, simulacion.getPlazPresSim().intValue());
                    }
                }
            }
            else{
                if(monto!=null){
                    cuotaPagar = calcularMontoMensual(monto, simulacion.getPlazPresSim().intValue());
                    if (cuotaPagar.compareTo(simulacion.getCapaMcuoSim()) == 1) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"La Cuota es superior a la Máxima cuota posible",""));            
                        cuotaPagar = simulacion.getCapaMcuoSim();
                        monto = calcularMontoTotal(cuotaPagar, simulacion.getPlazPresSim().intValue());
                    }
                }                 
            }
            Calendar today = Calendar.getInstance();
            today.set(Calendar.DAY_OF_MONTH, ciclica);
            int mes;
            cuotasSimulacion = new ArrayList<>();
            BigDecimal tasa = simulacion.getTasaTeaSim().divide(new BigDecimal(100));
            BigDecimal montoaux = monto;
            totalAmortizacion = BigDecimal.ZERO;
            totalAporte = BigDecimal.ZERO;
            totalCuota = BigDecimal.ZERO;
            totalInteres = BigDecimal.ZERO;
            for (int i = 0; i < simulacion.getPlazPresSim().intValue(); i++) {
                Cuota c = new Cuota();
                mes = today.get(Calendar.MONTH);
                today.set(Calendar.MONTH, mes + 1);
                c.setSaldoInicial(montoaux);
                c.setCuota(cuotaPagar);
                c.setInteres(montoaux.multiply(tasa).divide(BigDecimal.ONE, 2, RoundingMode.UP));
                c.setAmortizado(c.getCuota().add(c.getInteres().negate()));
                montoaux = montoaux.add(c.getAmortizado().negate());
                c.setNumero(i + 1);
                c.setFechaPago(today.getTime());
                totalAmortizacion = totalAmortizacion.add(c.getAmortizado());
                totalCuota = totalCuota.add(c.getCuota());
                totalInteres = totalInteres.add(c.getInteres());
                cuotasSimulacion.add(c);
            }
            simulacion.setIdenSimuSim(ejbSimulacionFacade.obtenerCorrelativo());
            ejbSimulacionFacade.create(simulacion);
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
        if (socio != null) {
            detalle = ejbEntidaddetFacade.findIdenEntiDet(socio.getEntiPagoSoc(), "ENTIPAGOSOC");
            porcDescuento = detalle.getValoDecuDet();
            if (detalle.getValoDecuDet().compareTo(new BigDecimal(30)) == 0) {
                tipoSocio = 2;
            } else {
                tipoSocio = 1;
                simulacion.setIngrCombSim(BigDecimal.ZERO);
            }
        }
        this.socio = socio;
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

}
