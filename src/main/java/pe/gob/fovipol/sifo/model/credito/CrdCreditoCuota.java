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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.listener.AuditListener;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "CRD_CREDITO_CUOTA")
@EntityListeners(value = AuditListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdCreditoCuota.findAll", query = "SELECT c FROM CrdCreditoCuota c"),
    @NamedQuery(name = "CrdCreditoCuota.findByIdenCredCrd", query = "SELECT c FROM CrdCreditoCuota c WHERE c.crdCreditoCuotaPK.idenCredCrd = :idenCredCrd"),
    @NamedQuery(name = "CrdCreditoCuota.findBySecuCuotCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.crdCreditoCuotaPK.secuCuotCuo = :secuCuotCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByNumeCuotCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.numeCuotCuo = :numeCuotCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findBySaldInicCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.saldInicCuo = :saldInicCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByCapiMontCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.capiMontCuo = :capiMontCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByImpoSeguCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.impoSeguCuo = :impoSeguCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByInteMontCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.inteMontCuo = :inteMontCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByTasaPteaCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.tasaPteaCuo = :tasaPteaCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByImpoCuotCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.impoCuotCuo = :impoCuotCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByFechPagoCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.fechPagoCuo = :fechPagoCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByFechUddpCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.fechUddpCuo = :fechUddpCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByCodiSituCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.codiSituCuo = :codiSituCuo"),
    @NamedQuery(name = "CrdCreditoCuota.findByUsuaCreaAud", query = "SELECT c FROM CrdCreditoCuota c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdCreditoCuota.findByFechCreaAud", query = "SELECT c FROM CrdCreditoCuota c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdCreditoCuota.findByUsuaModiAud", query = "SELECT c FROM CrdCreditoCuota c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdCreditoCuota.findByFechModiAud", query = "SELECT c FROM CrdCreditoCuota c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdCreditoCuota.findByNombEquiAud", query = "SELECT c FROM CrdCreditoCuota c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdCreditoCuota.findByNombSopeAud", query = "SELECT c FROM CrdCreditoCuota c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdCreditoCuota.findByFlagEstaCuo", query = "SELECT c FROM CrdCreditoCuota c WHERE c.flagEstaCuo = :flagEstaCuo")})
public class CrdCreditoCuota implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crdCreditoCuota")
    private List<CrdCuotaSeguro> crdCuotaSeguroList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrdCreditoCuotaPK crdCreditoCuotaPK;
    @Size(max = 18)
    @Column(name = "NUME_CUOT_CUO")
    private String numeCuotCuo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALD_INIC_CUO")
    private BigDecimal saldInicCuo;
    @Column(name = "CAPI_MONT_CUO")
    private BigDecimal capiMontCuo;
    @Column(name = "IMPO_SEGU_CUO")
    private BigDecimal impoSeguCuo;
    @Column(name = "INTE_MONT_CUO")
    private BigDecimal inteMontCuo;
    @Column(name = "TASA_PTEA_CUO")
    private BigDecimal tasaPteaCuo;
    @Column(name = "IMPO_CUOT_CUO")
    private BigDecimal impoCuotCuo;
    @Column(name = "FECH_PAGO_CUO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechPagoCuo;
    @Column(name = "FECH_UDDP_CUO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechUddpCuo;
    @Column(name = "CODI_SITU_CUO")
    private Integer codiSituCuo;
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
    @Column(name = "FLAG_ESTA_CUO")
    private Short flagEstaCuo;
    @JoinColumn(name = "IDEN_TCAM_TCM", referencedColumnName = "IDEN_TCAM_TCM")
    @ManyToOne
    private MaeTipoCambio idenTcamTcm;
    @JoinColumn(name = "IDEN_CRED_CRD", referencedColumnName = "IDEN_CRED_CRD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CrdCredito crdCredito;

    public CrdCreditoCuota() {
    }

    public CrdCreditoCuota(CrdCreditoCuotaPK crdCreditoCuotaPK) {
        this.crdCreditoCuotaPK = crdCreditoCuotaPK;
    }

    public CrdCreditoCuota(BigInteger idenCredCrd, int secuCuotCuo) {
        this.crdCreditoCuotaPK = new CrdCreditoCuotaPK(idenCredCrd, secuCuotCuo);
    }

    public CrdCreditoCuotaPK getCrdCreditoCuotaPK() {
        return crdCreditoCuotaPK;
    }

    public void setCrdCreditoCuotaPK(CrdCreditoCuotaPK crdCreditoCuotaPK) {
        this.crdCreditoCuotaPK = crdCreditoCuotaPK;
    }

    public String getNumeCuotCuo() {
        return numeCuotCuo;
    }

    public void setNumeCuotCuo(String numeCuotCuo) {
        this.numeCuotCuo = numeCuotCuo;
    }

    public BigDecimal getSaldInicCuo() {
        return saldInicCuo;
    }

    public void setSaldInicCuo(BigDecimal saldInicCuo) {
        this.saldInicCuo = saldInicCuo;
    }

    public BigDecimal getCapiMontCuo() {
        return capiMontCuo;
    }

    public void setCapiMontCuo(BigDecimal capiMontCuo) {
        this.capiMontCuo = capiMontCuo;
    }

    public BigDecimal getImpoSeguCuo() {
        return impoSeguCuo;
    }

    public void setImpoSeguCuo(BigDecimal impoSeguCuo) {
        this.impoSeguCuo = impoSeguCuo;
    }

    public BigDecimal getInteMontCuo() {
        return inteMontCuo;
    }

    public void setInteMontCuo(BigDecimal inteMontCuo) {
        this.inteMontCuo = inteMontCuo;
    }

    public BigDecimal getTasaPteaCuo() {
        return tasaPteaCuo;
    }

    public void setTasaPteaCuo(BigDecimal tasaPteaCuo) {
        this.tasaPteaCuo = tasaPteaCuo;
    }

    public BigDecimal getImpoCuotCuo() {
        return impoCuotCuo;
    }

    public void setImpoCuotCuo(BigDecimal impoCuotCuo) {
        this.impoCuotCuo = impoCuotCuo;
    }

    public Date getFechPagoCuo() {
        return fechPagoCuo;
    }

    public void setFechPagoCuo(Date fechPagoCuo) {
        this.fechPagoCuo = fechPagoCuo;
    }

    public Date getFechUddpCuo() {
        return fechUddpCuo;
    }

    public void setFechUddpCuo(Date fechUddpCuo) {
        this.fechUddpCuo = fechUddpCuo;
    }

    public Integer getCodiSituCuo() {
        return codiSituCuo;
    }

    public void setCodiSituCuo(Integer codiSituCuo) {
        this.codiSituCuo = codiSituCuo;
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

    public Short getFlagEstaCuo() {
        return flagEstaCuo;
    }

    public void setFlagEstaCuo(Short flagEstaCuo) {
        this.flagEstaCuo = flagEstaCuo;
    }

    public MaeTipoCambio getIdenTcamTcm() {
        return idenTcamTcm;
    }

    public void setIdenTcamTcm(MaeTipoCambio idenTcamTcm) {
        this.idenTcamTcm = idenTcamTcm;
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
        hash += (crdCreditoCuotaPK != null ? crdCreditoCuotaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCreditoCuota)) {
            return false;
        }
        CrdCreditoCuota other = (CrdCreditoCuota) object;
        if ((this.crdCreditoCuotaPK == null && other.crdCreditoCuotaPK != null) || (this.crdCreditoCuotaPK != null && !this.crdCreditoCuotaPK.equals(other.crdCreditoCuotaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCreditoCuota[ crdCreditoCuotaPK=" + crdCreditoCuotaPK + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdCuotaSeguro> getCrdCuotaSeguroList() {
        return crdCuotaSeguroList;
    }

    public void setCrdCuotaSeguroList(List<CrdCuotaSeguro> crdCuotaSeguroList) {
        this.crdCuotaSeguroList = crdCuotaSeguroList;
    }
    
}
