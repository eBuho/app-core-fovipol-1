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
@Table(name = "ADM_MODULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmModulo.findAll", query = "SELECT a FROM AdmModulo a"),
    @NamedQuery(name = "AdmModulo.findByIdenModuMod", query = "SELECT a FROM AdmModulo a WHERE a.idenModuMod = :idenModuMod"),
    @NamedQuery(name = "AdmModulo.findByNombModuMod", query = "SELECT a FROM AdmModulo a WHERE a.nombModuMod = :nombModuMod"),
    @NamedQuery(name = "AdmModulo.findByRutaImagMod", query = "SELECT a FROM AdmModulo a WHERE a.rutaImagMod = :rutaImagMod"),
    @NamedQuery(name = "AdmModulo.findByUsuaCreaAud", query = "SELECT a FROM AdmModulo a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmModulo.findByFechCreaAud", query = "SELECT a FROM AdmModulo a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmModulo.findByUsuaModiAud", query = "SELECT a FROM AdmModulo a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmModulo.findByFechModiAud", query = "SELECT a FROM AdmModulo a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmModulo.findByNombEquiAud", query = "SELECT a FROM AdmModulo a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmModulo.findByNombSopeAud", query = "SELECT a FROM AdmModulo a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmModulo.findByFlagEstaMod", query = "SELECT a FROM AdmModulo a WHERE a.flagEstaMod = :flagEstaMod")})
public class AdmModulo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_MODU_MOD")
    private BigDecimal idenModuMod;
    @Size(max = 120)
    @Column(name = "NOMB_MODU_MOD")
    private String nombModuMod;
    @Size(max = 200)
    @Column(name = "RUTA_IMAG_MOD")
    private String rutaImagMod;
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
    @Column(name = "FLAG_ESTA_MOD")
    private short flagEstaMod;
    @OneToMany(mappedBy = "idenModuMod")
    private List<AdmMenuModulo> admMenuModuloList;
    @OneToMany(mappedBy = "idenModuMod")
    private List<AdmModuloRol> admModuloRolList;

    public AdmModulo() {
    }

    public AdmModulo(BigDecimal idenModuMod) {
        this.idenModuMod = idenModuMod;
    }

    public AdmModulo(BigDecimal idenModuMod, short flagEstaMod) {
        this.idenModuMod = idenModuMod;
        this.flagEstaMod = flagEstaMod;
    }

    public BigDecimal getIdenModuMod() {
        return idenModuMod;
    }

    public void setIdenModuMod(BigDecimal idenModuMod) {
        this.idenModuMod = idenModuMod;
    }

    public String getNombModuMod() {
        return nombModuMod;
    }

    public void setNombModuMod(String nombModuMod) {
        this.nombModuMod = nombModuMod;
    }

    public String getRutaImagMod() {
        return rutaImagMod;
    }

    public void setRutaImagMod(String rutaImagMod) {
        this.rutaImagMod = rutaImagMod;
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

    public short getFlagEstaMod() {
        return flagEstaMod;
    }

    public void setFlagEstaMod(short flagEstaMod) {
        this.flagEstaMod = flagEstaMod;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmMenuModulo> getAdmMenuModuloList() {
        return admMenuModuloList;
    }

    public void setAdmMenuModuloList(List<AdmMenuModulo> admMenuModuloList) {
        this.admMenuModuloList = admMenuModuloList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmModuloRol> getAdmModuloRolList() {
        return admModuloRolList;
    }

    public void setAdmModuloRolList(List<AdmModuloRol> admModuloRolList) {
        this.admModuloRolList = admModuloRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenModuMod != null ? idenModuMod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmModulo)) {
            return false;
        }
        AdmModulo other = (AdmModulo) object;
        if ((this.idenModuMod == null && other.idenModuMod != null) || (this.idenModuMod != null && !this.idenModuMod.equals(other.idenModuMod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmModulo[ idenModuMod=" + idenModuMod + " ]";
    }
    
}
