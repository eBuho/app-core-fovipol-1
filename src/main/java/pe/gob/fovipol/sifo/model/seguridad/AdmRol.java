/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.seguridad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author eBuho
 */
@Entity
@Table(name = "ADM_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmRol.findAll", query = "SELECT a FROM AdmRol a"),
    @NamedQuery(name = "AdmRol.findByIdenRoleRol", query = "SELECT a FROM AdmRol a WHERE a.idenRoleRol = :idenRoleRol"),
    @NamedQuery(name = "AdmRol.findByNombRoleRol", query = "SELECT a FROM AdmRol a WHERE a.nombRoleRol = :nombRoleRol"),
    @NamedQuery(name = "AdmRol.findByUsuaCreaAud", query = "SELECT a FROM AdmRol a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmRol.findByFechCreaAud", query = "SELECT a FROM AdmRol a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmRol.findByUsuaModiAud", query = "SELECT a FROM AdmRol a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmRol.findByFechModiAud", query = "SELECT a FROM AdmRol a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmRol.findByNombEquiAud", query = "SELECT a FROM AdmRol a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmRol.findByNombSopeAud", query = "SELECT a FROM AdmRol a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmRol.findByFlagEstaRol", query = "SELECT a FROM AdmRol a WHERE a.flagEstaRol = :flagEstaRol")})
public class AdmRol implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_ROLE_ROL")
    private BigDecimal idenRoleRol;
    @Size(max = 120)
    @Column(name = "NOMB_ROLE_ROL")
    private String nombRoleRol;
    @Size(max = 15)
    @Column(name = "USUA_CREA_AUD")
    private String usuaCreaAud;
    @Column(name = "FECH_CREA_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaAud;
    @Size(max = 15)
    @Column(name = "USUA_MODI_AUD")
    private String usuaModiAud;
    @Column(name = "FECH_MODI_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechModiAud;
    @Size(max = 40)
    @Column(name = "NOMB_EQUI_AUD")
    private String nombEquiAud;
    @Size(max = 40)
    @Column(name = "NOMB_SOPE_AUD")
    private String nombSopeAud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FLAG_ESTA_ROL")
    private short flagEstaRol;
    @OneToMany(mappedBy = "idenRoleRol")
    private List<AdmUsuarioRol> admUsuarioRolList;
    @OneToMany(mappedBy = "idenRoleRol")
    private List<AdmModuloRol> admModuloRolList;
    @OneToMany(mappedBy = "idenRoleRol")
    private List<AdmRolPermenu> admRolPermenuList;

    public AdmRol() {
    }

    public AdmRol(BigDecimal idenRoleRol) {
        this.idenRoleRol = idenRoleRol;
    }

    public AdmRol(BigDecimal idenRoleRol, short flagEstaRol) {
        this.idenRoleRol = idenRoleRol;
        this.flagEstaRol = flagEstaRol;
    }

    public BigDecimal getIdenRoleRol() {
        return idenRoleRol;
    }

    public void setIdenRoleRol(BigDecimal idenRoleRol) {
        this.idenRoleRol = idenRoleRol;
    }

    public String getNombRoleRol() {
        return nombRoleRol;
    }

    public void setNombRoleRol(String nombRoleRol) {
        this.nombRoleRol = nombRoleRol;
    }

    public String getUsuaCreaAud() {
        return usuaCreaAud;
    }

    public void setUsuaCreaAud(String usuaCreaAud) {
        this.usuaCreaAud = usuaCreaAud;
    }

    public Date getFechCreaAud() {
        return fechCreaAud;
    }

    public void setFechCreaAud(Date fechCreaAud) {
        this.fechCreaAud = fechCreaAud;
    }

    public String getUsuaModiAud() {
        return usuaModiAud;
    }

    public void setUsuaModiAud(String usuaModiAud) {
        this.usuaModiAud = usuaModiAud;
    }

    public Date getFechModiAud() {
        return fechModiAud;
    }

    public void setFechModiAud(Date fechModiAud) {
        this.fechModiAud = fechModiAud;
    }

    public String getNombEquiAud() {
        return nombEquiAud;
    }

    public void setNombEquiAud(String nombEquiAud) {
        this.nombEquiAud = nombEquiAud;
    }

    public String getNombSopeAud() {
        return nombSopeAud;
    }

    public void setNombSopeAud(String nombSopeAud) {
        this.nombSopeAud = nombSopeAud;
    }

    public short getFlagEstaRol() {
        return flagEstaRol;
    }

    public void setFlagEstaRol(short flagEstaRol) {
        this.flagEstaRol = flagEstaRol;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmUsuarioRol> getAdmUsuarioRolList() {
        return admUsuarioRolList;
    }

    public void setAdmUsuarioRolList(List<AdmUsuarioRol> admUsuarioRolList) {
        this.admUsuarioRolList = admUsuarioRolList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmModuloRol> getAdmModuloRolList() {
        return admModuloRolList;
    }

    public void setAdmModuloRolList(List<AdmModuloRol> admModuloRolList) {
        this.admModuloRolList = admModuloRolList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmRolPermenu> getAdmRolPermenuList() {
        return admRolPermenuList;
    }

    public void setAdmRolPermenuList(List<AdmRolPermenu> admRolPermenuList) {
        this.admRolPermenuList = admRolPermenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenRoleRol != null ? idenRoleRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmRol)) {
            return false;
        }
        AdmRol other = (AdmRol) object;
        if ((this.idenRoleRol == null && other.idenRoleRol != null) || (this.idenRoleRol != null && !this.idenRoleRol.equals(other.idenRoleRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmRol[ idenRoleRol=" + idenRoleRol + " ]";
    }
    
}
