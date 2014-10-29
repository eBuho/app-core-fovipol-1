package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.TrmDocumentoFacade;

import java.io.Serializable;
import java.math.BigInteger;
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

@ManagedBean(name = "trmDocumentoController")
@SessionScoped
public class TrmDocumentoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.TrmDocumentoFacade ejbFacade;
    private List<TrmDocumento> items = null;
    private TrmDocumento selected;

    public TrmDocumentoController() {
    }

    public TrmDocumento getSelected() {
        return selected;
    }

    public void setSelected(TrmDocumento selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        BigInteger id = selected.getTrmTramite().getIdenExpeTrm().toBigInteger();
        selected.getTrmDocumentoPK().setIdenExpeTrm(id);
    }

    protected void initializeEmbeddableKey() {
        selected.setTrmDocumentoPK(new pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK());
    }

    private TrmDocumentoFacade getFacade() {
        return ejbFacade;
    }

    public TrmDocumento prepareCreate() {
        selected = new TrmDocumento();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Tramite").getString("TrmDocumentoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Tramite").getString("TrmDocumentoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Tramite").getString("TrmDocumentoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TrmDocumento> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Tramite").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Tramite").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<TrmDocumento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TrmDocumento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TrmDocumento.class)
    public static class TrmDocumentoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrmDocumentoController controller = (TrmDocumentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trmDocumentoController");
            return controller.getFacade().find(getKey(value));
        }

        pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK getKey(String value) {
            pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK();
            BigInteger id = BigInteger.valueOf( Long.parseLong(values[0]) );
            key.setIdenExpeTrm(id);
            key.setSecuDocuDoc(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdenExpeTrm());
            sb.append(SEPARATOR);
            sb.append(value.getSecuDocuDoc());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TrmDocumento) {
                TrmDocumento o = (TrmDocumento) object;
                return getStringKey(o.getTrmDocumentoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TrmDocumento.class.getName()});
                return null;
            }
        }

    }

}
