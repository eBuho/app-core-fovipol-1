package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
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
    private boolean tipoSocio;
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;
    @PostConstruct
    public void init(){
        simulacion=new CrdSimulacion();
        simulacion.setIngrBrtoSim(BigDecimal.ZERO);
        simulacion.setDsctOficSim(BigDecimal.ZERO);
        simulacion.setDsctPersSim(BigDecimal.ZERO);
        simulacion.setIngrCombSim(BigDecimal.ZERO);
        simulacion.setDeudOtraSim(BigDecimal.ZERO);
        totalAporte=BigDecimal.ZERO;
        tipoSocio=true;
    }
    public SimulacionController() {
    }
    
    /*public void calcularDescuentoMaximo(){
        if(socio!=null){
            detalle=ejbEntidaddetFacade.findIdenEntiDet(socio.getEntiPagoSoc(), "ENTIPAGOSOC");
            if(detalle!=null){
                porcDescuento=detalle.getValoDecuDet();                
            }            
        }
    }*/
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
            JsfUtil.addErrorMessage("Ingrese Producto");
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
                tipoSocio=true;
            else
                tipoSocio=false;
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
    public boolean isTipoSocio() {
        return tipoSocio;
    }

    /**
     * @param tipoSocio the tipoSocio to set
     */
    public void setTipoSocio(boolean tipoSocio) {
        this.tipoSocio = tipoSocio;
    }
    
}
