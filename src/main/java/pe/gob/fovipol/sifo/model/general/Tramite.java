package pe.gob.fovipol.sifo.model.general;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ebuho 25/10/2014
 */
@Entity
public class Tramite implements Serializable{
    @Id
    private int id;
    @JoinColumn(name = "TIPO", referencedColumnName = "ID")
    @ManyToOne
    private TipoTramite tipo;
    private String descripcion;
    private String fecha;
    private String estado;

    public Tramite() {
    }

    public Tramite(int id, TipoTramite tipo, String descripcion, String fecha, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tramite{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public TipoTramite getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoTramite tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
