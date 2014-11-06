package pe.gob.fovipol.sifo.controller.seguridad;

import pe.gob.fovipol.sifo.model.seguridad.AdmModuloRol;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.seguridad.AdmModuloRolFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("admModuloRolController")
@SessionScoped
public class AdmModuloRolController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.seguridad.AdmModuloRolFacade ejbFacade;
    private List<AdmModuloRol> items = null;
    private AdmModuloRol selected;

    public AdmModuloRolController() {
    }

    public AdmModuloRol getSelected() {
        return selected;
    }

    public void setSelected(AdmModuloRol selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmModuloRolFacade getFacade() {
        return ejbFacade;
    }

    public AdmModuloRol prepareCreate() {
        selected = new AdmModuloRol();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloRolCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloRolUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloRolDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmModuloRol> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public AdmModuloRol getAdmModuloRol(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmModuloRol> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmModuloRol> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AdmModuloRol.class)
    public static class AdmModuloRolControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmModuloRolController controller = (AdmModuloRolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admModuloRolController");
            return controller.getAdmModuloRol(getKey(value));
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
            if (object instanceof AdmModuloRol) {
                AdmModuloRol o = (AdmModuloRol) object;
                return getStringKey(o.getIdenMoroMdr());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmModuloRol.class.getName()});
                return null;
            }
        }

    }

}
