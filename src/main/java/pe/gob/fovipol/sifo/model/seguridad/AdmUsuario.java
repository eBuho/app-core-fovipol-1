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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;

/**
 *
 * @author eBuho
 */
@Entity
@Table(name = "ADM_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmUsuario.findAll", query = "SELECT a FROM AdmUsuario a"),
    @NamedQuery(name = "AdmUsuario.findByIdenPersPer", query = "SELECT a FROM AdmUsuario a WHERE a.idenPersPer = :idenPersPer"),
    @NamedQuery(name = "AdmUsuario.findByCodiUsuaUsr", query = "SELECT a FROM AdmUsuario a WHERE a.codiUsuaUsr = :codiUsuaUsr"),
    @NamedQuery(name = "AdmUsuario.findByClavUsuaUsr", query = "SELECT a FROM AdmUsuario a WHERE a.clavUsuaUsr = :clavUsuaUsr"),
    @NamedQuery(name = "AdmUsuario.findByCorrRecuUsr", query = "SELECT a FROM AdmUsuario a WHERE a.corrRecuUsr = :corrRecuUsr"),
    @NamedQuery(name = "AdmUsuario.findByFechExpiUsr", query = "SELECT a FROM AdmUsuario a WHERE a.fechExpiUsr = :fechExpiUsr"),
    @NamedQuery(name = "AdmUsuario.findByUsuaCreaAud", query = "SELECT a FROM AdmUsuario a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmUsuario.findByFechCreaAud", query = "SELECT a FROM AdmUsuario a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmUsuario.findByUsuaModiAud", query = "SELECT a FROM AdmUsuario a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmUsuario.findByFechModiAud", query = "SELECT a FROM AdmUsuario a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmUsuario.findByNombEquiAud", query = "SELECT a FROM AdmUsuario a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmUsuario.findByNombSopeAud", query = "SELECT a FROM AdmUsuario a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmUsuario.findByFlagEstaUsr", query = "SELECT a FROM AdmUsuario a WHERE a.flagEstaUsr = :flagEstaUsr")})
public class AdmUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PERS_PER")
    private BigDecimal idenPersPer;
    @Size(max = 15)
    @Column(name = "CODI_USUA_USR")
    private String codiUsuaUsr;
    @Size(max = 128)
    @Column(name = "CLAV_USUA_USR")
    private String clavUsuaUsr;
    @Size(max = 50)
    @Column(name = "CORR_RECU_USR")
    private String corrRecuUsr;
    @Column(name = "FECH_EXPI_USR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechExpiUsr;
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
    @Column(name = "FLAG_ESTA_USR")
    private Short flagEstaUsr;
    @JoinColumn(name = "IDEN_PERS_PER", referencedColumnName = "IDEN_PERS_PER", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MaePersona maePersona;
    @OneToMany(mappedBy = "idenPersPer")
    private List<AdmUsuarioRol> admUsuarioRolList;

    public AdmUsuario() {
    }

    public AdmUsuario(BigDecimal idenPersPer) {
        this.idenPersPer = idenPersPer;
    }

    public BigDecimal getIdenPersPer() {
        return idenPersPer;
    }

    public void setIdenPersPer(BigDecimal idenPersPer) {
        this.idenPersPer = idenPersPer;
    }

    public String getCodiUsuaUsr() {
        return codiUsuaUsr;
    }

    public void setCodiUsuaUsr(String codiUsuaUsr) {
        this.codiUsuaUsr = codiUsuaUsr;
    }

    public String getClavUsuaUsr() {
        return clavUsuaUsr;
    }

    public void setClavUsuaUsr(String clavUsuaUsr) {
        this.clavUsuaUsr = clavUsuaUsr;
    }

    public String getCorrRecuUsr() {
        return corrRecuUsr;
    }

    public void setCorrRecuUsr(String corrRecuUsr) {
        this.corrRecuUsr = corrRecuUsr;
    }

    public Date getFechExpiUsr() {
        return fechExpiUsr;
    }

    public void setFechExpiUsr(Date fechExpiUsr) {
        this.fechExpiUsr = fechExpiUsr;
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

    public Short getFlagEstaUsr() {
        return flagEstaUsr;
    }

    public void setFlagEstaUsr(Short flagEstaUsr) {
        this.flagEstaUsr = flagEstaUsr;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmUsuarioRol> getAdmUsuarioRolList() {
        return admUsuarioRolList;
    }

    public void setAdmUsuarioRolList(List<AdmUsuarioRol> admUsuarioRolList) {
        this.admUsuarioRolList = admUsuarioRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenPersPer != null ? idenPersPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmUsuario)) {
            return false;
        }
        AdmUsuario other = (AdmUsuario) object;
        if ((this.idenPersPer == null && other.idenPersPer != null) || (this.idenPersPer != null && !this.idenPersPer.equals(other.idenPersPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmUsuario[ idenPersPer=" + idenPersPer + " ]";
    }
    
}
