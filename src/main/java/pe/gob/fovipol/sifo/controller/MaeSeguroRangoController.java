package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeSeguroRango;
import pe.gob.fovipol.sifo.dao.util.JsfUtil;
import pe.gob.fovipol.sifo.dao.util.JsfUtil.PersistAction;

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
import pe.gob.fovipol.sifo.dao.MaeSeguroRangoFacade;

@ManagedBean(name = "maeSeguroRangoController")
@SessionScoped
public class MaeSeguroRangoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeSeguroRangoFacade ejbFacade;
    private List<MaeSeguroRango> items = null;
    private MaeSeguroRango selected;

    public MaeSeguroRangoController() {
    }

    public MaeSeguroRango getSelected() {
        return selected;
    }

    public void setSelected(MaeSeguroRango selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMaeSeguroRangoPK().setIdenSeguSeg(selected.getMaeSeguro().getIdenSeguSeg().toBigInteger());
    }

    protected void initializeEmbeddableKey() {
        selected.setMaeSeguroRangoPK(new pe.gob.fovipol.sifo.model.maestros.MaeSeguroRangoPK());
    }

    private MaeSeguroRangoFacade getFacade() {
        return ejbFacade;
    }

    public MaeSeguroRango prepareCreate() {
        selected = new MaeSeguroRango();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("MaeSeguroRangoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("MaeSeguroRangoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("MaeSeguroRangoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeSeguroRango> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<MaeSeguroRango> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeSeguroRango> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MaeSeguroRango.class)
    public static class MaeSeguroRangoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeSeguroRangoController controller = (MaeSeguroRangoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeSeguroRangoController");
            return controller.getFacade().find(getKey(value));
        }

        pe.gob.fovipol.sifo.model.maestros.MaeSeguroRangoPK getKey(String value) {
            pe.gob.fovipol.sifo.model.maestros.MaeSeguroRangoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new pe.gob.fovipol.sifo.model.maestros.MaeSeguroRangoPK();
            //key.setIdenSeguSeg(values[0]);
            key.setSecuSeguSgd(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(pe.gob.fovipol.sifo.model.maestros.MaeSeguroRangoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdenSeguSeg());
            sb.append(SEPARATOR);
            sb.append(value.getSecuSeguSgd());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaeSeguroRango) {
                MaeSeguroRango o = (MaeSeguroRango) object;
                return getStringKey(o.getMaeSeguroRangoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeSeguroRango.class.getName()});
                return null;
            }
        }

    }

}
