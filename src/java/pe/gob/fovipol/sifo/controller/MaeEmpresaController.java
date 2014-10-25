package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeEmpresa;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeEmpresaFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "maeEmpresaController")
@ViewScoped
public class MaeEmpresaController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEmpresaFacade ejbFacade;
    private List<MaeEmpresa> items = null;
    private List<MaeEmpresa> itemsFiltro = null;
    private MaeEmpresa selected;

    public MaeEmpresaController() {
    }

    public MaeEmpresa getSelected() {
        return selected;
    }

    public void setSelected(MaeEmpresa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeEmpresaFacade getFacade() {
        return ejbFacade;
    }

    public MaeEmpresa prepareCreate() {
        selected = new MaeEmpresa();        
        selected.setCodiEmprEmp(ejbFacade.obtenerCorrelativo());
        selected.setFlagEstaEmp(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setFechCreaAud(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeEmpresaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setFechModiAud(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeEmpresaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeEmpresaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeEmpresa> getItems() {
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

    public List<MaeEmpresa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeEmpresa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsFiltro
     */
    public List<MaeEmpresa> getItemsFiltro() {
        return itemsFiltro;
    }

    /**
     * @param itemsFiltro the itemsFiltro to set
     */
    public void setItemsFiltro(List<MaeEmpresa> itemsFiltro) {
        this.itemsFiltro = itemsFiltro;
    }

    @FacesConverter(forClass = MaeEmpresa.class)
    public static class MaeEmpresaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeEmpresaController controller = (MaeEmpresaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeEmpresaController");
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
            if (object instanceof MaeEmpresa) {
                MaeEmpresa o = (MaeEmpresa) object;
                return getStringKey(o.getCodiEmprEmp());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeEmpresa.class.getName()});
                return null;
            }
        }

    }

}
