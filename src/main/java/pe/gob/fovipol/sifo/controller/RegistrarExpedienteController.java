package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.dao.MaeSocioFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;

@ManagedBean(name = "registrarExpedienteController")
@ViewScoped
public class RegistrarExpedienteController implements Serializable{
    private String cip;
    private MaeSocio socio;
    @EJB
    private MaeSocioFacade ejbSocioFacade;
    public void buscarSocio(){
        List<MaeSocio> socios=ejbSocioFacade.findByCIP(cip);
        if(socios!=null && !socios.isEmpty())
            socio=socios.get(0);
        else
            socio=null;
    }
    /**
     * @return the cip
     */
    public String getCip() {
        return cip;
    }

    /**
     * @param cip the cip to set
     */
    public void setCip(String cip) {
        this.cip = cip;
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
}
