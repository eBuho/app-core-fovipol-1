package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeProcesoFacade;

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

@ManagedBean(name = "maeProcesoController")
@ViewScoped
public class MaeProcesoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeProcesoFacade ejbFacade;
    private List<MaeProceso> items = null;
    private List<MaeProceso> itemsFiltro = null;
    private List<MaeProceso> itemsFiltroSubProceso = null;
    private List<MaeProceso> itemsFiltroActividad = null;
    private List<MaeProceso> itemsSubProceso = null;
    private List<MaeProceso> itemsActividad = null;
    private MaeProceso selectedProceso;
    private MaeProceso selectedSubProceso;
    private MaeProceso selectedActividad;
    private MaeProceso selected;

    public MaeProcesoController() {
    }
    
    public void cargarSubProcesos(){
        itemsSubProceso=ejbFacade.findProcesosHijos(selectedProceso);
        itemsActividad=null;
        selectedSubProceso=null;
        selectedActividad=null;
    }
    public void cargarActividad(){
        itemsActividad=ejbFacade.findProcesosHijos(selectedSubProceso);
        selectedActividad=null;
    }
    public MaeProceso getSelected() {
        return selected;
    }

    public void setSelected(MaeProceso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeProcesoFacade getFacade() {
        return ejbFacade;
    }

    public MaeProceso prepareCreate() {
        selected = new MaeProceso();
        selected.setCodiProcPrc(new BigDecimal(ejbFacade.count()+1));
        selected.setNiveProcPrc(new Short("1"));
        selected.setFlagEstaPrc(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }
    public MaeProceso prepareCreateSubProceso() {
        selected = new MaeProceso();
        selected.setCodiProcPrc(new BigDecimal(ejbFacade.count()+1));
        selected.setNiveProcPrc(new Short("2"));
        selected.setFlagEstaPrc(new Short("1"));
        selected.setCodiPropPrc(selectedProceso);
        initializeEmbeddableKey();
        return selected;
    }
    public MaeProceso prepareCreateActividad() {
        selected = new MaeProceso();
        selected.setCodiProcPrc(new BigDecimal(ejbFacade.count()+1));
        selected.setNiveProcPrc(new Short("3"));
        selected.setFlagEstaPrc(new Short("1"));
        selected.setCodiPropPrc(selectedSubProceso);
        initializeEmbeddableKey();
        return selected;
    }
    public void modificarProceso(){
        selected=selectedProceso;
    }
    public void modificarSubProceso(){
        selected=selectedSubProceso;
    }
    public void modificarActividad(){
        selected=selectedActividad;
    }
    public void create() {
        selected.setFechCreaAud(new Date());        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeProcesoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            if(selected.getNiveProcPrc()==1)
                items = ejbFacade.findProcesos();
            if(selected.getNiveProcPrc()==2)
                cargarSubProcesos();
            if(selected.getNiveProcPrc()==3)
                cargarActividad();
            // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setFechModiAud(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeProcesoUpdated"));
    }

    public void destroy() {
        selected=selectedProceso;
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeProcesoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void destroySubProceso() {
        selected=selectedSubProceso;
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeProcesoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            cargarSubProcesos();   // Invalidate list of items to trigger re-query.
        }
    }
    public void destroyActividad() {
        selected=selectedActividad;
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeProcesoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            cargarActividad();    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeProceso> getItems() {
        if (items == null) {
            items = getFacade().findProcesos();
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
            }            
            catch (EJBException ex) {
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

    public List<MaeProceso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeProceso> getItemsAvailableSelectOne() {
        return getFacade().findProcesosActivos();
    }

    /**
     * @return the itemsFiltro
     */
    public List<MaeProceso> getItemsFiltro() {
        return itemsFiltro;
    }

    /**
     * @param itemsFiltro the itemsFiltro to set
     */
    public void setItemsFiltro(List<MaeProceso> itemsFiltro) {
        this.itemsFiltro = itemsFiltro;
    }

    /**
     * @return the itemsSubProceso
     */
    public List<MaeProceso> getItemsSubProceso() {
        return itemsSubProceso;
    }

    /**
     * @param itemsSubProceso the itemsSubProceso to set
     */
    public void setItemsSubProceso(List<MaeProceso> itemsSubProceso) {
        this.itemsSubProceso = itemsSubProceso;
    }

    /**
     * @return the itemsActividad
     */
    public List<MaeProceso> getItemsActividad() {
        return itemsActividad;
    }

    /**
     * @param itemsActividad the itemsActividad to set
     */
    public void setItemsActividad(List<MaeProceso> itemsActividad) {
        this.itemsActividad = itemsActividad;
    }

    /**
     * @return the selectedProceso
     */
    public MaeProceso getSelectedProceso() {
        return selectedProceso;
    }

    /**
     * @param selectedProceso the selectedProceso to set
     */
    public void setSelectedProceso(MaeProceso selectedProceso) {
        this.selectedProceso = selectedProceso;
    }

    /**
     * @return the selectedSubProceso
     */
    public MaeProceso getSelectedSubProceso() {
        return selectedSubProceso;
    }

    /**
     * @param selectedSubProceso the selectedSubProceso to set
     */
    public void setSelectedSubProceso(MaeProceso selectedSubProceso) {
        this.selectedSubProceso = selectedSubProceso;
    }

    /**
     * @return the selectedActividad
     */
    public MaeProceso getSelectedActividad() {
        return selectedActividad;
    }

    /**
     * @param selectedActividad the selectedActividad to set
     */
    public void setSelectedActividad(MaeProceso selectedActividad) {
        this.selectedActividad = selectedActividad;
    }

    /**
     * @return the itemsFiltroSubProceso
     */
    public List<MaeProceso> getItemsFiltroSubProceso() {
        return itemsFiltroSubProceso;
    }

    /**
     * @param itemsFiltroSubProceso the itemsFiltroSubProceso to set
     */
    public void setItemsFiltroSubProceso(List<MaeProceso> itemsFiltroSubProceso) {
        this.itemsFiltroSubProceso = itemsFiltroSubProceso;
    }

    /**
     * @return the itemsFiltroActividad
     */
    public List<MaeProceso> getItemsFiltroActividad() {
        return itemsFiltroActividad;
    }

    /**
     * @param itemsFiltroActividad the itemsFiltroActividad to set
     */
    public void setItemsFiltroActividad(List<MaeProceso> itemsFiltroActividad) {
        this.itemsFiltroActividad = itemsFiltroActividad;
    }

    @FacesConverter(forClass = MaeProceso.class)
    public static class MaeProcesoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeProcesoController controller = (MaeProcesoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeProcesoController");
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
            if (object instanceof MaeProceso) {
                MaeProceso o = (MaeProceso) object;
                return getStringKey(o.getCodiProcPrc());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeProceso.class.getName()});
                return null;
            }
        }

    }

}
