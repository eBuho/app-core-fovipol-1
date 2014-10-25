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
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;

@FacesComponent("inputTelefono")
public class InputTelefono extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------

    private UIInput prefijo;
    private UIInput numero;
    @EJB
    private pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
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
        String valorTelefono=getAttributeValue("value", "1- ");
        String[] split=valorTelefono.split("-");
        if(split.length<2)
            numero.setValue("");
        else{
            numero.setValue(split[1]);
            prefijo.setValue(split[0]);
        }
        setPrefijos(ejbEntidadDetalleFacade.findDetalle(new MaeEntidad("CODITELEFON")));        
        super.encodeBegin(context);
    }

    /**
     * Returns the submitted value in dd-MM-yyyy format.
     */
    @Override
    public Object getSubmittedValue() {
        return prefijo.getSubmittedValue()
            + "-" + numero.getSubmittedValue();
    }

    /**
     * Converts the submitted value to concrete {@link Date} instance.
     */
    @Override
    protected Object getConvertedValue(FacesContext context, Object submittedValue) {
        return (String) submittedValue;        
    }

    /**
     * Update the available days based on the selected month and year, if necessary.
     */
    

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Return specified attribute value or otherwise the specified default if it's null.
     */
    @SuppressWarnings("unchecked")
    private <T> T getAttributeValue(String key, T defaultValue) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Create an integer array with values from specified begin to specified end, inclusive.
     */
    private static Integer[] createIntegerArray(int begin, int end) {
        int direction = (begin < end) ? 1 : (begin > end) ? -1 : 0;
        int size = Math.abs(end - begin) + 1;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = begin + (i * direction);
        }

        return array;
    }

    // Getters/setters ----------------------------------------------------------------------------

    public UIInput getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(UIInput prefijo) {
        this.prefijo = prefijo;
    }

    public List<MaeEntidaddet> getPrefijos() {
        return (List<MaeEntidaddet>) getStateHelper().get("prefijos");
    }

    public void setPrefijos(List<MaeEntidaddet> prefijos) {
        getStateHelper().put("prefijos", prefijos);
    }

    /**
     * @return the numero
     */
    public UIInput getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(UIInput numero) {
        this.numero = numero;
    }

}
