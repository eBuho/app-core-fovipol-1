package pe.gob.fovipol.sifo.model.general;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ebuho
 */
@Entity
public class TipoTramite implements Serializable{
    @Id
    private int id;
    private String nombre;
    private String pagina;
    private String estado;

    @Override
    public String toString() {
        return nombre;
    }

    
    
    public TipoTramite() {
    }

    public TipoTramite(int id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public TipoTramite(int id, String nombre, String pagina, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.pagina = pagina;
        this.estado = estado;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    
}
