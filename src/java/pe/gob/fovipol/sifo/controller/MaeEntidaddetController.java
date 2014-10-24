package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.MaeEntidaddet;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.SelectableDataModel;

@ManagedBean(name = "maeEntidaddetController")
@ViewScoped
public class MaeEntidaddetController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbFacade;
    private List<MaeEntidaddet> items = null;
    private MaeEntidaddet selected;

    public MaeEntidaddetController() {
    }

    public MaeEntidaddet getSelected() {
        return selected;
    }

    public void setSelected(MaeEntidaddet selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeEntidaddetFacade getFacade() {
        return ejbFacade;
    }

    public MaeEntidaddet prepareCreate() {
        selected = new MaeEntidaddet();
        initializeEmbeddableKey();
        return selected;
    }
    
    public MaeEntidaddet preparaCrear(MaeEntidaddet item) {
        item = new MaeEntidaddet();
        BigDecimal id = new BigDecimal( ejbFacade.obtenerCorrelativo().intValue() );
        item.setIdenEntiDet( id);
        initializeEmbeddableKey();
        return item;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidaddetCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void creaDetalle(MaeEntidaddet item) {
        selected = item;
        selected.setFechCreaDet(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidaddetCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidaddetUpdated"));
    }
    
    public void actualiza(MaeEntidaddet item) {
        selected = item;
        selected.setFechModiDet(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidaddetUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidaddetDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void destruir(MaeEntidaddet item) {        
        selected = item;
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidaddetDeleted"));        
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
    }

    public List<MaeEntidaddet> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<MaeEntidaddet> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeEntidaddet> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MaeEntidaddet.class)
    public static class MaeEntidaddetControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeEntidaddetController controller = (MaeEntidaddetController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeEntidaddetController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof MaeEntidaddet) {
                MaeEntidaddet o = (MaeEntidaddet) object;
                return getStringKey(o.getIdenEntiDet());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeEntidaddet.class.getName()});
                return null;
            }
        }

    }

}
