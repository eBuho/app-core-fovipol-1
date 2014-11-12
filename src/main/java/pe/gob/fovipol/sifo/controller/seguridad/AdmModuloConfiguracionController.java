package pe.gob.fovipol.sifo.controller.seguridad;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.dao.seguridad.AdmMenuModuloFacade;
import pe.gob.fovipol.sifo.dao.seguridad.AdmModuloFacade;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenuModulo;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;


@ManagedBean(name = "admModuloConfiguracionController")
@ViewScoped
public class AdmModuloConfiguracionController implements Serializable {

    @EJB
    private AdmModuloFacade ejbFacade;
    @EJB
    private AdmMenuModuloFacade ejbMenuModuloFacade;
    private List<AdmModulo> items;
    private AdmModulo selected;
    private List<AdmMenuModulo> menuModulos;

    @PostConstruct
    public void init() {
        items = getFacade().findAll();
    }
    
    public void cargarMenus(){
        menuModulos=ejbMenuModuloFacade.findAll();
    }
    public AdmModulo getSelected() {
        return selected;
    }

    public void setSelected(AdmModulo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmModuloFacade getFacade() {
        return ejbFacade;
    }

    public AdmModulo prepareCreate() {
        selected = new AdmModulo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmModulo> getItems() {
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Seguridad").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Seguridad").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AdmModulo getAdmModulo(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmModulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmModulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the menuModulos
     */
    public List<AdmMenuModulo> getMenuModulos() {
        return menuModulos;
    }

    /**
     * @param menuModulos the menuModulos to set
     */
    public void setMenuModulos(List<AdmMenuModulo> menuModulos) {
        this.menuModulos = menuModulos;
    }

    @FacesConverter(forClass = AdmModulo.class)
    public static class AdmModuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmModuloController controller = (AdmModuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admModuloController");
            return controller.getAdmModulo(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AdmModulo) {
                AdmModulo o = (AdmModulo) object;
                return getStringKey(o.getIdenModuMod());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmModulo.class.getName()});
                return null;
            }
        }

    }

}
