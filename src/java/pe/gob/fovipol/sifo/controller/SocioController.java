/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<Socio> listaSocios;
    
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
        
        listaSocios = new ArrayList<>();
        listaSocios.add(socioSeleccionado);
        listaSocios.add( new Socio("987654321", "12345678", "Ana Maria", "Linares Rojas", "Activo" ));
        listaSocios.add( new Socio("421010431", "09474511", "Miguel Angel", "Flores Reyes", "Activo" ));
        listaSocios.add( new Socio("121212123", "09786541", "John", "Sanchez Marin", "Activo" ));
        listaSocios.add( new Socio("432112347", "11111111", "Jean", "De los Santos", "Activo" ));
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

    /**
     * @return the listaSocios
     */
    public List<Socio> getListaSocios() {
        return listaSocios;
    }

    /**
     * @param listaSocios the listaSocios to set
     */
    public void setListaSocios(List<Socio> listaSocios) {
        this.listaSocios = listaSocios;
    }
    
}
