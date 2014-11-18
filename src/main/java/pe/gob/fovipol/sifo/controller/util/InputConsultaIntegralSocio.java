/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;

@FacesComponent("inputConsultaIntegralSocio")
public class InputConsultaIntegralSocio extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------
    private UIInput criterioBusqueda;
    private UIInput valorBusqueda;
    private UIInput tablaSocio;
    public boolean primeraCarga;
    private List<MaeSocio> listaBusqueda;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeSocioFacade ejbSocioFacade;
    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the component family of {@link UINamingContainer}. (that's just
     * required by composite component)
     */
    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    /**
     * Set the selected and available values of the day, month and year fields
     * based on the model.
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        primeraCarga=false;
        setListaSocios(ejbSocioFacade.findAll());
        super.encodeBegin(context);
    }

    /**
     * Returns the submitted value in dd-MM-yyyy format.
     */
    @Override
    public Object getSubmittedValue() {
        MaeSocio res = getAttributeValue("socio", null);

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
     * Update the available days based on the selected month and year, if
     * necessary.
     */
    public void buscarSocio() {
        String valorBuscar = valorBusqueda.getValue().toString();
        String criterioBuscar = criterioBusqueda.getValue().toString();
        if (valorBuscar == null || valorBuscar.trim().equals("")) {
            JsfUtil.addErrorMessage("Debe ingresar un valor a buscar");
        } else {
            if (criterioBuscar.equals("1")) {
                setSocios(ejbSocioFacade.findByDNI(valorBuscar));
            }
            if (criterioBuscar.equals("2")) {
                setSocios(ejbSocioFacade.findByCIP(valorBuscar));
            }
            if (criterioBuscar.equals("0")) {
                setSocios(ejbSocioFacade.findByName(valorBuscar));
            }
            primeraCarga=true;
        }
    }

    // Helpers ------------------------------------------------------------------------------------
    /**
     * Return specified attribute value or otherwise the specified default if
     * it's null.
     */
    @SuppressWarnings("unchecked")
    private <T> T getAttributeValue(String key, T defaultValue) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    // Getters/setters ----------------------------------------------------------------------------
    public List<MaeSocio> getSocios() {
        return (List<MaeSocio>) getStateHelper().get("socios");
    }

    public void setSocios(List<MaeSocio> socios) {
        getStateHelper().put("socios", socios);
    }

    public List<MaeSocio> getListaSocios() {
        return (List<MaeSocio>) getStateHelper().get("listaSocios");
    }

    public void setListaSocios(List<MaeSocio> socios) {
        getStateHelper().put("listaSocios", socios);
    }

    /**
     * @return the criterioBusqueda
     */
    public UIInput getCriterioBusqueda() {
        return criterioBusqueda;
    }

    /**
     * @param criterioBusqueda the criterioBusqueda to set
     */
    public void setCriterioBusqueda(UIInput criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    /**
     * @return the valorBusqueda
     */
    public UIInput getValorBusqueda() {
        return valorBusqueda;
    }

    /**
     * @param valorBusqueda the valorBusqueda to set
     */
    public void setValorBusqueda(UIInput valorBusqueda) {
        this.valorBusqueda = valorBusqueda;
    }

    /**
     * @return the tablaSocio
     */
    public UIInput getTablaSocio() {
        return tablaSocio;
    }

    /**
     * @param tablaSocio the tablaSocio to set
     */
    public void setTablaSocio(UIInput tablaSocio) {
        this.tablaSocio = tablaSocio;
    }

    /**
     * @return the primeraCarga
     */
    public boolean isPrimeraCarga() {
        return primeraCarga;
    }

    /**
     * @param primeraCarga the primeraCarga to set
     */
    public void setPrimeraCarga(boolean primeraCarga) {
        this.primeraCarga = primeraCarga;
    }

    /**
     * @return the listaBusqueda
     */
    public List<MaeSocio> getListaBusqueda() {
        return listaBusqueda;
    }

    /**
     * @param listaBusqueda the listaBusqueda to set
     */
    public void setListaBusqueda(List<MaeSocio> listaBusqueda) {
        this.listaBusqueda = listaBusqueda;
    }
}
