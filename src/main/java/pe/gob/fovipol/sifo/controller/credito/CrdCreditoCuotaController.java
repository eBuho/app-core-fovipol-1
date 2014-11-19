package pe.gob.fovipol.sifo.controller.credito;

import pe.gob.fovipol.sifo.model.credito.CrdCreditoCuota;
import pe.gob.fovipol.sifo.controller.credito.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.credito.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoCuotaFacade;

import java.io.Serializable;
import java.math.BigInteger;
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
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

@Named("crdCreditoCuotaController")
@SessionScoped
public class CrdCreditoCuotaController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.credito.CrdCreditoCuotaFacade ejbFacade;
    private List<CrdCreditoCuota> items = null;
    private List<CrdCreditoCuota> itemsByTramite = null;
    private CrdCreditoCuota selected;

    public CrdCreditoCuotaController() {
    }

    public CrdCreditoCuota getSelected() {
        return selected;
    }
    
    public void setSelected(CrdCreditoCuota selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getCrdCreditoCuotaPK().setIdenCredCrd(selected.getCrdCredito().getIdenCredCrd().toBigInteger());
    }

    protected void initializeEmbeddableKey() {
        selected.setCrdCreditoCuotaPK(new pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK());
    }

    private CrdCreditoCuotaFacade getFacade() {
        return ejbFacade;
    }

    public CrdCreditoCuota prepareCreate() {
        selected = new CrdCreditoCuota();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Consulta").getString("CrdCreditoCuotaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Consulta").getString("CrdCreditoCuotaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Consulta").getString("CrdCreditoCuotaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CrdCreditoCuota> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<CrdCreditoCuota> getItemsByTramite(TrmTramite tramite) {
        //if (itemsByTramite == null) {
            itemsByTramite = getFacade().getItemsByTramite(tramite);
        //}
        return itemsByTramite;
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Consulta").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Consulta").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CrdCreditoCuota getCrdCreditoCuota(pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK id) {
        return getFacade().find(id);
    }

    public List<CrdCreditoCuota> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CrdCreditoCuota> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsByTramite
     */
    public List<CrdCreditoCuota> getItemsByTramite() {
        return itemsByTramite;
    }

    /**
     * @param itemsByTramite the itemsByTramite to set
     */
    public void setItemsByTramite(List<CrdCreditoCuota> itemsByTramite) {
        this.itemsByTramite = itemsByTramite;
    }

    @FacesConverter(forClass = CrdCreditoCuota.class)
    public static class CrdCreditoCuotaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CrdCreditoCuotaController controller = (CrdCreditoCuotaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "crdCreditoCuotaController");
            return controller.getCrdCreditoCuota(getKey(value));
        }

        pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK getKey(String value) {
            pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK();
            String sId = values[0];
            BigInteger id = new BigInteger(sId);
            key.setIdenCredCrd(id);
            key.setSecuCuotCuo(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdenCredCrd());
            sb.append(SEPARATOR);
            sb.append(value.getSecuCuotCuo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CrdCreditoCuota) {
                CrdCreditoCuota o = (CrdCreditoCuota) object;
                return getStringKey(o.getCrdCreditoCuotaPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CrdCreditoCuota.class.getName()});
                return null;
            }
        }

    }

}
