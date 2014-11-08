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
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.listener.AuditListener;

/**
 *
 * @author eBuho
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "ADM_MODULO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmModuloRol.findAll", query = "SELECT a FROM AdmModuloRol a"),
    @NamedQuery(name = "AdmModuloRol.findByIdenMoroMdr", query = "SELECT a FROM AdmModuloRol a WHERE a.idenMoroMdr = :idenMoroMdr"),
    @NamedQuery(name = "AdmModuloRol.findByUsuaCreaAud", query = "SELECT a FROM AdmModuloRol a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmModuloRol.findByFechCreaAud", query = "SELECT a FROM AdmModuloRol a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmModuloRol.findByUsuaModiAud", query = "SELECT a FROM AdmModuloRol a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmModuloRol.findByFechModiAud", query = "SELECT a FROM AdmModuloRol a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmModuloRol.findByNombEquiAud", query = "SELECT a FROM AdmModuloRol a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmModuloRol.findByNombSopeAud", query = "SELECT a FROM AdmModuloRol a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmModuloRol.findByFlagEstaMdr", query = "SELECT a FROM AdmModuloRol a WHERE a.flagEstaMdr = :flagEstaMdr")})
public class AdmModuloRol implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @TableGenerator(name = "secAdmModuloRol",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "ADM_MODULO_ROL", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="secAdmModuloRol")
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_MORO_MDR", updatable=false)
    private BigDecimal idenMoroMdr;
    @Size(max = 15)
    @Column(name = "USUA_CREA_AUD", updatable=false)
    private String usuaCreaAud;
    @Column(name = "FECH_CREA_AUD", updatable=false)
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
    @Column(name = "FLAG_ESTA_MDR")
    private short flagEstaMdr;
    @OneToMany(mappedBy = "idenMoroMdr")
    private List<AdmModuloExcepcion> admModuloExcepcionList;
    @JoinColumn(name = "IDEN_MODU_MOD", referencedColumnName = "IDEN_MODU_MOD")
    @ManyToOne
    private AdmModulo idenModuMod;
    @JoinColumn(name = "IDEN_ROLE_ROL", referencedColumnName = "IDEN_ROLE_ROL")
    @ManyToOne
    private AdmRol idenRoleRol;

    public AdmModuloRol() {
    }

    public AdmModuloRol(BigDecimal idenMoroMdr) {
        this.idenMoroMdr = idenMoroMdr;
    }

    public AdmModuloRol(BigDecimal idenMoroMdr, short flagEstaMdr) {
        this.idenMoroMdr = idenMoroMdr;
        this.flagEstaMdr = flagEstaMdr;
    }

    public BigDecimal getIdenMoroMdr() {
        return idenMoroMdr;
    }

    public void setIdenMoroMdr(BigDecimal idenMoroMdr) {
        this.idenMoroMdr = idenMoroMdr;
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

    public short getFlagEstaMdr() {
        return flagEstaMdr;
    }

    public void setFlagEstaMdr(short flagEstaMdr) {
        this.flagEstaMdr = flagEstaMdr;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmModuloExcepcion> getAdmModuloExcepcionList() {
        return admModuloExcepcionList;
    }

    public void setAdmModuloExcepcionList(List<AdmModuloExcepcion> admModuloExcepcionList) {
        this.admModuloExcepcionList = admModuloExcepcionList;
    }

    public AdmModulo getIdenModuMod() {
        return idenModuMod;
    }

    public void setIdenModuMod(AdmModulo idenModuMod) {
        this.idenModuMod = idenModuMod;
    }

    public AdmRol getIdenRoleRol() {
        return idenRoleRol;
    }

    public void setIdenRoleRol(AdmRol idenRoleRol) {
        this.idenRoleRol = idenRoleRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenMoroMdr != null ? idenMoroMdr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmModuloRol)) {
            return false;
        }
        AdmModuloRol other = (AdmModuloRol) object;
        if ((this.idenMoroMdr == null && other.idenMoroMdr != null) || (this.idenMoroMdr != null && !this.idenMoroMdr.equals(other.idenMoroMdr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmModuloRol[ idenMoroMdr=" + idenMoroMdr + " ]";
    }
    
}
