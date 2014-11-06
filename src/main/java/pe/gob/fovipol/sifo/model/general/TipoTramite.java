package pe.gob.fovipol.sifo.model.general;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author ebuho
 */
@Entity
public class TipoTramite implements Serializable{
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 255)
    @Column(name = "PAGINA")
    private String pagina;
    @OneToMany(mappedBy = "tipo")
    private List<Tramite> tramiteList;
    @Id

    @Override
    public String toString() {
        return getNombre();
    }

    
    
    public TipoTramite() {
    }

    public TipoTramite(long id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public TipoTramite(long id, String nombre, String pagina, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.pagina = pagina;
        this.estado = estado;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.setId((Long) id);
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

    public TipoTramite(Long id) {
        this.id = id;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tramite> getTramiteList() {
        return tramiteList;
    }

    public void setTramiteList(List<Tramite> tramiteList) {
        this.tramiteList = tramiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTramite)) {
            return false;
        }
        TipoTramite other = (TipoTramite) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
