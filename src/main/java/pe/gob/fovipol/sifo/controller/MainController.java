package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pe.gob.fovipol.sifo.bean.sesion.Menu;
import pe.gob.fovipol.sifo.bean.sesion.MenuDetalle;
import pe.gob.fovipol.sifo.bean.sesion.SesionUsuario;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenu;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;

@ManagedBean(name = "mainPage")
@ViewScoped
public class MainController implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{sesionUsuario}")
    private SesionUsuario sesionUsuario;
    List<MenuDetalle> actual;
    List<AdmMenu> itemMenus;
    private String titulo;
    private boolean esPrimer;
    private boolean esSegundo;
    private boolean mostrar;
    private String tituloPrimero;
    private String tituloSegundo;

    @PostConstruct
    public void init() {
        mostrar = false;
        String nivel1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivel1");
        String nivel2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivel2");
        AdmModulo aux2 = new AdmModulo();
        if (nivel1 == null) {
            mostrar = false;
        } else {
            mostrar = true;
            aux2 = buscarModulo(new BigDecimal(nivel1));
            if (aux2 == null) {
                return;
            }
        }
        if (nivel2 != null) {
            AdmMenu aux = buscarItem(new BigDecimal(nivel2));
            if (aux == null) {
                return;
            }
            itemMenus = listarItem();
            titulo = aux.getDescPrmtMnu();
            esPrimer = false;
            tituloSegundo = aux.getNombMenuMnu();
            tituloPrimero = aux2.getNombModuMod();
            esSegundo = true;
        } else {
            esSegundo = false;
            if (nivel1 != null) {
                itemMenus = listarItem();
                titulo = aux2.getNombModuMod();
                setEsPrimer(true);
                tituloSegundo = "";
                tituloPrimero = aux2.getNombModuMod();
            }
        }
        if(itemMenus!=null && !itemMenus.isEmpty()){
            for(AdmMenu men:itemMenus){
            }
        }
    }

    public AdmModulo buscarModulo(BigDecimal idModulo) {
        Menu aux = null;
        boolean encontrado = false;
        for (Menu menu : getSesionUsuario().getMenus()) {
            if (menu.getModulo().getIdenModuMod().compareTo(idModulo) == 0) {
                encontrado = true;
                aux = menu;
                actual = menu.getMenuItems();
            }
        }
        if (encontrado) {
            return aux.getModulo();
        } else {
            return null;
        }
    }

    public List<AdmMenu> listarItem() {
        List<AdmMenu> aux = new ArrayList<>();
        for (MenuDetalle detalle : actual) {
            aux.add(detalle.getMenuItem());
        }
        return aux;
    }

    public AdmMenu buscarItem(BigDecimal idItem) {
        AdmMenu aux = null;
        boolean encontrado = false;
        int i = 0;
        if (actual == null) {
            return aux;
        } else {
            while (i < actual.size() && !encontrado) {
                MenuDetalle mdet = actual.get(i);
                if (mdet.getMenuItem().getIdenMenuMnu().compareTo(idItem) == 0) {
                    encontrado = true;
                    aux = mdet.getMenuItem();
                    actual = mdet.getMenuDetalles();
                }
                i++;
            }
            return aux;
        }
    }

    public List<AdmMenu> getItemMenus() {
        return itemMenus;
    }

    public void setItemMenus(List<AdmMenu> itemMenus) {
        this.itemMenus = itemMenus;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isEsPrimer() {
        return esPrimer;
    }

    public void setEsPrimer(boolean esPrimer) {
        this.esPrimer = esPrimer;
    }

    public boolean isEsSegundo() {
        return esSegundo;
    }

    public void setEsSegundo(boolean esSegundo) {
        this.esSegundo = esSegundo;
    }

    public String getTituloPrimero() {
        return tituloPrimero;
    }

    public void setTituloPrimero(String tituloPrimero) {
        this.tituloPrimero = tituloPrimero;
    }

    public String getTituloSegundo() {
        return tituloSegundo;
    }

    public void setTituloSegundo(String tituloSegundo) {
        this.tituloSegundo = tituloSegundo;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public List<MenuDetalle> getActual() {
        return actual;
    }

    public void setActual(List<MenuDetalle> actual) {
        this.actual = actual;
    }

    /**
     * @return the sesionUsuario
     */
    public SesionUsuario getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * @param sesionUsuario the sesionUsuario to set
     */
    public void setSesionUsuario(SesionUsuario sesionUsuario) {
        this.sesionUsuario = sesionUsuario;
    }
}
