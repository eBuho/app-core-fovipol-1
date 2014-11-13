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
import javax.persistence.JoinColumns;
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
@Table(name = "CRD_CUOTA_SEGURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdCuotaSeguro.findAll", query = "SELECT c FROM CrdCuotaSeguro c"),
    @NamedQuery(name = "CrdCuotaSeguro.findByIdenCredCrd", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.crdCuotaSeguroPK.idenCredCrd = :idenCredCrd"),
    @NamedQuery(name = "CrdCuotaSeguro.findBySecuCuotCuo", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.crdCuotaSeguroPK.secuCuotCuo = :secuCuotCuo"),
    @NamedQuery(name = "CrdCuotaSeguro.findBySecuSeguCcs", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.crdCuotaSeguroPK.secuSeguCcs = :secuSeguCcs"),
    @NamedQuery(name = "CrdCuotaSeguro.findByTasaSeguCcs", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.tasaSeguCcs = :tasaSeguCcs"),
    @NamedQuery(name = "CrdCuotaSeguro.findByImpoSeguCcs", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.impoSeguCcs = :impoSeguCcs"),
    @NamedQuery(name = "CrdCuotaSeguro.findByUsuaCreaAud", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdCuotaSeguro.findByFechCreaAud", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdCuotaSeguro.findByUsuaModiAud", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdCuotaSeguro.findByFechModiAud", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdCuotaSeguro.findByNombEquiAud", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdCuotaSeguro.findByNombSopeAud", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdCuotaSeguro.findByFlagEstaCcs", query = "SELECT c FROM CrdCuotaSeguro c WHERE c.flagEstaCcs = :flagEstaCcs")})
public class CrdCuotaSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrdCuotaSeguroPK crdCuotaSeguroPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TASA_SEGU_CCS")
    private BigDecimal tasaSeguCcs;
    @Column(name = "IMPO_SEGU_CCS")
    private BigDecimal impoSeguCcs;
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
    @Column(name = "FLAG_ESTA_CCS")
    private Short flagEstaCcs;
    @JoinColumn(name = "IDEN_SEGU_SEG", referencedColumnName = "IDEN_SEGU_SEG")
    @ManyToOne
    private MaeSeguro idenSeguSeg;
    @JoinColumns({
        @JoinColumn(name = "IDEN_CRED_CRD", referencedColumnName = "IDEN_CRED_CRD", insertable = false, updatable = false),
        @JoinColumn(name = "SECU_CUOT_CUO", referencedColumnName = "SECU_CUOT_CUO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CrdCreditoCuota crdCreditoCuota;

    public CrdCuotaSeguro() {
    }

    public CrdCuotaSeguro(CrdCuotaSeguroPK crdCuotaSeguroPK) {
        this.crdCuotaSeguroPK = crdCuotaSeguroPK;
    }

    public CrdCuotaSeguro(BigInteger idenCredCrd, int secuCuotCuo, int secuSeguCcs) {
        this.crdCuotaSeguroPK = new CrdCuotaSeguroPK(idenCredCrd, secuCuotCuo, secuSeguCcs);
    }

    public CrdCuotaSeguroPK getCrdCuotaSeguroPK() {
        return crdCuotaSeguroPK;
    }

    public void setCrdCuotaSeguroPK(CrdCuotaSeguroPK crdCuotaSeguroPK) {
        this.crdCuotaSeguroPK = crdCuotaSeguroPK;
    }

    public BigDecimal getTasaSeguCcs() {
        return tasaSeguCcs;
    }

    public void setTasaSeguCcs(BigDecimal tasaSeguCcs) {
        this.tasaSeguCcs = tasaSeguCcs;
    }

    public BigDecimal getImpoSeguCcs() {
        return impoSeguCcs;
    }

    public void setImpoSeguCcs(BigDecimal impoSeguCcs) {
        this.impoSeguCcs = impoSeguCcs;
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

    public Short getFlagEstaCcs() {
        return flagEstaCcs;
    }

    public void setFlagEstaCcs(Short flagEstaCcs) {
        this.flagEstaCcs = flagEstaCcs;
    }

    public MaeSeguro getIdenSeguSeg() {
        return idenSeguSeg;
    }

    public void setIdenSeguSeg(MaeSeguro idenSeguSeg) {
        this.idenSeguSeg = idenSeguSeg;
    }

    public CrdCreditoCuota getCrdCreditoCuota() {
        return crdCreditoCuota;
    }

    public void setCrdCreditoCuota(CrdCreditoCuota crdCreditoCuota) {
        this.crdCreditoCuota = crdCreditoCuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crdCuotaSeguroPK != null ? crdCuotaSeguroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCuotaSeguro)) {
            return false;
        }
        CrdCuotaSeguro other = (CrdCuotaSeguro) object;
        if ((this.crdCuotaSeguroPK == null && other.crdCuotaSeguroPK != null) || (this.crdCuotaSeguroPK != null && !this.crdCuotaSeguroPK.equals(other.crdCuotaSeguroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCuotaSeguro[ crdCuotaSeguroPK=" + crdCuotaSeguroPK + " ]";
    }
    
}
