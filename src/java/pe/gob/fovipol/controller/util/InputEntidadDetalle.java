/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.controller.util;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import pe.gob.fovipol.model.MaeEntidaddet;

@FacesComponent("inputEntidadDetalle")
public class InputEntidadDetalle extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------

    private HtmlOutputText resultado;
    @EJB
    private pe.gob.fovipol.dao.MaeEntidaddetFacade ejbEntidadDetalleFacade;
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
        BigDecimal key;
        try{
            key=getAttributeValue("value", null);
        }
        catch(ClassCastException cce){
            try{
                int ente=getAttributeValue("value", 0);
                key=new BigDecimal(ente);
            }
            catch(ClassCastException cde){
                String entidad=getAttributeValue("value", "0");
                key=new BigDecimal(entidad);
            }            
        }
        //BigDecimal key=new BigDecimal(ingreso);
        MaeEntidaddet aux=ejbEntidadDetalleFacade.findIdenEntiDet(key);
        if(aux!=null)
            resultado.setValue(aux.getValoCaduDet());
        else
            resultado.setValue(" ");
        super.encodeBegin(context);
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
     * @return the resultado
     */
    public HtmlOutputText getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(HtmlOutputText resultado) {
        this.resultado = resultado;
    }
}
