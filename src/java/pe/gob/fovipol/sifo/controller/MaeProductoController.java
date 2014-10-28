package pe.gob.fovipol.sifo.controller;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;

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

@ManagedBean(name = "maeProductoController")
@ViewScoped
public class MaeProductoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeProductoFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
    private List<MaeProducto> items = null;
    private List<MaeProducto> itemsFiltro = null;
    private MaeProducto selected;
    private List<MaeEntidaddet> lineasProducto;

    public MaeProductoController() {
    }

    public MaeProducto getSelected() {
        return selected;
    }

    public void setSelected(MaeProducto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeProductoFacade getFacade() {
        return ejbFacade;
    }

    public MaeProducto prepareCreate() {
        selected = new MaeProducto();
        selected.setIdenProdPrd(new BigDecimal(ejbFacade.count()+1));
        selected.setFlagEstaPrd(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setFechCreaAud(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeProductoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setFechModAud(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeProductoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeProductoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeProducto> getItems() {
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

    public List<MaeProducto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeProducto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsFiltro
     */
    public List<MaeProducto> getItemsFiltro() {
        return itemsFiltro;
    }

    /**
     * @param itemsFiltro the itemsFiltro to set
     */
    public void setItemsFiltro(List<MaeProducto> itemsFiltro) {
        this.itemsFiltro = itemsFiltro;
    }

    /**
     * @return the lineasProducto
     */
    public List<MaeEntidaddet> getLineasProducto() {
        if(lineasProducto==null)
            lineasProducto=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("CODILINEPRD"));
        return lineasProducto;
    }

    /**
     * @param lineasProducto the lineasProducto to set
     */
    public void setLineasProducto(List<MaeEntidaddet> lineasProducto) {
        this.lineasProducto = lineasProducto;
    }

    @FacesConverter(forClass = MaeProducto.class)
    public static class MaeProductoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeProductoController controller = (MaeProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeProductoController");
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
            if (object instanceof MaeProducto) {
                MaeProducto o = (MaeProducto) object;
                return getStringKey(o.getIdenProdPrd());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeProducto.class.getName()});
                return null;
            }
        }

    }

}
