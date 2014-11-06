package pe.gob.fovipol.sifo.controller.seguridad;

import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.seguridad.AdmUsuarioFacade;

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

@Named("admUsuarioController")
@SessionScoped
public class AdmUsuarioController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.seguridad.AdmUsuarioFacade ejbFacade;
    private List<AdmUsuario> items = null;
    private AdmUsuario selected;

    public AdmUsuarioController() {
    }

    public AdmUsuario getSelected() {
        return selected;
    }

    public void setSelected(AdmUsuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public AdmUsuario prepareCreate() {
        selected = new AdmUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmUsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmUsuario> getItems() {
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

    public AdmUsuario getAdmUsuario(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AdmUsuario.class)
    public static class AdmUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmUsuarioController controller = (AdmUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admUsuarioController");
            return controller.getAdmUsuario(getKey(value));
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
            if (object instanceof AdmUsuario) {
                AdmUsuario o = (AdmUsuario) object;
                return getStringKey(o.getIdenPersPer());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmUsuario.class.getName()});
                return null;
            }
        }

    }

}
