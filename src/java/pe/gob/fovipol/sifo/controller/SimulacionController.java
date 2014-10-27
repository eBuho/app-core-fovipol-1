package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;

@ManagedBean(name = "simulacionController")
@ViewScoped
public class SimulacionController implements Serializable {
    private MaeSocio socio;
    private List<MaeProducto> productos;
    private MaeProducto producto;
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @PostConstruct
    public void init(){
    }
    public SimulacionController() {
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
}
