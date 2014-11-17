package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.TrmMovimientoFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.TabChangeEvent;
import pe.gob.fovipol.sifo.bean.sesion.SesionUsuario;

@ManagedBean(name = "trmMovimientoController")
@ViewScoped
public class TrmMovimientoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.TrmMovimientoFacade ejbFacade;
    @ManagedProperty(value="#{sesionUsuario}")
    private SesionUsuario sesionUsuario;
    private List<TrmMovimiento> items = null;
    private List<TrmMovimiento> itemsHistoricos = null;
    private TrmMovimiento selected;
    private TrmMovimiento selectedHistorico;
    
    @PostConstruct
    public void init() {
        items=ejbFacade.findItemsActivos(getSesionUsuario().getArea().getCodiAreaAre());
    }

    public TrmMovimiento getSelected() {
        return selected;
    }
    
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle().equals("Bandeja de trabajo")){
            items=ejbFacade.findItemsActivos(getSesionUsuario().getArea().getCodiAreaAre());
        }        
    }
    
    public void setSelected(TrmMovimiento selected) {
        this.selected = selected;
    }
    /*
    public void onRowSelect(SelectEvent event) {
        TrmMovimiento movimiento = (TrmMovimiento) event.getObject();

        FacesMessage msg = new FacesMessage("Movimiento de Tramite Seleccionado",
                " " + movimiento.getTrmMovimientoPK().getIdenExpeTrm() + 
                " " + movimiento.getTrmMovimientoPK().getSecuMoviMvm());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().
                put("idTramite", movimiento.getTrmTramite().getIdenExpeTrm());
    }

    public void onRowUnselect(UnselectEvent event) {
        Tramite tramite = (Tramite) event.getObject();
        FacesMessage msg = new FacesMessage("Tramite Deseleccionado",
                "" + tramite.getId() + " " + tramite.getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    */
    protected void setEmbeddableKeys() {
        BigInteger id = getSelected().getTrmTramite().getIdenExpeTrm().toBigInteger();
        getSelected().getTrmMovimientoPK().setIdenExpeTrm(id);
    }

    protected void initializeEmbeddableKey() {
        getSelected().setTrmMovimientoPK(new pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK());
    }

    private TrmMovimientoFacade getFacade() {
        return getEjbFacade();
    }

    public TrmMovimiento prepareCreate() {
        setSelected(new TrmMovimiento());
        initializeEmbeddableKey();
        return getSelected();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Tramite").getString("TrmMovimientoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            setItems(null);    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Tramite").getString("TrmMovimientoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Tramite").getString("TrmMovimientoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            setSelected(null); // Remove selection
            setItems(null);    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TrmMovimiento> getItems() {        
        return items;
    }
    
    public List<TrmMovimiento> getItemsActivos() {
        if (getItems() == null) {
            setItems(getFacade().findItemsActivos(getSesionUsuario().getArea().getCodiAreaAre()));
        }
        return getItems();
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

    public List<TrmMovimiento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TrmMovimiento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the ejbFacade
     */
    public pe.gob.fovipol.sifo.dao.TrmMovimientoFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(pe.gob.fovipol.sifo.dao.TrmMovimientoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<TrmMovimiento> items) {
        this.items = items;
    }

    /**
     * @return the itemsHistoricos
     */
    public List<TrmMovimiento> getItemsHistoricos() {
        if (itemsHistoricos == null) {
            setItems(getFacade().findItemsHistoricos()); //findAll();
        }
        return itemsHistoricos;
    }

    /**
     * @param itemsHistoricos the itemsHistoricos to set
     */
    public void setItemsHistoricos(List<TrmMovimiento> itemsHistoricos) {
        this.itemsHistoricos = itemsHistoricos;
    }

    /**
     * @return the selectedHistorico
     */
    public TrmMovimiento getSelectedHistorico() {
        return selectedHistorico;
    }

    /**
     * @param selectedHistorico the selectedHistorico to set
     */
    public void setSelectedHistorico(TrmMovimiento selectedHistorico) {
        this.selectedHistorico = selectedHistorico;
    }

    /**
     * @return the sesionUsuario
     */
    public SesionUsuario getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * @param sesionUsuario the sesionUsuario to set
     */
    public void setSesionUsuario(SesionUsuario sesionUsuario) {
        this.sesionUsuario = sesionUsuario;
    }

    @FacesConverter(forClass = TrmMovimiento.class)
    public static class TrmMovimientoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrmMovimientoController controller = (TrmMovimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trmMovimientoController");
            return controller.getFacade().find(getKey(value));
        }

        pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK getKey(String value) {
            pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK();
            BigInteger id = BigInteger.valueOf( Long.parseLong(values[0]) )  ;
            key.setIdenExpeTrm(id);
            key.setSecuMoviMvm(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdenExpeTrm());
            sb.append(SEPARATOR);
            sb.append(value.getSecuMoviMvm());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TrmMovimiento) {
                TrmMovimiento o = (TrmMovimiento) object;
                return getStringKey(o.getTrmMovimientoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TrmMovimiento.class.getName()});
                return null;
            }
        }

    }

}
