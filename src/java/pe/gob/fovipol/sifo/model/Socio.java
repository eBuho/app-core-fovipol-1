/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ebuho
 */
@Entity
public class Socio {
    @Id
    private String cip;
    private String dni;
    private String nombres;
    private String apellidos;
    private String estado;

    public Socio() {
    }
    
    public Socio(String cip, String dni, String nombres, String apellidos, String estado) {
        this.cip = cip;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Socio{" + "cip=" + getCip() + ", dni=" + getDni() + ", nombres=" + getNombres() + ", apellidos=" + getApellidos() + '}';
    }
    
    /**
     * @return the cip
     */
    public String getCip() {
        return cip;
    }

    /**
     * @param cip the cip to set
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
