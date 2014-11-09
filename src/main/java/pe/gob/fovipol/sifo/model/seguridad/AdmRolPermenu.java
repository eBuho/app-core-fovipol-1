/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.seguridad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pe.gob.fovipol.sifo.listener.AuditListener;

/**
 *
 * @author eBuho
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "ADM_ROL_PERMENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmRolPermenu.findAll", query = "SELECT a FROM AdmRolPermenu a"),
    @NamedQuery(name = "AdmRolPermenu.findByIdenRoleRpm", query = "SELECT a FROM AdmRolPermenu a WHERE a.idenRoleRpm = :idenRoleRpm"),
    @NamedQuery(name = "AdmRolPermenu.findByUsuaCreaAud", query = "SELECT a FROM AdmRolPermenu a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmRolPermenu.findByFechCreaAud", query = "SELECT a FROM AdmRolPermenu a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmRolPermenu.findByUsuaModiAud", query = "SELECT a FROM AdmRolPermenu a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmRolPermenu.findByFechModiAud", query = "SELECT a FROM AdmRolPermenu a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmRolPermenu.findByNombEquiAud", query = "SELECT a FROM AdmRolPermenu a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmRolPermenu.findByNombSopeAud", query = "SELECT a FROM AdmRolPermenu a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmRolPermenu.findByFlagEstaRpm", query = "SELECT a FROM AdmRolPermenu a WHERE a.flagEstaRpm = :flagEstaRpm")})
public class AdmRolPermenu implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @TableGenerator(name = "secAdmRolPermenu",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "ADM_ROL_PERMENU", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="secAdmRolPermenu")
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_ROLE_RPM", updatable=false)
    private BigDecimal idenRoleRpm;
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
    @Column(name = "FLAG_ESTA_RPM")
    private short flagEstaRpm;
    @JoinColumn(name = "IDEN_PERM_PMN", referencedColumnName = "IDEN_PERM_PMN")
    @ManyToOne
    private AdmPermisoMenu idenPermPmn;
    @JoinColumn(name = "IDEN_ROLE_ROL", referencedColumnName = "IDEN_ROLE_ROL")
    @ManyToOne
    private AdmRol idenRoleRol;

    public AdmRolPermenu() {
    }

    public AdmRolPermenu(BigDecimal idenRoleRpm) {
        this.idenRoleRpm = idenRoleRpm;
    }

    public AdmRolPermenu(BigDecimal idenRoleRpm, short flagEstaRpm) {
        this.idenRoleRpm = idenRoleRpm;
        this.flagEstaRpm = flagEstaRpm;
    }

    public BigDecimal getIdenRoleRpm() {
        return idenRoleRpm;
    }

    public void setIdenRoleRpm(BigDecimal idenRoleRpm) {
        this.idenRoleRpm = idenRoleRpm;
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

    public short getFlagEstaRpm() {
        return flagEstaRpm;
    }

    public void setFlagEstaRpm(short flagEstaRpm) {
        this.flagEstaRpm = flagEstaRpm;
    }

    public AdmPermisoMenu getIdenPermPmn() {
        return idenPermPmn;
    }

    public void setIdenPermPmn(AdmPermisoMenu idenPermPmn) {
        this.idenPermPmn = idenPermPmn;
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
        hash += (idenRoleRpm != null ? idenRoleRpm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmRolPermenu)) {
            return false;
        }
        AdmRolPermenu other = (AdmRolPermenu) object;
        if ((this.idenRoleRpm == null && other.idenRoleRpm != null) || (this.idenRoleRpm != null && !this.idenRoleRpm.equals(other.idenRoleRpm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmRolPermenu[ idenRoleRpm=" + idenRoleRpm + " ]";
    }
    
}
