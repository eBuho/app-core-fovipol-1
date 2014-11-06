/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.tramite;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author ebuho
 */
@Entity
@Table(name = "REC_APORTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecAporte.findAll", query = "SELECT r FROM RecAporte r"),
    @NamedQuery(name = "RecAporte.findByIdenAporApo", query = "SELECT r FROM RecAporte r WHERE r.idenAporApo = :idenAporApo"),
    @NamedQuery(name = "RecAporte.findByNanoCobrApo", query = "SELECT r FROM RecAporte r WHERE r.nanoCobrApo = :nanoCobrApo"),
    @NamedQuery(name = "RecAporte.findByNmesCobrApo", query = "SELECT r FROM RecAporte r WHERE r.nmesCobrApo = :nmesCobrApo"),
    @NamedQuery(name = "RecAporte.findByImpoCobrApo", query = "SELECT r FROM RecAporte r WHERE r.impoCobrApo = :impoCobrApo"),
    @NamedQuery(name = "RecAporte.findByNumeAporApo", query = "SELECT r FROM RecAporte r WHERE r.numeAporApo = :numeAporApo"),
    @NamedQuery(name = "RecAporte.findByCodiSituSoc", query = "SELECT r FROM RecAporte r WHERE r.codiSituSoc = :codiSituSoc"),
    @NamedQuery(name = "RecAporte.findByUsuaCreaAud", query = "SELECT r FROM RecAporte r WHERE r.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "RecAporte.findByFechCreaAud", query = "SELECT r FROM RecAporte r WHERE r.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "RecAporte.findByUsuaModiAud", query = "SELECT r FROM RecAporte r WHERE r.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "RecAporte.findByFechModiAud", query = "SELECT r FROM RecAporte r WHERE r.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "RecAporte.findByNombEquiAud", query = "SELECT r FROM RecAporte r WHERE r.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "RecAporte.findByNombSopeAud", query = "SELECT r FROM RecAporte r WHERE r.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "RecAporte.findByFlagEstaApo", query = "SELECT r FROM RecAporte r WHERE r.flagEstaApo = :flagEstaApo")})
public class RecAporte implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_APOR_APO")
    private BigDecimal idenAporApo;
    @Size(max = 4)
    @Column(name = "NANO_COBR_APO")
    private String nanoCobrApo;
    @Size(max = 2)
    @Column(name = "NMES_COBR_APO")
    private String nmesCobrApo;
    @Column(name = "IMPO_COBR_APO")
    private BigDecimal impoCobrApo;
    @Column(name = "NUME_APOR_APO")
    private Integer numeAporApo;
    @Column(name = "CODI_SITU_SOC")
    private Integer codiSituSoc;
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
    @Column(name = "FLAG_ESTA_APO")
    private short flagEstaApo;

    public RecAporte() {
    }

    public RecAporte(BigDecimal idenAporApo) {
        this.idenAporApo = idenAporApo;
    }

    public RecAporte(BigDecimal idenAporApo, short flagEstaApo) {
        this.idenAporApo = idenAporApo;
        this.flagEstaApo = flagEstaApo;
    }

    public BigDecimal getIdenAporApo() {
        return idenAporApo;
    }

    public void setIdenAporApo(BigDecimal idenAporApo) {
        this.idenAporApo = idenAporApo;
    }

    public String getNanoCobrApo() {
        return nanoCobrApo;
    }

    public void setNanoCobrApo(String nanoCobrApo) {
        this.nanoCobrApo = nanoCobrApo;
    }

    public String getNmesCobrApo() {
        return nmesCobrApo;
    }

    public void setNmesCobrApo(String nmesCobrApo) {
        this.nmesCobrApo = nmesCobrApo;
    }

    public BigDecimal getImpoCobrApo() {
        return impoCobrApo;
    }

    public void setImpoCobrApo(BigDecimal impoCobrApo) {
        this.impoCobrApo = impoCobrApo;
    }

    public Integer getNumeAporApo() {
        return numeAporApo;
    }

    public void setNumeAporApo(Integer numeAporApo) {
        this.numeAporApo = numeAporApo;
    }

    public Integer getCodiSituSoc() {
        return codiSituSoc;
    }

    public void setCodiSituSoc(Integer codiSituSoc) {
        this.codiSituSoc = codiSituSoc;
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

    public short getFlagEstaApo() {
        return flagEstaApo;
    }

    public void setFlagEstaApo(short flagEstaApo) {
        this.flagEstaApo = flagEstaApo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenAporApo != null ? idenAporApo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecAporte)) {
            return false;
        }
        RecAporte other = (RecAporte) object;
        if ((this.idenAporApo == null && other.idenAporApo != null) || (this.idenAporApo != null && !this.idenAporApo.equals(other.idenAporApo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.RecAporte[ idenAporApo=" + idenAporApo + " ]";
    }
    
}
