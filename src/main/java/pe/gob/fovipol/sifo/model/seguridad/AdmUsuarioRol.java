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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eBuho
 */
@Entity
@Table(name = "ADM_USUARIO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmUsuarioRol.findAll", query = "SELECT a FROM AdmUsuarioRol a"),
    @NamedQuery(name = "AdmUsuarioRol.findByIdenUsuaUro", query = "SELECT a FROM AdmUsuarioRol a WHERE a.idenUsuaUro = :idenUsuaUro"),
    @NamedQuery(name = "AdmUsuarioRol.findByUsuaCreaAud", query = "SELECT a FROM AdmUsuarioRol a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmUsuarioRol.findByFechCreaAud", query = "SELECT a FROM AdmUsuarioRol a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmUsuarioRol.findByUsuaModiAud", query = "SELECT a FROM AdmUsuarioRol a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmUsuarioRol.findByFechModiAud", query = "SELECT a FROM AdmUsuarioRol a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmUsuarioRol.findByNombEquiAud", query = "SELECT a FROM AdmUsuarioRol a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmUsuarioRol.findByNombSopeAud", query = "SELECT a FROM AdmUsuarioRol a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmUsuarioRol.findByFlagEstaUro", query = "SELECT a FROM AdmUsuarioRol a WHERE a.flagEstaUro = :flagEstaUro")})
public class AdmUsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_USUA_URO")
    private BigDecimal idenUsuaUro;
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
    @Column(name = "FLAG_ESTA_URO")
    private short flagEstaUro;
    @JoinColumn(name = "IDEN_ROLE_ROL", referencedColumnName = "IDEN_ROLE_ROL")
    @ManyToOne
    private AdmRol idenRoleRol;
    @JoinColumn(name = "IDEN_PERS_PER", referencedColumnName = "IDEN_PERS_PER")
    @ManyToOne
    private AdmUsuario idenPersPer;

    public AdmUsuarioRol() {
    }

    public AdmUsuarioRol(BigDecimal idenUsuaUro) {
        this.idenUsuaUro = idenUsuaUro;
    }

    public AdmUsuarioRol(BigDecimal idenUsuaUro, short flagEstaUro) {
        this.idenUsuaUro = idenUsuaUro;
        this.flagEstaUro = flagEstaUro;
    }

    public BigDecimal getIdenUsuaUro() {
        return idenUsuaUro;
    }

    public void setIdenUsuaUro(BigDecimal idenUsuaUro) {
        this.idenUsuaUro = idenUsuaUro;
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

    public short getFlagEstaUro() {
        return flagEstaUro;
    }

    public void setFlagEstaUro(short flagEstaUro) {
        this.flagEstaUro = flagEstaUro;
    }

    public AdmRol getIdenRoleRol() {
        return idenRoleRol;
    }

    public void setIdenRoleRol(AdmRol idenRoleRol) {
        this.idenRoleRol = idenRoleRol;
    }

    public AdmUsuario getIdenPersPer() {
        return idenPersPer;
    }

    public void setIdenPersPer(AdmUsuario idenPersPer) {
        this.idenPersPer = idenPersPer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenUsuaUro != null ? idenUsuaUro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmUsuarioRol)) {
            return false;
        }
        AdmUsuarioRol other = (AdmUsuarioRol) object;
        if ((this.idenUsuaUro == null && other.idenUsuaUro != null) || (this.idenUsuaUro != null && !this.idenUsuaUro.equals(other.idenUsuaUro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmUsuarioRol[ idenUsuaUro=" + idenUsuaUro + " ]";
    }
    
}
