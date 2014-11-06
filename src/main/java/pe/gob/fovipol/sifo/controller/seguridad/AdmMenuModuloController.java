package pe.gob.fovipol.sifo.controller.seguridad;

import pe.gob.fovipol.sifo.model.seguridad.AdmMenuModulo;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.seguridad.AdmMenuModuloFacade;

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

@Named("admMenuModuloController")
@SessionScoped
public class AdmMenuModuloController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.seguridad.AdmMenuModuloFacade ejbFacade;
    private List<AdmMenuModulo> items = null;
    private AdmMenuModulo selected;

    public AdmMenuModuloController() {
    }

    public AdmMenuModulo getSelected() {
        return selected;
    }

    public void setSelected(AdmMenuModulo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmMenuModuloFacade getFacade() {
        return ejbFacade;
    }

    public AdmMenuModulo prepareCreate() {
        selected = new AdmMenuModulo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmMenuModuloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmMenuModuloUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmMenuModuloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmMenuModulo> getItems() {
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

    public AdmMenuModulo getAdmMenuModulo(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmMenuModulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmMenuModulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AdmMenuModulo.class)
    public static class AdmMenuModuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdmMenuModuloController controller = (AdmMenuModuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "admMenuModuloController");
            return controller.getAdmMenuModulo(getKey(value));
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
            if (object instanceof AdmMenuModulo) {
                AdmMenuModulo o = (AdmMenuModulo) object;
                return getStringKey(o.getIdenMemoMmd());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AdmMenuModulo.class.getName()});
                return null;
            }
        }

    }

}
