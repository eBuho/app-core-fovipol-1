package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaeProcesoestado;
import pe.gob.fovipol.sifo.dao.util.JsfUtil;
import pe.gob.fovipol.sifo.dao.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaeProcesoestadoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.gob.fovipol.sifo.dao.MaeProcesoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK;
import pe.gob.fovipol.sifo.util.Constantes;

@Named("maeProcesoestadoController")
@SessionScoped
public class MaeProcesoestadoController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaeProcesoestadoFacade ejbFacade;
    @EJB
    private MaeProcesoFacade ejbProcesoFacade;
    private List<MaeProcesoestado> items = null;
    private MaeProcesoestado selected;
    private List<MaeProceso> procesos;

    @PostConstruct
    public void init(){
        procesos=ejbProcesoFacade.findProcesos();
    }
    
    public MaeProcesoestadoController() {
    }

    public MaeProcesoestado getSelected() {
        return selected;
    }

    public void setSelected(MaeProcesoestado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        //selected.getMaeProcesoestadoPK().setIdenProcPrc(selected.getMaeProceso().getCodiProcPrc());
    }

    protected void initializeEmbeddableKey() {
        //selected.setMaeProcesoestadoPK(new pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK());
    }

    private MaeProcesoestadoFacade getFacade() {
        return ejbFacade;
    }

    public MaeProcesoestado prepareCreate() {
        selected = new MaeProcesoestado();
        selected.setMaeProcesoestadoPK(new MaeProcesoestadoPK());
        selected.setFlagEstaPrc(Constantes.VALOR_ESTADO_ACTIVO);
        return selected;
    }

    public void create() {
        if(selected.getMaeProceso()!=null){
            selected.getMaeProcesoestadoPK().setIdenProcPrc(selected.getMaeProceso().getCodiProcPrc().toBigInteger());
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle3").getString("MaeProcesoestadoCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        else{
            JsfUtil.addErrorMessage("Seleccione Proceso");
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle3").getString("MaeProcesoestadoUpdated"));
    }

    public void destroy() {
        selected.setFlagEstaPrc(Constantes.VALOR_ESTADO_INACTIVO);
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle3").getString("MaeProcesoestadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            //selected = null; // Remove selection
            //items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaeProcesoestado> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MaeProcesoestado getMaeProcesoestado(pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK id) {
        return getFacade().find(id);
    }

    public List<MaeProcesoestado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaeProcesoestado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the procesos
     */
    public List<MaeProceso> getProcesos() {
        return procesos;
    }

    /**
     * @param procesos the procesos to set
     */
    public void setProcesos(List<MaeProceso> procesos) {
        this.procesos = procesos;
    }

    @FacesConverter(forClass = MaeProcesoestado.class)
    public static class MaeProcesoestadoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaeProcesoestadoController controller = (MaeProcesoestadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maeProcesoestadoController");
            return controller.getMaeProcesoestado(getKey(value));
        }

        pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK getKey(String value) {
            pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK();
            //key.setIdenProcPrc(values[0]);
            key.setSecuEstaPre(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdenProcPrc());
            sb.append(SEPARATOR);
            sb.append(value.getSecuEstaPre());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaeProcesoestado) {
                MaeProcesoestado o = (MaeProcesoestado) object;
                return getStringKey(o.getMaeProcesoestadoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaeProcesoestado.class.getName()});
                return null;
            }
        }

    }

}
