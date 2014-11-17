/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.listener;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author eBuho
 */

public class StartupListener implements ServletContextListener{
    
    @EJB
    private MaeEntidaddetFacade ejbMaeEntidaddetFacade;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Cargando Lista de monedas");
        List<MaeEntidaddet> listaMonedas;
        listaMonedas = ejbMaeEntidaddetFacade.findDetalleActivo(new MaeEntidad(Constantes.CODI_MONE_CRD) );
        sce.getServletContext().setAttribute("listaMonedas", listaMonedas);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destruyendo Lista de monedas");
        sce.getServletContext().removeAttribute("listaMonedas");
        
    }
    
}
