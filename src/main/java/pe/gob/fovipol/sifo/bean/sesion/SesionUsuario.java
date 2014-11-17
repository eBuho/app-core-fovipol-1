/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.bean.sesion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.gob.fovipol.sifo.dao.seguridad.AdmMenuFacade;
import pe.gob.fovipol.sifo.dao.seguridad.AdmModuloFacade;
import pe.gob.fovipol.sifo.dao.seguridad.AdmUsuarioFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeArea;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenu;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;

/**
 *
 * @author probook
 */
@ManagedBean(name = "sesionUsuario")
@SessionScoped
public class SesionUsuario implements Serializable {

    private AdmUsuario usuario;
    private List<Menu> menus;
    private List<AdmModulo> modulos;
    private MaeArea area;
    @EJB
    AdmUsuarioFacade ejbAdmUsuarioFacade;
    @EJB
    AdmModuloFacade ejbAdmModuloFacade;
    @EJB
    AdmMenuFacade ejbAdmMenuFacade;

    @PostConstruct
    public void init() {
        menus = new ArrayList<>();
        usuario = new AdmUsuario();
    }

    public void actualizarSesion() {
        String nombre = SecurityContextHolder.getContext().getAuthentication().getName();
        if (nombre == null || !nombre.equals(usuario.getCodiUsuaUsr())) {
            usuario = ejbAdmUsuarioFacade.findByUsuario(nombre);
            try{
                area=usuario.getMaePersona().getMaeEmpleado().getIdenAreaAre();
            }
            catch(NullPointerException npe){
                area=null;
            }
            modulos = ejbAdmModuloFacade.findByUsuario(usuario);
            for (AdmModulo modulo : modulos) {
                Menu m = new Menu();
                m.setModulo(modulo);
                m.setMenuItems(llenarMenu(modulo.getIdenModuMod()));
                menus.add(m);
            }
        }
    }

    public List<MenuDetalle> llenarMenu(BigDecimal idModulo) {
        List<AdmMenu> lista = ejbAdmMenuFacade.listarPorModuloExcepcion(idModulo, usuario.getIdenPersPer());
        List<MenuDetalle> detalles = new ArrayList<>();
        for (AdmMenu mi : lista) {
            MenuDetalle menu = new MenuDetalle();
            menu.setMenuItem(mi);
            //menu.setMenuDetalles(llenarRecursivo(idModulo, mi.getId().getId()));
            detalles.add(menu);
        }
        return detalles;
    }

    /*public List<MenuDetalle> llenarRecursivo(String idModulo, String idItem) {
        List<MenuItem> lista = menuItemService.listarPorNivelModuloExcepcion(idItem, idModulo, usuario.getIdusuario());
        List<MenuDetalle> detalles = new ArrayList<MenuDetalle>();
        for (MenuItem mi : lista) {
            MenuDetalle menu = new MenuDetalle();
            menu.setMenuItem(mi);
            menu.setMenuDetalles(llenarRecursivo(idModulo, mi.getId().getId()));
            detalles.add(menu);
        }
        return detalles;
    }*/

    /**
     * @return the usuario
     */
    public AdmUsuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(AdmUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the menus
     */
    public List<Menu> getMenus() {
        actualizarSesion();
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * @return the modulos
     */
    public List<AdmModulo> getModulos() {
        return modulos;
    }

    /**
     * @param modulos the modulos to set
     */
    public void setModulos(List<AdmModulo> modulos) {
        this.modulos = modulos;
    }

    /**
     * @return the area
     */
    public MaeArea getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(MaeArea area) {
        this.area = area;
    }
}
