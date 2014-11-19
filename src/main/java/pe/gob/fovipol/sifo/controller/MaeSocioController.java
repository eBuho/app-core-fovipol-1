package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeSocioFacade;

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
import pe.gob.fovipol.sifo.model.recuperaciones.RecAporte;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

@ManagedBean(name = "maeSocioController")
@SessionScoped
public class MaeSocioController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeSocioFacade ejbFacade;
    private List<MaeSocio> items = null;
    private MaeSocio selected = new MaeSocio();
    private List<TrmTramite> listaTramites;
    private List<RecAporte> listaAportes;

    public MaeSocioController() {
    }

    public MaeSocio getSelected() {
        return selected;
    }

    public void setSelected(MaeSocio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaeSocioFacade getFacade() {
        return ejbFacade;
    }

    public MaeSocio prepareCreate() {
        selected = new MaeSocio();
        selected.setFlagEstaSoc(new Short("1"));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeSocioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeSocioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeSocioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeSocio> getItems() {
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

    public List<MaeSocio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeSocio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the listaTramites
     */
    public List<TrmTramite> getListaTramites() {
        listaTramites = ejbFacade.findTramitesSocio(selected);
        listaAportes = ejbFacade.findAportesSocio(selected);
        return listaTramites;
    }

    /**
     * @param listaTramites the listaTramites to set
     */
    public void setListaTramites(List<TrmTramite> listaTramites) {
        this.listaTramites = listaTramites;
    }

    /**
     * @return the listaAportes
     */
    public List<RecAporte> getListaAportes() {
        return listaAportes;
    }

    /**
     * @param listaAportes the listaAportes to set
     */
    public void setListaAportes(List<RecAporte> listaAportes) {
        this.listaAportes = listaAportes;
    }

    @FacesConverter(forClass = MaeSocio.class)
    public static class MaeSocioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeSocioController controller = (MaeSocioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeSocioController");
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
            if (object instanceof MaeSocio) {
                MaeSocio o = (MaeSocio) object;
                return getStringKey(o.getCodiPersPer());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeSocio.class.getName()});
                return null;
            }
        }

    }

}
