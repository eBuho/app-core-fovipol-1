package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.tramite.TrmEstatramHis;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.TrmEstatramHisFacade;

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

@ManagedBean(name = "trmEstatramHisController")
@SessionScoped
public class TrmEstatramHisController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.TrmEstatramHisFacade ejbFacade;
    private List<TrmEstatramHis> items = null;
    private TrmEstatramHis selected;

    public TrmEstatramHisController() {
    }

    public TrmEstatramHis getSelected() {
        return selected;
    }

    public void setSelected(TrmEstatramHis selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        BigInteger id = selected.getTrmTramite().getIdenExpeTrm().toBigInteger();
        selected.getTrmEstatramHisPK().setIdenExpeTrm(id);
    }

    protected void initializeEmbeddableKey() {
        selected.setTrmEstatramHisPK(new pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK());
    }

    private TrmEstatramHisFacade getFacade() {
        return ejbFacade;
    }

    public TrmEstatramHis prepareCreate() {
        selected = new TrmEstatramHis();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Tramite").getString("TrmEstatramHisCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Tramite").getString("TrmEstatramHisUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Tramite").getString("TrmEstatramHisDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TrmEstatramHis> getItems() {
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

    public List<TrmEstatramHis> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TrmEstatramHis> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TrmEstatramHis.class)
    public static class TrmEstatramHisControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrmEstatramHisController controller = (TrmEstatramHisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trmEstatramHisController");
            return controller.getFacade().find(getKey(value));
        }

        pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK getKey(String value) {
            pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK();
            BigInteger id = BigInteger.valueOf( Long.parseLong(values[0]) );
            key.setIdenExpeTrm(id);
            key.setSecuEstaHis(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdenExpeTrm());
            sb.append(SEPARATOR);
            sb.append(value.getSecuEstaHis());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TrmEstatramHis) {
                TrmEstatramHis o = (TrmEstatramHis) object;
                return getStringKey(o.getTrmEstatramHisPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TrmEstatramHis.class.getName()});
                return null;
            }
        }

    }

}
