package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeRequisito;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeRequisitoFacade;

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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;

@ManagedBean(name = "maeRequisitoController")
@SessionScoped
public class MaeRequisitoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeRequisitoFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
    private List<MaeRequisito> items = null;
    private List<MaeRequisito> itemsFiltro = null;
    private List<MaeEntidaddet> tiposRequisito;
    private MaeRequisito selected;

    public MaeRequisitoController() {
    }

    public MaeRequisito getSelected() {
        return selected;
    }

    public void setSelected(MaeRequisito selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeRequisitoFacade getFacade() {
        return ejbFacade;
    }

    public MaeRequisito prepareCreate() {
        selected = new MaeRequisito();
        selected.setIdenMaeRequisito(new BigDecimal(ejbFacade.count()+1));
        selected.setFlagEstaReq(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setFechCreaAud(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeRequisitoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setFechModiAud(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeRequisitoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeRequisitoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeRequisito> getItems() {
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

    public List<MaeRequisito> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeRequisito> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsFiltro
     */
    public List<MaeRequisito> getItemsFiltro() {
        return itemsFiltro;
    }

    /**
     * @param itemsFiltro the itemsFiltro to set
     */
    public void setItemsFiltro(List<MaeRequisito> itemsFiltro) {
        this.itemsFiltro = itemsFiltro;
    }

    /**
     * @return the tiposRequisito
     */
    public List<MaeEntidaddet> getTiposRequisito() {
        if(tiposRequisito==null)
            tiposRequisito=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("CODITIPOREQ"));
        return tiposRequisito;
    }

    /**
     * @param tiposRequisito the tiposRequisito to set
     */
    public void setTiposRequisito(List<MaeEntidaddet> tiposRequisito) {
        this.tiposRequisito = tiposRequisito;
    }

    @FacesConverter(forClass = MaeRequisito.class)
    public static class MaeRequisitoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeRequisitoController controller = (MaeRequisitoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeRequisitoController");
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
            if (object instanceof MaeRequisito) {
                MaeRequisito o = (MaeRequisito) object;
                return getStringKey(o.getIdenMaeRequisito());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeRequisito.class.getName()});
                return null;
            }
        }

    }

}
