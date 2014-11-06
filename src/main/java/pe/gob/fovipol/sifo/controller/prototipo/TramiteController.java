/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller.prototipo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import pe.gob.fovipol.sifo.model.general.TipoTramite;
import pe.gob.fovipol.sifo.model.general.Tramite;
import pe.gob.fovipol.sifo.service.AuditoriaService;

/**
 *
 * @author ebuho
 */
@ManagedBean(name = "tramiteController")
@SessionScoped
public class TramiteController implements Serializable {
    @EJB
    AuditoriaService auditoriaService;
    private Tramite seleccionado;
    private List<Tramite> lista;
    private List<Tramite> listaSeleccionados;
    private TipoTramite tipo;
    private String pagina = "";

    /**
     * Creates a new instance of TramiteController
     */
    public TramiteController() {
        lista = new ArrayList<>();
        TipoTramite tipo1 = new TipoTramite(1, "Oficio", "oficio", "Activo");
        Tramite t1 = new Tramite(Long.parseLong("1"), tipo1, "Oficio 1", "2014-10-23", "Activo");
        TipoTramite tipo2 = new TipoTramite(2, "Solicitud", "solicitud", "Activo");
        Tramite t2 = new Tramite(Long.parseLong("2"), tipo2, "Solicitud 1", "2014-10-24", "Activo");
        TipoTramite tipo3 = new TipoTramite(3, "Carta", "carta", "Activo");
        Tramite t3 = new Tramite(Long.parseLong("3"), tipo3, "Carta 1", "2014-10-25", "Activo");
        seleccionado = t1;
        tipo = seleccionado.getTipo();
        pagina = tipo.getPagina();
        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
    }

    public void obtenerIP() {  
        HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().
                getExternalContext().getRequest());

        // Obtener los atributos de cabecera. 
        // Los usamos para recuperar los valores reales.
        request.getHeaderNames();

        // Obtener la dirección IP del cliente.  
        
        String ip = auditoriaService.obtenerIP(); //request.getRemoteAddr();
        //System.out.println("ip:" + ip);
        // Obtener el nombre de host del cliente.  
        String pc = auditoriaService.obtenerEquipo(); // request.getRemoteHost();
        //System.out.println("pc:" + pc);
    }

    public void onRowSelect(SelectEvent event) {
        Tramite tramite = (Tramite) event.getObject();

        FacesMessage msg = new FacesMessage("Tramite Seleccionado",
                "" + tramite.getId() + " " + tramite.getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        //int tipo = tramite.getTipo().getId();
        tipo = tramite.getTipo();
        pagina = tipo.getPagina();
    }

    public void onRowUnselect(UnselectEvent event) {
        Tramite tramite = (Tramite) event.getObject();
        FacesMessage msg = new FacesMessage("Tramite Deseleccionado",
                "" + tramite.getId() + " " + tramite.getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the seleccionado
     */
    public Tramite getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(Tramite seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the lista
     */
    public List<Tramite> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Tramite> lista) {
        this.lista = lista;
    }

    /**
     * @return the listaSeleccionados
     */
    public List<Tramite> getListaSeleccionados() {
        return listaSeleccionados;
    }

    /**
     * @param listaSeleccionados the listaSeleccionados to set
     */
    public void setListaSeleccionados(List<Tramite> listaSeleccionados) {
        this.listaSeleccionados = listaSeleccionados;
    }

    /**
     * @return the tipo
     */
    public TipoTramite getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoTramite tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

}
