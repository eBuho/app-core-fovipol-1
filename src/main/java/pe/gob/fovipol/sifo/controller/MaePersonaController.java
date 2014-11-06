package pe.gob.fovipol.sifo.controller;

import pe.gob.fovipol.sifo.model.maestros.MaePersona;
import pe.gob.fovipol.sifo.controller.util.JsfUtil;
import pe.gob.fovipol.sifo.controller.util.JsfUtil.PersistAction;
import pe.gob.fovipol.sifo.dao.MaePersonaFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.maestros.MaeUbigeo;

@ManagedBean(name = "maePersonaController")
@ViewScoped
public class MaePersonaController implements Serializable {

    @EJB
    private pe.gob.fovipol.sifo.dao.MaePersonaFacade ejbFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeUbigeoFacade ejbUbigeoFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeSocioFacade ejbSocioFacade;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
    private List<MaePersona> items = null;
    private List<MaePersona> itemsFamilia = null;
    private List<MaePersona> itemsFiltro;
    private List<MaeSocio> socios;
    private MaePersona selected;
    private MaePersona selectedFamilia;
    private MaePersona selectedPersona;
    private MaeSocio selectedSocio;
    private MaeUbigeo departamentoNacimiento;
    private MaeUbigeo provinciaNacimiento;
    private List<MaeUbigeo> departamentos=null;
    private List<MaeUbigeo> provincias=null;
    private List<MaeUbigeo> distritos=null;
    private List<MaeEntidaddet> documentos;
    private List<MaeEntidaddet> parentescos;
    private List<MaeEntidaddet> estadosCiviles;
    private List<MaeEntidaddet> generos;
    private List<MaeEntidaddet> profesiones;
    private List<MaeEntidaddet> bloqueos;    
    private List<MaeEntidaddet> entidadesPago;
    private List<MaeEntidaddet> tiposPolicia;
    private List<MaeEntidaddet> unidadesTrabajo;
    public MaePersonaController() {  
        
    }
    @PostConstruct
    public void init(){
        
    }
    public void prepararActualizar(){
        selected=selectedPersona;
        if(selected!=null && selected.getUbigNaciPer()!=null){
            provinciaNacimiento=selected.getUbigNaciPer().getIdenUbipUbi();
            if(provinciaNacimiento!=null){
                if(provinciaNacimiento.getIdenUbipUbi()!=null){
                    departamentoNacimiento=provinciaNacimiento.getIdenUbipUbi();
                    cargarProvincias();
                    cargarDistritos();
                }
            }
        }
    }
    
    public void prepararActualizarFamilia(){
        selected=selectedFamilia;
        if(selected!=null && selected.getUbigNaciPer()!=null){
            provinciaNacimiento=selected.getUbigNaciPer().getIdenUbipUbi();
            if(provinciaNacimiento!=null){
                if(provinciaNacimiento.getIdenUbipUbi()!=null){
                    departamentoNacimiento=provinciaNacimiento.getIdenUbipUbi();
                    cargarProvincias();
                    cargarDistritos();
                }
            }
        }
    }
    
    public void cargarSocio(){
        socios=ejbSocioFacade.findHijos(selectedPersona);
        selectedSocio=null;
        itemsFamilia=ejbFacade.findFamilia(selectedPersona);
        selectedFamilia=null;
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
    
    public void prepararVer(){
        selected=selectedPersona;
    }
    public void prepararVerFamilia(){
        selected=selectedFamilia;
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
    public MaePersona prepareCreateFamilia() {
        selected = new MaePersona();
        selected.setCodiPersPer(ejbFacade.obtenerCorrelativo());
        selected.setCodiPerpPer(selectedPersona);
        departamentoNacimiento=null;
        provinciaNacimiento=null;
        provincias=null;
        distritos=null;
        initializeEmbeddableKey();
        return selected;
    }
    public MaeSocio prepareCreateSocio() {
        selectedSocio = new MaeSocio();
        selectedSocio.setFlagEstaSoc(new Short("1"));
        selectedSocio.setCodiPersPer(selected.getCodiPersPer());  
        selectedSocio.setMaePersona(selected);
        return selectedSocio;
    }
    public void createSocio(){
        selectedSocio.setFechCreaAud(new Date());
        persistSocio(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaeSocioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            cargarSocio();    // Invalidate list of items to trigger re-query.
        }
    }
    public void create() {
        selected.setFechCreaAud(new Date());
        selected.setFlagEstaPer(new Short("1"));
        selected.setNombCompPer(nombreCompleto());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaePersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;
            cargarSocio();// Invalidate list of items to trigger re-query.
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
        items=null;
    }
    public void updateSocio() {
        selectedSocio.setFechModiAud(new Date());
        persistSocio(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaeSocioUpdated"));
    }

    public void destroy() {
        selected=selectedPersona;
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaePersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void destroyFamilia() {
        selected=selectedFamilia;
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaePersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selectedFamilia = null; // Remove selection
            cargarSocio();    // Invalidate list of items to trigger re-query.
        }
    }
    public void destroySocio() {
        persistSocio(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaeSocioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            cargarSocio();
            selectedSocio = null; 
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

    private void persistSocio(PersistAction persistAction, String successMessage) {
        if (selectedSocio != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    ejbSocioFacade.edit(selectedSocio);
                } else {
                    ejbSocioFacade.remove(selectedSocio);
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
            documentos=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("TIPOIDENPER"));
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
            estadosCiviles=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("ESTACIVIPER"));
        }
        return estadosCiviles;
    }

    /**
     * @param estadosCiviles the estadosCiviles to set
     */
    public void setEstadosCiviles(List<MaeEntidaddet> estadosCiviles) {
        this.estadosCiviles = estadosCiviles;
    }

    /**
     * @return the generos
     */
    public List<MaeEntidaddet> getGeneros() {
        if(generos==null){
            generos=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("SEXOPERSPER"));
        }
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(List<MaeEntidaddet> generos) {
        this.generos = generos;
    }

    /**
     * @return the profesiones
     */
    public List<MaeEntidaddet> getProfesiones() {
        if(profesiones==null){
            profesiones=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("TIPOPROFPER"));
        }
        return profesiones;
    }

    /**
     * @param profesiones the profesiones to set
     */
    public void setProfesiones(List<MaeEntidaddet> profesiones) {
        this.profesiones = profesiones;
    }

    /**
     * @return the bloqueos
     */
    public List<MaeEntidaddet> getBloqueos() {
        if(bloqueos==null){
            bloqueos=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("MOTIBLOQPER"));
        }
        return bloqueos;
    }

    /**
     * @param bloqueos the bloqueos to set
     */
    public void setBloqueos(List<MaeEntidaddet> bloqueos) {
        this.bloqueos = bloqueos;
    }


    /**
     * @return the entidadesPago
     */
    public List<MaeEntidaddet> getEntidadesPago() {
        if(entidadesPago==null){
            entidadesPago=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("ENTIPAGOSOC"));
        }
        return entidadesPago;
    }

    /**
     * @param entidadesPago the entidadesPago to set
     */
    public void setEntidadesPago(List<MaeEntidaddet> entidadesPago) {
        this.entidadesPago = entidadesPago;
    }

    /**
     * @return the socios
     */
    public List<MaeSocio> getSocios() {
        return socios;
    }

    /**
     * @param socios the socios to set
     */
    public void setSocios(List<MaeSocio> socios) {
        this.socios = socios;
    }

    /**
     * @return the selectedSocio
     */
    public MaeSocio getSelectedSocio() {
        return selectedSocio;
    }

    /**
     * @param selectedSocio the selectedSocio to set
     */
    public void setSelectedSocio(MaeSocio selectedSocio) {
        this.selectedSocio = selectedSocio;
    }

    /**
     * @return the tiposPolicia
     */
    public List<MaeEntidaddet> getTiposPolicia() {
        if(tiposPolicia==null){
            tiposPolicia=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("TIPOPOLISOC"));
        }
        return tiposPolicia;
    }

    /**
     * @param tiposPolicia the tiposPolicia to set
     */
    public void setTiposPolicia(List<MaeEntidaddet> tiposPolicia) {
        this.tiposPolicia = tiposPolicia;
    }

    /**
     * @return the unidadesTrabajo
     */
    public List<MaeEntidaddet> getUnidadesTrabajo() {
        if(unidadesTrabajo==null){
            unidadesTrabajo=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("UNIDTRABSOC"));
        }
        return unidadesTrabajo;
    }

    /**
     * @param unidadesTrabajo the unidadesTrabajo to set
     */
    public void setUnidadesTrabajo(List<MaeEntidaddet> unidadesTrabajo) {
        this.unidadesTrabajo = unidadesTrabajo;
    }

    /**
     * @return the itemsFamilia
     */
    public List<MaePersona> getItemsFamilia() {
        return itemsFamilia;
    }

    /**
     * @param itemsFamilia the itemsFamilia to set
     */
    public void setItemsFamilia(List<MaePersona> itemsFamilia) {
        this.itemsFamilia = itemsFamilia;
    }

    /**
     * @return the selectedFamilia
     */
    public MaePersona getSelectedFamilia() {
        return selectedFamilia;
    }

    /**
     * @param selectedFamilia the selectedFamilia to set
     */
    public void setSelectedFamilia(MaePersona selectedFamilia) {
        this.selectedFamilia = selectedFamilia;
    }

    /**
     * @return the selectedPersona
     */
    public MaePersona getSelectedPersona() {
        return selectedPersona;
    }

    /**
     * @param selectedPersona the selectedPersona to set
     */
    public void setSelectedPersona(MaePersona selectedPersona) {
        this.selectedPersona = selectedPersona;
    }

    /**
     * @return the parentescos
     */
    public List<MaeEntidaddet> getParentescos() {
        if(parentescos==null){
            parentescos=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("GRADPAREPER"));
        }
        return parentescos;
    }

    /**
     * @param parentescos the parentescos to set
     */
    public void setParentescos(List<MaeEntidaddet> parentescos) {
        this.parentescos = parentescos;
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
