package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeArea;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeAreaFacade;

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
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;

@ManagedBean(name = "maeAreaController")
@ViewScoped
public class MaeAreaController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeAreaFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
    private List<MaeArea> items = null;
    private List<MaeArea> itemsFiltro = null;
    private List<MaeEntidaddet> tiposArea;
    private MaeArea selected;

    public MaeAreaController() {
    }

    public MaeArea getSelected() {
        return selected;
    }

    public void setSelected(MaeArea selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeAreaFacade getFacade() {
        return ejbFacade;
    }

    public MaeArea prepareCreate() {
        selected = new MaeArea();
        selected.setCodiAreaAre(new BigDecimal(ejbFacade.count()+1));
        selected.setFlagEstaAre(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setFechCreaAud(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeAreaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setFechModiAud(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeAreaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeAreaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeArea> getItems() {
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

    public List<MaeArea> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeArea> getItemsAvailableSelectOne() {
        return getFacade().findAllActivo();
    }

    /**
     * @return the itemsFiltro
     */
    public List<MaeArea> getItemsFiltro() {
        return itemsFiltro;
    }

    /**
     * @param itemsFiltro the itemsFiltro to set
     */
    public void setItemsFiltro(List<MaeArea> itemsFiltro) {
        this.itemsFiltro = itemsFiltro;
    }

    /**
     * @return the tiposArea
     */
    public List<MaeEntidaddet> getTiposArea() {
        if(tiposArea==null)
            tiposArea=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("TipoAreaAre"));
        return tiposArea;
    }

    /**
     * @param tiposArea the tiposArea to set
     */
    public void setTiposArea(List<MaeEntidaddet> tiposArea) {
        this.tiposArea = tiposArea;
    }

    @FacesConverter(forClass = MaeArea.class)
    public static class MaeAreaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeAreaController controller = (MaeAreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeAreaController");
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
            if (object instanceof MaeArea) {
                MaeArea o = (MaeArea) object;
                return getStringKey(o.getCodiAreaAre());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeArea.class.getName()});
                return null;
            }
        }

    }

}
