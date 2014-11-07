/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.listener;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.sessions.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author eBuho
 */
@Stateful
public class AuditListener extends DescriptorEventAdapter implements SessionCustomizer, DescriptorCustomizer {
    
    public AuditListener() {
    }

    private final static Logger logger = Logger.getLogger(AuditListener.class);    

    /**
     * This will audit a specific class.
     */
    public void customize(ClassDescriptor descriptor) {
        descriptor.getEventManager().addListener(this);
    }

    /**
     * This will audit all classes.
     */
    public void customize(Session session) {
        for (ClassDescriptor descriptor : session.getDescriptors().values()) {
            customize(descriptor);
        }
    }

    public void aboutToInsert(DescriptorEvent event) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        logger.info("aboutToUpdate");
        for (String table : (List<String>) event.getDescriptor().getTableNames()) {
            event.getRecord().put(table + ".USUA_CREA_AUD", user.getName().toUpperCase());
            event.getRecord().put(table + ".FECH_CREA_AUD", Calendar.getInstance());
            event.getRecord().put(table + ".USUA_MODI_AUD", user.getName().toUpperCase());
            event.getRecord().put(table + ".FECH_MODI_AUD", Calendar.getInstance());
            event.getRecord().put(table + ".NOMB_EQUI_AUD", obtenerIP());
        }
    }

    public void aboutToUpdate(DescriptorEvent event) {
        logger.info("aboutToUpdate");
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        for (String table : (List<String>) event.getDescriptor().getTableNames()) {
            event.getRecord().put(table + ".USUA_MODI_AUD", user.getName().toUpperCase());
            event.getRecord().put(table + ".FECH_MODI_AUD", Calendar.getInstance());
            event.getRecord().put(table + ".NOMB_EQUI_AUD", obtenerIP());
        }
    }
    public static String obtenerIP() {
        HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().
                getExternalContext().getRequest());

        // Obtener los atributos de cabecera. 
        // Los usamos para recuperar los valores reales.
        request.getHeaderNames();

        // Obtener la dirección IP del cliente.    
        String ip = request.getRemoteAddr();
        System.out.println("ip:" + ip);
        return ip;
    }

}
