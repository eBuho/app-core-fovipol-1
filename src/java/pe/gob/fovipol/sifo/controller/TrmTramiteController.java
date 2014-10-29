package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.TrmTramiteFacade;

import java.io.Serializable;
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

@ManagedBean(name = "trmTramiteController")
@SessionScoped
public class TrmTramiteController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.TrmTramiteFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
    
    private List<TrmTramite> items = null;
    private TrmTramite selected;
    
    private List<MaeEntidaddet> listaTiposTramite;      // TIPOTRAMTRM
    private List<MaeEntidaddet> listaCodigoOrigen;      // CODIORIGTRM
    private List<MaeEntidaddet> listaModalidadTramite;  // CODIMODATRM
    private List<MaeEntidaddet> listaCodigoPrioridad;   // CODIPRIOTRM

    public TrmTramiteController() {
    }

    public TrmTramite getSelected() {
        return selected;
    }

    public void setSelected(TrmTramite selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TrmTramiteFacade getFacade() {
        return getEjbFacade();
    }

    public TrmTramite prepareCreate() {
        setSelected(new TrmTramite());
        initializeEmbeddableKey();
        return getSelected();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Tramite").getString("TrmTramiteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            setItems(null);    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Tramite").getString("TrmTramiteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Tramite").getString("TrmTramiteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            setSelected(null); // Remove selection
            setItems(null);    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TrmTramite> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (getSelected() != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(getSelected());
                } else {
                    getFacade().remove(getSelected());
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Tramite").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Tramite").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<TrmTramite> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TrmTramite> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the ejbFacade
     */
    public pe.gob.fovipol.sifo.dao.TrmTramiteFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(pe.gob.fovipol.sifo.dao.TrmTramiteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ejbEntidadDetalleFacade
     */
    public pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade getEjbEntidadDetalleFacade() {
        return ejbEntidadDetalleFacade;
    }

    /**
     * @param ejbEntidadDetalleFacade the ejbEntidadDetalleFacade to set
     */
    public void setEjbEntidadDetalleFacade(pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade) {
        this.ejbEntidadDetalleFacade = ejbEntidadDetalleFacade;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<TrmTramite> items) {
        this.items = items;
    }

    /**
     * @return the listaTiposTramite
     */
    public List<MaeEntidaddet> getListaTiposTramite() {
        if(listaTiposTramite==null){
            listaTiposTramite=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("TIPOTRAMTRM"));
        }
        return listaTiposTramite;
    }

    /**
     * @param listaTiposTramite the listaTiposTramite to set
     */
    public void setListaTiposTramite(List<MaeEntidaddet> listaTiposTramite) {
        this.listaTiposTramite = listaTiposTramite;
    }

    /**
     * @return the listaCodigoOrigen
     */
    public List<MaeEntidaddet> getListaCodigoOrigen() {
        if(listaCodigoOrigen==null){
            listaCodigoOrigen=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("CODIORIGTRM"));
        }
        return listaCodigoOrigen;
    }

    /**
     * @param listaCodigoOrigen the listaCodigoOrigen to set
     */
    public void setListaCodigoOrigen(List<MaeEntidaddet> listaCodigoOrigen) {
        this.listaCodigoOrigen = listaCodigoOrigen;
    }

    /**
     * @return the listaModalidadTramite
     */
    public List<MaeEntidaddet> getListaModalidadTramite() {
        if(listaModalidadTramite==null){
            listaModalidadTramite=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("CODIMODATRM"));
        }
        return listaModalidadTramite;
    }

    /**
     * @param listaModalidadTramite the listaModalidadTramite to set
     */
    public void setListaModalidadTramite(List<MaeEntidaddet> listaModalidadTramite) {
        this.listaModalidadTramite = listaModalidadTramite;
    }

    /**
     * @return the listaCodigoPrioridad
     */
    public List<MaeEntidaddet> getListaCodigoPrioridad() {
        if(listaCodigoPrioridad==null){
            listaCodigoPrioridad=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("CODIPRIOTRM"));
        }
        return listaCodigoPrioridad;
    }

    /**
     * @param listaCodigoPrioridad the listaCodigoPrioridad to set
     */
    public void setListaCodigoPrioridad(List<MaeEntidaddet> listaCodigoPrioridad) {
        this.listaCodigoPrioridad = listaCodigoPrioridad;
    }

    @FacesConverter(forClass = TrmTramite.class)
    public static class TrmTramiteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrmTramiteController controller = (TrmTramiteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trmTramiteController");
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
            if (object instanceof TrmTramite) {
                TrmTramite o = (TrmTramite) object;
                return getStringKey(o.getIdenExpeTrm());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TrmTramite.class.getName()});
                return null;
            }
        }

    }

}
