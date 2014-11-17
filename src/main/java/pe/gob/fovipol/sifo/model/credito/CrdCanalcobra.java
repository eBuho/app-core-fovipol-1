/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "CRD_CANALCOBRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdCanalcobra.findAll", query = "SELECT c FROM CrdCanalcobra c"),
    @NamedQuery(name = "CrdCanalcobra.findByIdenCredCrd", query = "SELECT c FROM CrdCanalcobra c WHERE c.crdCanalcobraPK.idenCredCrd = :idenCredCrd"),
    @NamedQuery(name = "CrdCanalcobra.findBySecuCanaCdc", query = "SELECT c FROM CrdCanalcobra c WHERE c.crdCanalcobraPK.secuCanaCdc = :secuCanaCdc"),
    @NamedQuery(name = "CrdCanalcobra.findByCodiCanaCob", query = "SELECT c FROM CrdCanalcobra c WHERE c.codiCanaCob = :codiCanaCob"),
    @NamedQuery(name = "CrdCanalcobra.findByImpoCobrCdc", query = "SELECT c FROM CrdCanalcobra c WHERE c.impoCobrCdc = :impoCobrCdc"),
    @NamedQuery(name = "CrdCanalcobra.findByFechInicCdc", query = "SELECT c FROM CrdCanalcobra c WHERE c.fechInicCdc = :fechInicCdc"),
    @NamedQuery(name = "CrdCanalcobra.findByFechFinaCdc", query = "SELECT c FROM CrdCanalcobra c WHERE c.fechFinaCdc = :fechFinaCdc"),
    @NamedQuery(name = "CrdCanalcobra.findByUsuaCreaAud", query = "SELECT c FROM CrdCanalcobra c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdCanalcobra.findByFechCreaAud", query = "SELECT c FROM CrdCanalcobra c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdCanalcobra.findByUsuaModiAud", query = "SELECT c FROM CrdCanalcobra c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdCanalcobra.findByFechModiAud", query = "SELECT c FROM CrdCanalcobra c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdCanalcobra.findByNombEquiAud", query = "SELECT c FROM CrdCanalcobra c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdCanalcobra.findByNombSopeAud", query = "SELECT c FROM CrdCanalcobra c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdCanalcobra.findByFlagEstaCdc", query = "SELECT c FROM CrdCanalcobra c WHERE c.flagEstaCdc = :flagEstaCdc")})
public class CrdCanalcobra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrdCanalcobraPK crdCanalcobraPK;
    @Column(name = "CODI_CANA_COB")
    private Integer codiCanaCob;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMPO_COBR_CDC")
    private BigDecimal impoCobrCdc;
    @Column(name = "FECH_INIC_CDC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechInicCdc;
    @Column(name = "FECH_FINA_CDC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechFinaCdc;
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
    @Column(name = "FLAG_ESTA_CDC")
    private Short flagEstaCdc;
    @JoinColumn(name = "IDEN_CRED_CRD", referencedColumnName = "IDEN_CRED_CRD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CrdCredito crdCredito;

    public CrdCanalcobra() {
    }

    public CrdCanalcobra(CrdCanalcobraPK crdCanalcobraPK) {
        this.crdCanalcobraPK = crdCanalcobraPK;
    }

    public CrdCanalcobra(BigInteger idenCredCrd, short secuCanaCdc) {
        this.crdCanalcobraPK = new CrdCanalcobraPK(idenCredCrd, secuCanaCdc);
    }

    public CrdCanalcobraPK getCrdCanalcobraPK() {
        return crdCanalcobraPK;
    }

    public void setCrdCanalcobraPK(CrdCanalcobraPK crdCanalcobraPK) {
        this.crdCanalcobraPK = crdCanalcobraPK;
    }

    public Integer getCodiCanaCob() {
        return codiCanaCob;
    }

    public void setCodiCanaCob(Integer codiCanaCob) {
        this.codiCanaCob = codiCanaCob;
    }

    public BigDecimal getImpoCobrCdc() {
        return impoCobrCdc;
    }

    public void setImpoCobrCdc(BigDecimal impoCobrCdc) {
        this.impoCobrCdc = impoCobrCdc;
    }

    public Date getFechInicCdc() {
        return fechInicCdc;
    }

    public void setFechInicCdc(Date fechInicCdc) {
        this.fechInicCdc = fechInicCdc;
    }

    public Date getFechFinaCdc() {
        return fechFinaCdc;
    }

    public void setFechFinaCdc(Date fechFinaCdc) {
        this.fechFinaCdc = fechFinaCdc;
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

    public Short getFlagEstaCdc() {
        return flagEstaCdc;
    }

    public void setFlagEstaCdc(Short flagEstaCdc) {
        this.flagEstaCdc = flagEstaCdc;
    }

    public CrdCredito getCrdCredito() {
        return crdCredito;
    }

    public void setCrdCredito(CrdCredito crdCredito) {
        this.crdCredito = crdCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crdCanalcobraPK != null ? crdCanalcobraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCanalcobra)) {
            return false;
        }
        CrdCanalcobra other = (CrdCanalcobra) object;
        if ((this.crdCanalcobraPK == null && other.crdCanalcobraPK != null) || (this.crdCanalcobraPK != null && !this.crdCanalcobraPK.equals(other.crdCanalcobraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCanalcobra[ crdCanalcobraPK=" + crdCanalcobraPK + " ]";
    }
    
}
