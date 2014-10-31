package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeSocioFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

@ManagedBean(name = "registrarExpedienteController")
@ViewScoped
public class RegistrarExpedienteController implements Serializable{    
    @EJB
    private MaeSocioFacade ejbSocioFacade;
    @EJB
    private MaeEntidaddetFacade ejbEntidadDetalleFacade;
    private String cip;
    private MaeSocio socio;
    private TrmTramite tramite;
    private List<MaeEntidaddet> tiposTramite;
    private List<MaeEntidaddet> tiposPrioridad;
    private List<MaeEntidaddet> modalidadesTramite;
    @PostConstruct
    public void init(){
        tramite=new TrmTramite();
    }
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
        if(tiposTramite==null)
            tiposTramite=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("TIPOTRAMTRM"));
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
        if(modalidadesTramite==null)
            modalidadesTramite=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("CODIMODATRM"));
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
        if(tiposPrioridad==null)
            tiposPrioridad=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("CODIPRIOTRM"));
        return tiposPrioridad;
    }

    /**
     * @param tiposPrioridad the tiposPrioridad to set
     */
    public void setTiposPrioridad(List<MaeEntidaddet> tiposPrioridad) {
        this.tiposPrioridad = tiposPrioridad;
    }
}
