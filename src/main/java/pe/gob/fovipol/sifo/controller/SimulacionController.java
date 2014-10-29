package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.dao.MaeSeguroFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
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
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;
    @EJB
    private MaeSeguroFacade ejbSeguroFacade;
    @PostConstruct
    public void init(){
        simulacion=new CrdSimulacion();
        simulacion.setIngrBrtoSim(BigDecimal.ZERO);
        simulacion.setDsctOficSim(BigDecimal.ZERO);
        simulacion.setDsctPersSim(BigDecimal.ZERO);
        simulacion.setIngrCombSim(BigDecimal.ZERO);
        simulacion.setDeudOtraSim(BigDecimal.ZERO);
        simulacion.setTasaTeaSim(new BigDecimal(3));
        totalAporte=BigDecimal.ZERO;
        tipoSocio=1;
        segurosSimulacion=new ArrayList<>();
        producto=new MaeProducto();
    }
    public SimulacionController() {
    }
    
    public void calcularEdad(){
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
    
    public void agregarPoliza(){
        if(seguro!=null){
            segurosSimulacion.add(seguro);        
        }
        polizaNombre="";
        for(MaeSeguro seg:segurosSimulacion){
            if(polizaNombre.equals(""))
                polizaNombre=seg.getDescNombSeg();
            else
                polizaNombre=polizaNombre+", "+seg.getDescNombSeg();
        }
    }
    
    public void calcular(){
        if(producto!=null && socio!=null){
            BigDecimal valor1,valor2;
            valor1=simulacion.getIngrBrtoSim().multiply(porcDescuento).divide(new BigDecimal(100));
            valor1=valor1.add(simulacion.getDsctOficSim().negate());
            valor1=valor1.add(simulacion.getDsctPersSim().negate()).add(simulacion.getIngrCombSim());
            simulacion.setCapaMcuoSim(valor1);
            valor2=totalAporte.multiply(new BigDecimal(producto.getCantVecePrd())).add(simulacion.getDeudOtraSim().negate());
            simulacion.setImpoMaxpSim(valor2);
        }
        else{
            //JsfUtil.addErrorMessage("Seleccione un Producto");
        }
    }
    
    public void simular(){
        
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
        if(socio!=null){
            detalle=ejbEntidaddetFacade.findIdenEntiDet(socio.getEntiPagoSoc(), "ENTIPAGOSOC");
            porcDescuento=detalle.getValoDecuDet();
            if(detalle.getValoDecuDet().compareTo(new BigDecimal(30))==0)
                tipoSocio=2;
            else{
                tipoSocio=1;
                simulacion.setIngrCombSim(BigDecimal.ZERO);
            }                
        }
        this.socio = socio;
    }

    /**
     * @return the productos
     */
    public List<MaeProducto> getProductos() {
        if(productos==null)
            productos=ejbProductoFacade.findAll();
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
        if(monedas==null)
            monedas=ejbEntidaddetFacade.findDetalleActivo(new MaeEntidad("TIPOMONECRD"));
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
        if(seguros==null)
            seguros=ejbSeguroFacade.findAll();
        return seguros;
    }

    /**
     * @param seguros the seguros to set
     */
    public void setSeguros(List<MaeSeguro> seguros) {
        this.seguros = seguros;
    }//borrar

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

}
