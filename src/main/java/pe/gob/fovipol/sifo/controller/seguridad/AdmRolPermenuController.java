package pe.gob.fovipol.sifo.controller.seguridad;

import pe.gob.fovipol.sifo.model.seguridad.AdmRolPermenu;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.seguridad.AdmRolPermenuFacade;

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

@Named("admRolPermenuController")
@SessionScoped
public class AdmRolPermenuController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.seguridad.AdmRolPermenuFacade ejbFacade;
    private List<AdmRolPermenu> items = null;
    private AdmRolPermenu selected;

    public AdmRolPermenuController() {
    }

    public AdmRolPermenu getSelected() {
        return selected;
    }

    public void setSelected(AdmRolPermenu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmRolPermenuFacade getFacade() {
        return ejbFacade;
    }

    public AdmRolPermenu prepareCreate() {
        selected = new AdmRolPermenu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmRolPermenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmRolPermenuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmRolPermenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmRolPermenu> getItems() {
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

    public AdmRolPermenu getAdmRolPermenu(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmRolPermenu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmRolPermenu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AdmRolPermenu.class)
    public static class AdmRolPermenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmRolPermenuController controller = (AdmRolPermenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admRolPermenuController");
            return controller.getAdmRolPermenu(getKey(value));
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
            if (object instanceof AdmRolPermenu) {
                AdmRolPermenu o = (AdmRolPermenu) object;
                return getStringKey(o.getIdenRoleRpm());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmRolPermenu.class.getName()});
                return null;
            }
        }

    }

}
