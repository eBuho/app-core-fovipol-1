/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import pe.gob.fovipol.sifo.model.maestros.MaeUbigeo;

@FacesComponent("inputUbigeo")
public class InputUbigeo extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------

    private UIInput departamento;
    private UIInput provincia;
    private UIInput distrito;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeUbigeoFacade ejbUbigeoFacade;
    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the component family of {@link UINamingContainer}.
     * (that's just required by composite component)
     */
    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    /**
     * Set the selected and available values of the day, month and year fields based on the model.
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        MaeUbigeo value=getAttributeValue("value", null);        
        setDepartamentos(ejbUbigeoFacade.findDepartamentos()); 
        if(value!=null && value.getIdenUbipUbi()!=null && value.getIdenUbipUbi().getIdenUbipUbi()!=null){
            provincia.setValue(value.getIdenUbipUbi().getIdenUbigUbi());
            departamento.setValue(value.getIdenUbipUbi().getIdenUbipUbi().getIdenUbigUbi());
            cargarProvincias();
            distrito.setValue(value.getIdenUbigUbi());            
            cargarDistritos();
        }
        super.encodeBegin(context);
    }

    /**
     * Returns the submitted value in dd-MM-yyyy format.
     */
    @Override
    public Object getSubmittedValue() {
        MaeUbigeo res=ejbUbigeoFacade.find(getDistrito().getSubmittedValue().toString());
        
        return res;
    }

    /**
     * Converts the submitted value to concrete {@link Date} instance.
     */
    @Override
    protected Object getConvertedValue(FacesContext context, Object submittedValue) {
        return submittedValue;
    }

    /**
     * Update the available days based on the selected month and year, if necessary.
     */
    public void cargarProvincias() {
        
        setProvincias(ejbUbigeoFacade.findHijos(new MaeUbigeo(getDepartamento().getValue().toString())));
        setDistritos(new ArrayList<MaeUbigeo>());
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getPartialViewContext().getRenderIds().add(getProvincia().getClientId(context));
        context.getPartialViewContext().getRenderIds().add(getDistrito().getClientId(context));
    }
    public void cargarDistritos() {
        
        setDistritos(ejbUbigeoFacade.findHijos(new MaeUbigeo(getProvincia().getValue().toString())));
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.getPartialViewContext().getRenderIds().add(getDistrito().getClientId(context));
        
    }
    // Helpers ------------------------------------------------------------------------------------

    /**
     * Return specified attribute value or otherwise the specified default if it's null.
     */
    @SuppressWarnings("unchecked")
    private <T> T getAttributeValue(String key, T defaultValue) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    

    // Getters/setters ----------------------------------------------------------------------------

    public List<MaeUbigeo> getDistritos() {
        return (List<MaeUbigeo>) getStateHelper().get("distritos");
    }

    public void setDistritos(List<MaeUbigeo> distritos) {
        getStateHelper().put("distritos", distritos);
    }

    public List<MaeUbigeo> getProvincias() {
        return (List<MaeUbigeo>) getStateHelper().get("provincias");
    }

    public void setProvincias(List<MaeUbigeo> provincias) {
        getStateHelper().put("provincias", provincias);
    }

    public List<MaeUbigeo> getDepartamentos() {
        return (List<MaeUbigeo>) getStateHelper().get("departamentos");
    }

    public void setDepartamentos(List<MaeUbigeo> departamentos) {
        getStateHelper().put("departamentos", departamentos);
    }

    /**
     * @return the departamento
     */
    public UIInput getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(UIInput departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the provincia
     */
    public UIInput getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(UIInput provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the distrito
     */
    public UIInput getDistrito() {
        return distrito;
    }

    /**
     * @param distrito the distrito to set
     */
    public void setDistrito(UIInput distrito) {
        this.distrito = distrito;
    }

}