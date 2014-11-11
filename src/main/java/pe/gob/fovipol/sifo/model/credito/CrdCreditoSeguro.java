/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

import java.io.Serializable;
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
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "CRD_CREDITO_SEGURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdCreditoSeguro.findAll", query = "SELECT c FROM CrdCreditoSeguro c"),
    @NamedQuery(name = "CrdCreditoSeguro.findByIdenCredCrd", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.crdCreditoSeguroPK.idenCredCrd = :idenCredCrd"),
    @NamedQuery(name = "CrdCreditoSeguro.findBySecuSeguCrs", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.crdCreditoSeguroPK.secuSeguCrs = :secuSeguCrs"),
    @NamedQuery(name = "CrdCreditoSeguro.findByUsuaCreaAud", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdCreditoSeguro.findByFechCreaAud", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdCreditoSeguro.findByUsuaModiAud", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdCreditoSeguro.findByFechModiAud", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdCreditoSeguro.findByNombEquiAud", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdCreditoSeguro.findByNombSopeAud", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdCreditoSeguro.findByFlagEstaCrs", query = "SELECT c FROM CrdCreditoSeguro c WHERE c.flagEstaCrs = :flagEstaCrs")})
public class CrdCreditoSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrdCreditoSeguroPK crdCreditoSeguroPK;
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
    @Column(name = "FLAG_ESTA_CRS")
    private Short flagEstaCrs;
    @JoinColumn(name = "IDEN_SEGU_SEG", referencedColumnName = "IDEN_SEGU_SEG")
    @ManyToOne
    private MaeSeguro idenSeguSeg;
    @JoinColumn(name = "IDEN_CRED_CRD", referencedColumnName = "IDEN_CRED_CRD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CrdCredito crdCredito;

    public CrdCreditoSeguro() {
    }

    public CrdCreditoSeguro(CrdCreditoSeguroPK crdCreditoSeguroPK) {
        this.crdCreditoSeguroPK = crdCreditoSeguroPK;
    }

    public CrdCreditoSeguro(BigInteger idenCredCrd, int secuSeguCrs) {
        this.crdCreditoSeguroPK = new CrdCreditoSeguroPK(idenCredCrd, secuSeguCrs);
    }

    public CrdCreditoSeguroPK getCrdCreditoSeguroPK() {
        return crdCreditoSeguroPK;
    }

    public void setCrdCreditoSeguroPK(CrdCreditoSeguroPK crdCreditoSeguroPK) {
        this.crdCreditoSeguroPK = crdCreditoSeguroPK;
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

    public Short getFlagEstaCrs() {
        return flagEstaCrs;
    }

    public void setFlagEstaCrs(Short flagEstaCrs) {
        this.flagEstaCrs = flagEstaCrs;
    }

    public MaeSeguro getIdenSeguSeg() {
        return idenSeguSeg;
    }

    public void setIdenSeguSeg(MaeSeguro idenSeguSeg) {
        this.idenSeguSeg = idenSeguSeg;
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
        hash += (crdCreditoSeguroPK != null ? crdCreditoSeguroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCreditoSeguro)) {
            return false;
        }
        CrdCreditoSeguro other = (CrdCreditoSeguro) object;
        if ((this.crdCreditoSeguroPK == null && other.crdCreditoSeguroPK != null) || (this.crdCreditoSeguroPK != null && !this.crdCreditoSeguroPK.equals(other.crdCreditoSeguroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCreditoSeguro[ crdCreditoSeguroPK=" + crdCreditoSeguroPK + " ]";
    }
    
}
