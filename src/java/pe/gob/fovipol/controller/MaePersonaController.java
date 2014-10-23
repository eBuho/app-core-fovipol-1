package pe.gob.fovipol.controller;

import pe.gob.fovipol.model.MaePersona;
import pe.gob.fovipol.controller.util.JsfUtil;
import pe.gob.fovipol.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.dao.MaePersonaFacade;

import java.io.Serializable;
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
import pe.gob.fovipol.model.MaeEntidad;
import pe.gob.fovipol.model.MaeEntidaddet;
import pe.gob.fovipol.model.MaeUbigeo;

@ManagedBean(name = "maePersonaController")
@ViewScoped
public class MaePersonaController implements Serializable {

    @EJB
    private pe.gob.fovipol.dao.MaePersonaFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.dao.MaeUbigeoFacade ejbUbigeoFacade;
    @EJB
    private pe.gob.fovipol.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
    private List<MaePersona> items = null;
    private List<MaePersona> itemsFiltro;
    private MaePersona selected;
    private MaeUbigeo departamentoNacimiento;
    private MaeUbigeo provinciaNacimiento;
    private List<MaeUbigeo> departamentos=null;
    private List<MaeUbigeo> provincias=null;
    private List<MaeUbigeo> distritos=null;
    private List<MaeEntidaddet> documentos;
    private List<MaeEntidaddet> estadosCiviles;
    public MaePersonaController() {        
    }
    
    public void cargarProvincias(){
        provincias=ejbUbigeoFacade.findHijos(departamentoNacimiento);
    }
    public void cargarDistritos(){
        distritos=ejbUbigeoFacade.findHijos(provinciaNacimiento);
    }
    public MaePersona getSelected() {
        return selected;
    }

    public void setSelected(MaePersona selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MaePersonaFacade getFacade() {
        return ejbFacade;
    }

    public MaePersona prepareCreate() {
        selected = new MaePersona();
        selected.setCodiPersPer(ejbFacade.obtenerCorrelativo());
        departamentoNacimiento=null;
        provinciaNacimiento=null;
        provincias=null;
        distritos=null;
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setFechCreaAud(new Date());
        selected.setFlagEstaPer(new Short("1"));
        selected.setNombCompPer(nombreCompleto());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaePersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public String nombreCompleto(){
        String nombre="";
        if(!selected.getNombPersPer().trim().equals(""))
            nombre=selected.getNombPersPer()+" ";
        if(!selected.getApelPatePer().trim().equals(""))
            nombre+=selected.getApelPatePer()+" ";
        if(!selected.getApelMatePer().trim().equals(""))
            nombre+=selected.getApelMatePer();
        return nombre;
    }
    public void update() {
        selected.setFechModiAud(new Date());
        selected.setNombCompPer(nombreCompleto());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaePersonaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaePersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaePersona> getItems() {
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

    public List<MaePersona> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaePersona> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsFiltro
     */
    public List<MaePersona> getItemsFiltro() {
        return itemsFiltro;
    }

    /**
     * @param itemsFiltro the itemsFiltro to set
     */
    public void setItemsFiltro(List<MaePersona> itemsFiltro) {
        this.itemsFiltro = itemsFiltro;
    }

    /**
     * @return the departamentoNacimiento
     */
    public MaeUbigeo getDepartamentoNacimiento() {
        return departamentoNacimiento;
    }

    /**
     * @param departamentoNacimiento the departamentoNacimiento to set
     */
    public void setDepartamentoNacimiento(MaeUbigeo departamentoNacimiento) {
        this.departamentoNacimiento = departamentoNacimiento;
    }

    /**
     * @return the provinciaNacimiento
     */
    public MaeUbigeo getProvinciaNacimiento() {
        return provinciaNacimiento;
    }

    /**
     * @param provinciaNacimiento the provinciaNacimiento to set
     */
    public void setProvinciaNacimiento(MaeUbigeo provinciaNacimiento) {
        this.provinciaNacimiento = provinciaNacimiento;
    }

    /**
     * @return the departamentos
     */
    public List<MaeUbigeo> getDepartamentos() {
        if(departamentos==null){
            departamentos=ejbUbigeoFacade.findDepartamentos();
        }
        return departamentos;
    }

    /**
     * @param departamentos the departamentos to set
     */
    public void setDepartamentos(List<MaeUbigeo> departamentos) {
        this.departamentos = departamentos;
    }

    /**
     * @return the provincias
     */
    public List<MaeUbigeo> getProvincias() {
        return provincias;
    }

    /**
     * @param provincias the provincias to set
     */
    public void setProvincias(List<MaeUbigeo> provincias) {
        this.provincias = provincias;
    }

    /**
     * @return the distritos
     */
    public List<MaeUbigeo> getDistritos() {
        return distritos;
    }

    /**
     * @param distritos the distritos to set
     */
    public void setDistritos(List<MaeUbigeo> distritos) {
        this.distritos = distritos;
    }

    /**
     * @return the documentos
     */
    public List<MaeEntidaddet> getDocumentos() {
        if(documentos==null){
            documentos=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("TIPOIDENPER"));
        }
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<MaeEntidaddet> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the estadosCiviles
     */
    public List<MaeEntidaddet> getEstadosCiviles() {
        if(estadosCiviles==null){
            estadosCiviles=ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("ESTACIVIPER"));
        }
        return estadosCiviles;
    }

    /**
     * @param estadosCiviles the estadosCiviles to set
     */
    public void setEstadosCiviles(List<MaeEntidaddet> estadosCiviles) {
        this.estadosCiviles = estadosCiviles;
    }

    @FacesConverter(forClass = MaePersona.class)
    public static class MaePersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaePersonaController controller = (MaePersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "maePersonaController");
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
            if (object instanceof MaePersona) {
                MaePersona o = (MaePersona) object;
                return getStringKey(o.getCodiPersPer());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaePersona.class.getName()});
                return null;
            }
        }

    }

}
