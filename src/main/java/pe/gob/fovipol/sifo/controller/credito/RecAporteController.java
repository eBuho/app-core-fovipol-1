package pe.gob.fovipol.sifo.controller.credito;

import pe.gob.fovipol.sifo.model.recuperaciones.RecAporte;
import pe.gob.fovipol.sifo.controller.credito.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.credito.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.recuperaciones.RecAporteFacade;

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

@Named("recAporteController")
@SessionScoped
public class RecAporteController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.recuperaciones.RecAporteFacade ejbFacade;
    private List<RecAporte> items = null;
    private RecAporte selected;

    public RecAporteController() {
    }

    public RecAporte getSelected() {
        return selected;
    }

    public void setSelected(RecAporte selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RecAporteFacade getFacade() {
        return ejbFacade;
    }

    public RecAporte prepareCreate() {
        selected = new RecAporte();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Consulta2").getString("RecAporteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Consulta2").getString("RecAporteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Consulta2").getString("RecAporteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RecAporte> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Consulta2").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Consulta2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public RecAporte getRecAporte(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<RecAporte> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RecAporte> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = RecAporte.class)
    public static class RecAporteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecAporteController controller = (RecAporteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recAporteController");
            return controller.getRecAporte(getKey(value));
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
            if (object instanceof RecAporte) {
                RecAporte o = (RecAporte) object;
                return getStringKey(o.getIdenAporApo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RecAporte.class.getName()});
                return null;
            }
        }

    }

}
