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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeInmueble;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "CRD_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdCredito.findAll", query = "SELECT c FROM CrdCredito c"),
    @NamedQuery(name = "CrdCredito.findByIdenCredCrd", query = "SELECT c FROM CrdCredito c WHERE c.idenCredCrd = :idenCredCrd"),
    @NamedQuery(name = "CrdCredito.findByCodiCredCrd", query = "SELECT c FROM CrdCredito c WHERE c.codiCredCrd = :codiCredCrd"),
    @NamedQuery(name = "CrdCredito.findByCodiFinaCrd", query = "SELECT c FROM CrdCredito c WHERE c.codiFinaCrd = :codiFinaCrd"),
    @NamedQuery(name = "CrdCredito.findByPercSociCrd", query = "SELECT c FROM CrdCredito c WHERE c.percSociCrd = :percSociCrd"),
    @NamedQuery(name = "CrdCredito.findByImpoSoliCrd", query = "SELECT c FROM CrdCredito c WHERE c.impoSoliCrd = :impoSoliCrd"),
    @NamedQuery(name = "CrdCredito.findByNumeCuotPrd", query = "SELECT c FROM CrdCredito c WHERE c.numeCuotPrd = :numeCuotPrd"),
    @NamedQuery(name = "CrdCredito.findByTasaInteCrd", query = "SELECT c FROM CrdCredito c WHERE c.tasaInteCrd = :tasaInteCrd"),
    @NamedQuery(name = "CrdCredito.findByImpoInteCrd", query = "SELECT c FROM CrdCredito c WHERE c.impoInteCrd = :impoInteCrd"),
    @NamedQuery(name = "CrdCredito.findByTasaGadmCrd", query = "SELECT c FROM CrdCredito c WHERE c.tasaGadmCrd = :tasaGadmCrd"),
    @NamedQuery(name = "CrdCredito.findByImpoGadmCrd", query = "SELECT c FROM CrdCredito c WHERE c.impoGadmCrd = :impoGadmCrd"),
    @NamedQuery(name = "CrdCredito.findByFechAproCrd", query = "SELECT c FROM CrdCredito c WHERE c.fechAproCrd = :fechAproCrd"),
    @NamedQuery(name = "CrdCredito.findByImpoGiraCrd", query = "SELECT c FROM CrdCredito c WHERE c.impoGiraCrd = :impoGiraCrd"),
    @NamedQuery(name = "CrdCredito.findByCodiMoneCrd", query = "SELECT c FROM CrdCredito c WHERE c.codiMoneCrd = :codiMoneCrd"),
    @NamedQuery(name = "CrdCredito.findByAutoCdobCrd", query = "SELECT c FROM CrdCredito c WHERE c.autoCdobCrd = :autoCdobCrd"),
    @NamedQuery(name = "CrdCredito.findByPeriGracCrd", query = "SELECT c FROM CrdCredito c WHERE c.periGracCrd = :periGracCrd"),
    @NamedQuery(name = "CrdCredito.findByFechCreaAud", query = "SELECT c FROM CrdCredito c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdCredito.findByUsuaCreaAud", query = "SELECT c FROM CrdCredito c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdCredito.findByFechModiAud", query = "SELECT c FROM CrdCredito c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdCredito.findByUsuaModiAud", query = "SELECT c FROM CrdCredito c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdCredito.findByNombEquiAud", query = "SELECT c FROM CrdCredito c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdCredito.findByNombSopeAud", query = "SELECT c FROM CrdCredito c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdCredito.findByFlagEstaCrd", query = "SELECT c FROM CrdCredito c WHERE c.flagEstaCrd = :flagEstaCrd")})
public class CrdCredito implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crdCredito")
    private List<CrdCreditoCuota> crdCreditoCuotaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crdCredito")
    private List<CrdEstacredHis> crdEstacredHisList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crdCredito")
    private List<CrdCreditoSeguro> crdCreditoSeguroList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_CRED_CRD")
    private BigDecimal idenCredCrd;
    @Size(max = 20)
    @Column(name = "CODI_CRED_CRD")
    private String codiCredCrd;
    @Column(name = "CODI_FINA_CRD")
    private Integer codiFinaCrd;
    @Column(name = "PERC_SOCI_CRD")
    private BigDecimal percSociCrd;
    @Column(name = "IMPO_SOLI_CRD")
    private BigDecimal impoSoliCrd;
    @Column(name = "NUME_CUOT_PRD")
    private BigInteger numeCuotPrd;
    @Column(name = "TASA_INTE_CRD")
    private BigDecimal tasaInteCrd;
    @Column(name = "IMPO_INTE_CRD")
    private BigDecimal impoInteCrd;
    @Column(name = "TASA_GADM_CRD")
    private BigDecimal tasaGadmCrd;
    @Column(name = "IMPO_GADM_CRD")
    private BigDecimal impoGadmCrd;
    @Column(name = "FECH_APRO_CRD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechAproCrd;
    @Column(name = "IMPO_GIRA_CRD")
    private BigDecimal impoGiraCrd;
    @Column(name = "CODI_MONE_CRD")
    private Integer codiMoneCrd;
    @Column(name = "AUTO_CDOB_CRD")
    private Character autoCdobCrd;
    @Column(name = "PERI_GRAC_CRD")
    private BigInteger periGracCrd;
    @Column(name = "FECH_CREA_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaAud;
    @Size(max = 15)
    @Column(name = "USUA_CREA_AUD")
    private String usuaCreaAud;
    @Column(name = "FECH_MODI_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechModiAud;
    @Size(max = 15)
    @Column(name = "USUA_MODI_AUD")
    private String usuaModiAud;
    @Size(max = 40)
    @Column(name = "NOMB_EQUI_AUD")
    private String nombEquiAud;
    @Size(max = 40)
    @Column(name = "NOMB_SOPE_AUD")
    private String nombSopeAud;
    @Column(name = "FLAG_ESTA_CRD")
    private Short flagEstaCrd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crdCredito")
    private List<CrdCanalcobra> crdCanalcobraList;
    @JoinColumn(name = "IDEN_EXPE_TRM", referencedColumnName = "IDEN_EXPE_TRM")
    @ManyToOne(optional = false)
    private TrmTramite idenExpeTrm;
    @JoinColumn(name = "IDEN_INMU_IMB", referencedColumnName = "IDEN_INMU_IMB")
    @ManyToOne
    private MaeInmueble idenInmuImb;
    @Transient
    private MaeEntidaddet moneda;

    public CrdCredito() {
    }

    public CrdCredito(BigDecimal idenCredCrd) {
        this.idenCredCrd = idenCredCrd;
    }

    public BigDecimal getIdenCredCrd() {
        return idenCredCrd;
    }

    public void setIdenCredCrd(BigDecimal idenCredCrd) {
        this.idenCredCrd = idenCredCrd;
    }

    public String getCodiCredCrd() {
        return codiCredCrd;
    }

    public void setCodiCredCrd(String codiCredCrd) {
        this.codiCredCrd = codiCredCrd;
    }

    public Integer getCodiFinaCrd() {
        return codiFinaCrd;
    }

    public void setCodiFinaCrd(Integer codiFinaCrd) {
        this.codiFinaCrd = codiFinaCrd;
    }

    public BigDecimal getPercSociCrd() {
        return percSociCrd;
    }

    public void setPercSociCrd(BigDecimal percSociCrd) {
        this.percSociCrd = percSociCrd;
    }

    public BigDecimal getImpoSoliCrd() {
        return impoSoliCrd;
    }

    public void setImpoSoliCrd(BigDecimal impoSoliCrd) {
        this.impoSoliCrd = impoSoliCrd;
    }

    public BigInteger getNumeCuotPrd() {
        return numeCuotPrd;
    }

    public void setNumeCuotPrd(BigInteger numeCuotPrd) {
        this.numeCuotPrd = numeCuotPrd;
    }

    public BigDecimal getTasaInteCrd() {
        return tasaInteCrd;
    }

    public void setTasaInteCrd(BigDecimal tasaInteCrd) {
        this.tasaInteCrd = tasaInteCrd;
    }

    public BigDecimal getImpoInteCrd() {
        return impoInteCrd;
    }

    public void setImpoInteCrd(BigDecimal impoInteCrd) {
        this.impoInteCrd = impoInteCrd;
    }

    public BigDecimal getTasaGadmCrd() {
        return tasaGadmCrd;
    }

    public void setTasaGadmCrd(BigDecimal tasaGadmCrd) {
        this.tasaGadmCrd = tasaGadmCrd;
    }

    public BigDecimal getImpoGadmCrd() {
        return impoGadmCrd;
    }

    public void setImpoGadmCrd(BigDecimal impoGadmCrd) {
        this.impoGadmCrd = impoGadmCrd;
    }

    public Date getFechAproCrd() {
        return fechAproCrd;
    }

    public void setFechAproCrd(Date fechAproCrd) {
        this.fechAproCrd = fechAproCrd;
    }

    public BigDecimal getImpoGiraCrd() {
        return impoGiraCrd;
    }

    public void setImpoGiraCrd(BigDecimal impoGiraCrd) {
        this.impoGiraCrd = impoGiraCrd;
    }

    public Integer getCodiMoneCrd() {
        return codiMoneCrd;
    }

    public void setCodiMoneCrd(Integer codiMoneCrd) {
        this.codiMoneCrd = codiMoneCrd;
    }

    public Character getAutoCdobCrd() {
        return autoCdobCrd;
    }

    public void setAutoCdobCrd(Character autoCdobCrd) {
        this.autoCdobCrd = autoCdobCrd;
    }

    public BigInteger getPeriGracCrd() {
        return periGracCrd;
    }

    public void setPeriGracCrd(BigInteger periGracCrd) {
        this.periGracCrd = periGracCrd;
    }

    public Date getFechCreaAud() {
        return fechCreaAud;
    }

    public void setFechCreaAud(Date fechCreaAud) {
        this.fechCreaAud = fechCreaAud;
    }

    public String getUsuaCreaAud() {
        return usuaCreaAud;
    }

    public void setUsuaCreaAud(String usuaCreaAud) {
        this.usuaCreaAud = usuaCreaAud;
    }

    public Date getFechModiAud() {
        return fechModiAud;
    }

    public void setFechModiAud(Date fechModiAud) {
        this.fechModiAud = fechModiAud;
    }

    public String getUsuaModiAud() {
        return usuaModiAud;
    }

    public void setUsuaModiAud(String usuaModiAud) {
        this.usuaModiAud = usuaModiAud;
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

    public Short getFlagEstaCrd() {
        return flagEstaCrd;
    }

    public void setFlagEstaCrd(Short flagEstaCrd) {
        this.flagEstaCrd = flagEstaCrd;
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdCanalcobra> getCrdCanalcobraList() {
        return crdCanalcobraList;
    }

    public void setCrdCanalcobraList(List<CrdCanalcobra> crdCanalcobraList) {
        this.crdCanalcobraList = crdCanalcobraList;
    }

    public TrmTramite getIdenExpeTrm() {
        return idenExpeTrm;
    }

    public void setIdenExpeTrm(TrmTramite idenExpeTrm) {
        this.idenExpeTrm = idenExpeTrm;
    }

    public MaeInmueble getIdenInmuImb() {
        return idenInmuImb;
    }

    public void setIdenInmuImb(MaeInmueble idenInmuImb) {
        this.idenInmuImb = idenInmuImb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenCredCrd != null ? idenCredCrd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCredito)) {
            return false;
        }
        CrdCredito other = (CrdCredito) object;
        if ((this.idenCredCrd == null && other.idenCredCrd != null) || (this.idenCredCrd != null && !this.idenCredCrd.equals(other.idenCredCrd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCredito[ idenCredCrd=" + idenCredCrd + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdCreditoCuota> getCrdCreditoCuotaList() {
        return crdCreditoCuotaList;
    }

    public void setCrdCreditoCuotaList(List<CrdCreditoCuota> crdCreditoCuotaList) {
        this.crdCreditoCuotaList = crdCreditoCuotaList;
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdEstacredHis> getCrdEstacredHisList() {
        return crdEstacredHisList;
    }

    public void setCrdEstacredHisList(List<CrdEstacredHis> crdEstacredHisList) {
        this.crdEstacredHisList = crdEstacredHisList;
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdCreditoSeguro> getCrdCreditoSeguroList() {
        return crdCreditoSeguroList;
    }

    public void setCrdCreditoSeguroList(List<CrdCreditoSeguro> crdCreditoSeguroList) {
        this.crdCreditoSeguroList = crdCreditoSeguroList;
    }

    /**
     * @return the moneda
     */
    public MaeEntidaddet getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(MaeEntidaddet moneda) {
        this.moneda = moneda;
    }
    
}
