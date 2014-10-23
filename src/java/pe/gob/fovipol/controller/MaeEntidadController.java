package pe.gob.fovipol.controller;

import pe.gob.fovipol.model.MaeEntidad;
import pe.gob.fovipol.controller.util.JsfUtil;
import pe.gob.fovipol.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.dao.MaeEntidadFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SelectableDataModel;
import pe.gob.fovipol.model.MaeEntidaddet;

@ManagedBean(name = "maeEntidadController")
@ViewScoped
public class MaeEntidadController implements Serializable {

    @EJB
    private pe.gob.fovipol.dao.MaeEntidadFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.dao.MaeEntidaddetFacade ejbFacadeDetalle;
    // Propiedades
    private List<MaeEntidad> items = null;
    private MaeEntidad selected;
    private List<MaeEntidaddet> itemsDetalle;
    private MaeEntidaddet detalleSeleccionado;
    private MaeEntidaddet detalleNuevo;

    public MaeEntidadController() {
        itemsDetalle = new ArrayList<>();
        detalleSeleccionado = new MaeEntidaddet();
        detalleNuevo = new MaeEntidaddet();
    }

    public MaeEntidad getSelected() {
        return selected;
    }

    public void setSelected(MaeEntidad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeEntidadFacade getFacade() {
        return ejbFacade;
    }

    public void onRowSelect(SelectEvent event) {
        MaeEntidad padre = (MaeEntidad) event.getObject();
        FacesMessage msg = new FacesMessage("Maestro Seleccionado", 
                ((MaeEntidad) event.getObject()).getCodiEntiEnt());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        List<MaeEntidaddet> lista = ejbFacadeDetalle.findDetalle(padre);
        setItemsDetalle(lista);
        
    }
 
    public void actualizarDetalle(){
        List<MaeEntidaddet> lista = ejbFacadeDetalle.findDetalle(selected);
        setItemsDetalle(lista);
    }
    
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Maestro Deseleccionado", 
                ((MaeEntidad) event.getObject()).getCodiEntiEnt());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public MaeEntidad prepareCreate() {
        selected = new MaeEntidad();
        selected.setFlagEstaEnt(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }
    
    public void preparaCrear() {
        detalleNuevo = new MaeEntidaddet();        
        detalleNuevo.setIdenEntiDet(ejbFacadeDetalle.obtenerCorrelativo());
        detalleNuevo.setCodiEntiEnt(selected);
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidadUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeEntidadDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeEntidad> getItems() {
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

    public List<MaeEntidad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeEntidad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsDetalle
     */
    public List<MaeEntidaddet> getItemsDetalle() {
        return itemsDetalle;
    }

    /**
     * @param itemsDetalle the itemsDetalle to set
     */
    public void setItemsDetalle(List<MaeEntidaddet> itemsDetalle) {
        this.itemsDetalle = itemsDetalle;
    }

    /**
     * @return the detalleSeleccionado
     */
    public MaeEntidaddet getDetalleSeleccionado() {
        return detalleSeleccionado;
    }

    /**
     * @param detalleSeleccionado the detalleSeleccionado to set
     */
    public void setDetalleSeleccionado(MaeEntidaddet detalleSeleccionado) {
        this.detalleSeleccionado = detalleSeleccionado;
    }

    /**
     * @return the detalleNuevo
     */
    public MaeEntidaddet getDetalleNuevo() {
        return detalleNuevo;
    }

    /**
     * @param detalleNuevo the detalleNuevo to set
     */
    public void setDetalleNuevo(MaeEntidaddet detalleNuevo) {
        this.detalleNuevo = detalleNuevo;
    }

    @FacesConverter(forClass = MaeEntidad.class)
    public static class MaeEntidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeEntidadController controller = (MaeEntidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeEntidadController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaeEntidad) {
                MaeEntidad o = (MaeEntidad) object;
                return getStringKey(o.getCodiEntiEnt());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeEntidad.class.getName()});
                return null;
            }
        }

    }

}
