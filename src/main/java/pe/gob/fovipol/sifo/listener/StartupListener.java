/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.listener;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import pe.gob.fovipol.sifo.dao.MaeEntidadFacade;

/**
 *
 * @author eBuho
 */

public class StartupListener implements ServletContextListener{
    
    @EJB
    private MaeEntidadFacade ejbFacade;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("hola");
        System.out.println(ejbFacade.toString());
        
        sce.getServletContext().setAttribute("Menu", "menu");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("hola");
        sce.getServletContext().removeAttribute("Menu");
        
    }
    
}
