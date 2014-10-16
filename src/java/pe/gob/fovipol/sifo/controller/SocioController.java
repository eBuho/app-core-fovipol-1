/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pe.gob.fovipol.sifo.model.Socio;

/**
 *
 * @author ebuho
 */
@ManagedBean(name="socioController")
@SessionScoped
public class SocioController {

    private Socio socioSeleccionado;
    
    /**
     * Creates a new instance of SocioController
     */
    public SocioController() {
        
    }
    
    @PostConstruct
    public void init() {
        socioSeleccionado = new Socio();
        socioSeleccionado.setCip("123456789");
        socioSeleccionado.setNombres("Juan Jose");
        socioSeleccionado.setApellidos("Perez rios");
        socioSeleccionado.setDni("09876543");
        socioSeleccionado.setEstado("Activo");
        
    }
    
    /**
     * @return the socioSeleccionado
     */
    public Socio getSocioSeleccionado() {
        return socioSeleccionado;
    }

    /**
     * @param socioSeleccionado the socioSeleccionado to set
     */
    public void setSocioSeleccionado(Socio socioSeleccionado) {
        this.socioSeleccionado = socioSeleccionado;
    }
    
}
