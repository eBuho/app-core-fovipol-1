package pe.gob.fovipol.sifo.model.general;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pe.gob.fovipol.sifo.service.ComunService;

@FacesConverter(forClass = pe.gob.fovipol.sifo.model.general.Moneda.class, 
        value="monedaConverter")
public class MonedaConverter implements Converter {
    @EJB
    private ComunService serviceComun;
    
    static List<Moneda> monedas = null;

    public MonedaConverter() {
        if (monedas==null){
            monedas = serviceComun.getMonedas();
        }
    }
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
       
        return buscarMoneda(getKey(value));
    }

    Integer getKey(String value) {
        Integer key;
        key = new Integer(value);
        return key;
    }

    String getStringKey(Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Moneda) {
            Moneda o = (Moneda) object;
            return getStringKey(o.getCodiMoneMon());
        } else {

            return null;
        }
    }

    private Object buscarMoneda(Integer key) {
        Object objeto = new Object();
        
        for (Moneda bean : monedas) {
            if (bean.getCodiMoneMon()!= key){
            } else {
                objeto = bean;
                break;
            }
        }
        return objeto;
    }
}
