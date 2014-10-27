package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;

@ManagedBean(name = "pruebaController")
@ViewScoped
public class PruebaController implements Serializable {
    private MaeSocio socio;
    private int mensaje;
    @PostConstruct
    public void init(){
        mensaje=1;
    }
    public PruebaController() {
    }
    public void aumentar(){
        mensaje++;
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
     * @return the mensaje
     */
    public int getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(int mensaje) {
        this.mensaje = mensaje;
    }
}
