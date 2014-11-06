/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller.util;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@FacesComponent("inputBooleanCheckbox")
public class InputBooleanCheckbox extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------

    private UIInput check;
    
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
        short value=getAttributeValue("value", new Short("0"));        
        if(value==0)
            check.setValue(false);
        else
            check.setValue(true);
        super.encodeBegin(context);
    }

    /**
     * Returns the submitted value in dd-MM-yyyy format.
     */
    @Override
    public Object getSubmittedValue() {
        boolean resultado=(boolean) check.getSubmittedValue();
        if(resultado)
            return new Short("1");
        else
            return new Short("0");
    }

    
    @Override
    protected Object getConvertedValue(FacesContext context, Object submittedValue) {
        return submittedValue;
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

    

    /**
     * @return the check
     */
    public UIInput getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(UIInput check) {
        this.check = check;
    }

}