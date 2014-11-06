package pe.gob.fovipol.sifo.controller.seguridad;

import pe.gob.fovipol.sifo.model.seguridad.AdmUsuarioRol;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.seguridad.AdmUsuarioRolFacade;

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

@Named("admUsuarioRolController")
@SessionScoped
public class AdmUsuarioRolController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.seguridad.AdmUsuarioRolFacade ejbFacade;
    private List<AdmUsuarioRol> items = null;
    private AdmUsuarioRol selected;

    public AdmUsuarioRolController() {
    }

    public AdmUsuarioRol getSelected() {
        return selected;
    }

    public void setSelected(AdmUsuarioRol selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmUsuarioRolFacade getFacade() {
        return ejbFacade;
    }

    public AdmUsuarioRol prepareCreate() {
        selected = new AdmUsuarioRol();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmUsuarioRolCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmUsuarioRolUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmUsuarioRolDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmUsuarioRol> getItems() {
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

    public AdmUsuarioRol getAdmUsuarioRol(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmUsuarioRol> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmUsuarioRol> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AdmUsuarioRol.class)
    public static class AdmUsuarioRolControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmUsuarioRolController controller = (AdmUsuarioRolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admUsuarioRolController");
            return controller.getAdmUsuarioRol(getKey(value));
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
            if (object instanceof AdmUsuarioRol) {
                AdmUsuarioRol o = (AdmUsuarioRol) object;
                return getStringKey(o.getIdenUsuaUro());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmUsuarioRol.class.getName()});
                return null;
            }
        }

    }

}
