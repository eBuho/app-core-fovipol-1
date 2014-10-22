/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_ENTIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeEntidad.findAll", query = "SELECT m FROM MaeEntidad m"),
    @NamedQuery(name = "MaeEntidad.findByCodiEntiEnt", query = "SELECT m FROM MaeEntidad m WHERE m.codiEntiEnt = :codiEntiEnt"),
    @NamedQuery(name = "MaeEntidad.findByNombEntiEnt", query = "SELECT m FROM MaeEntidad m WHERE m.nombEntiEnt = :nombEntiEnt"),
    @NamedQuery(name = "MaeEntidad.findByDescLargEnt", query = "SELECT m FROM MaeEntidad m WHERE m.descLargEnt = :descLargEnt"),
    @NamedQuery(name = "MaeEntidad.findByFlagEstaEnt", query = "SELECT m FROM MaeEntidad m WHERE m.flagEstaEnt = :flagEstaEnt")})
public class MaeEntidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CODI_ENTI_ENT")
    private String codiEntiEnt;
    @Size(max = 50)
    @Column(name = "NOMB_ENTI_ENT")
    private String nombEntiEnt;
    @Size(max = 200)
    @Column(name = "DESC_LARG_ENT")
    private String descLargEnt;
    @Column(name = "FLAG_ESTA_ENT")
    private Short flagEstaEnt;
    @OneToMany(mappedBy = "codiEntiEnt")
    private List<MaeEntidaddet> maeEntidaddetList;

    public MaeEntidad() {
    }

    public MaeEntidad(String codiEntiEnt) {
        this.codiEntiEnt = codiEntiEnt;
    }

    public String getCodiEntiEnt() {
        return codiEntiEnt;
    }

    public void setCodiEntiEnt(String codiEntiEnt) {
        this.codiEntiEnt = codiEntiEnt;
    }

    public String getNombEntiEnt() {
        return nombEntiEnt;
    }

    public void setNombEntiEnt(String nombEntiEnt) {
        this.nombEntiEnt = nombEntiEnt;
    }

    public String getDescLargEnt() {
        return descLargEnt;
    }

    public void setDescLargEnt(String descLargEnt) {
        this.descLargEnt = descLargEnt;
    }

    public Short getFlagEstaEnt() {
        return flagEstaEnt;
    }

    public void setFlagEstaEnt(Short flagEstaEnt) {
        this.flagEstaEnt = flagEstaEnt;
    }

    @XmlTransient
    public List<MaeEntidaddet> getMaeEntidaddetList() {
        return maeEntidaddetList;
    }

    public void setMaeEntidaddetList(List<MaeEntidaddet> maeEntidaddetList) {
        this.maeEntidaddetList = maeEntidaddetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEntiEnt != null ? codiEntiEnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeEntidad)) {
            return false;
        }
        MaeEntidad other = (MaeEntidad) object;
        if ((this.codiEntiEnt == null && other.codiEntiEnt != null) || (this.codiEntiEnt != null && !this.codiEntiEnt.equals(other.codiEntiEnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.model.MaeEntidad[ codiEntiEnt=" + codiEntiEnt + " ]";
    }
    
}
