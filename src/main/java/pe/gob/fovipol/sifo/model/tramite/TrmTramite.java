/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.tramite;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;
import pe.gob.fovipol.sifo.model.simulacion.CrdSimulacion;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "TRM_TRAMITE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrmTramite.findAll", query = "SELECT t FROM TrmTramite t"),
    @NamedQuery(name = "TrmTramite.findByIdenExpeTrm", query = "SELECT t FROM TrmTramite t WHERE t.idenExpeTrm = :idenExpeTrm"),
    @NamedQuery(name = "TrmTramite.findByNumeGuiaTrm", query = "SELECT t FROM TrmTramite t WHERE t.numeGuiaTrm = :numeGuiaTrm"),
    @NamedQuery(name = "TrmTramite.findByTipoTramTrm", query = "SELECT t FROM TrmTramite t WHERE t.tipoTramTrm = :tipoTramTrm"),
    @NamedQuery(name = "TrmTramite.findByCodiOrigTrm", query = "SELECT t FROM TrmTramite t WHERE t.codiOrigTrm = :codiOrigTrm"),
    @NamedQuery(name = "TrmTramite.findByCodiModaTrm", query = "SELECT t FROM TrmTramite t WHERE t.codiModaTrm = :codiModaTrm"),
    @NamedQuery(name = "TrmTramite.findByCodiExpeTrm", query = "SELECT t FROM TrmTramite t WHERE t.codiExpeTrm = :codiExpeTrm"),
    @NamedQuery(name = "TrmTramite.findByCodiBarrTrm", query = "SELECT t FROM TrmTramite t WHERE t.codiBarrTrm = :codiBarrTrm"),
    @NamedQuery(name = "TrmTramite.findByNombTramTrm", query = "SELECT t FROM TrmTramite t WHERE t.nombTramTrm = :nombTramTrm"),
    @NamedQuery(name = "TrmTramite.findByNumeFolioTrm", query = "SELECT t FROM TrmTramite t WHERE t.numeFolioTrm = :numeFolioTrm"),
    @NamedQuery(name = "TrmTramite.findByDescAsunTrm", query = "SELECT t FROM TrmTramite t WHERE t.descAsunTrm = :descAsunTrm"),
    @NamedQuery(name = "TrmTramite.findByFechIngrTrm", query = "SELECT t FROM TrmTramite t WHERE t.fechIngrTrm = :fechIngrTrm"),
    @NamedQuery(name = "TrmTramite.findByNumeDiasTrm", query = "SELECT t FROM TrmTramite t WHERE t.numeDiasTrm = :numeDiasTrm"),
    @NamedQuery(name = "TrmTramite.findByFechVencTrm", query = "SELECT t FROM TrmTramite t WHERE t.fechVencTrm = :fechVencTrm"),
    @NamedQuery(name = "TrmTramite.findByCodiPrioTrm", query = "SELECT t FROM TrmTramite t WHERE t.codiPrioTrm = :codiPrioTrm"),
    @NamedQuery(name = "TrmTramite.findByFlagPrivTrm", query = "SELECT t FROM TrmTramite t WHERE t.flagPrivTrm = :flagPrivTrm"),
    @NamedQuery(name = "TrmTramite.findByUsuaCreaAud", query = "SELECT t FROM TrmTramite t WHERE t.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "TrmTramite.findByFechCreaAud", query = "SELECT t FROM TrmTramite t WHERE t.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "TrmTramite.findByUsuaModiAud", query = "SELECT t FROM TrmTramite t WHERE t.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "TrmTramite.findByFechModiAud", query = "SELECT t FROM TrmTramite t WHERE t.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "TrmTramite.findByNombEquiAud", query = "SELECT t FROM TrmTramite t WHERE t.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "TrmTramite.findByNombSopeAud", query = "SELECT t FROM TrmTramite t WHERE t.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "TrmTramite.findByFlagEstaTrm", query = "SELECT t FROM TrmTramite t WHERE t.flagEstaTrm = :flagEstaTrm")})
public class TrmTramite implements Serializable {
    @JoinColumn(name = "CODI_PERS_TRM", referencedColumnName = "IDEN_PERS_PER")
    @ManyToOne
    private MaePersona codiPersTrm;
    @JoinColumn(name = "IDEN_SIMU_SIM", referencedColumnName = "IDEN_SIMU_SIM")
    @ManyToOne
    private CrdSimulacion idenSimuSim;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_EXPE_TRM")
    private BigDecimal idenExpeTrm;
    @Size(max = 20)
    @Column(name = "NUME_GUIA_TRM")
    private String numeGuiaTrm;
    @Column(name = "TIPO_TRAM_TRM")
    private Integer tipoTramTrm;
    @Column(name = "CODI_ORIG_TRM")
    private Integer codiOrigTrm;
    @Column(name = "CODI_MODA_TRM")
    private Integer codiModaTrm;
    @Size(max = 20)
    @Column(name = "CODI_EXPE_TRM")
    private String codiExpeTrm;
    @Size(max = 20)
    @Column(name = "CODI_BARR_TRM")
    private String codiBarrTrm;
    @Size(max = 120)
    @Column(name = "NOMB_TRAM_TRM")
    private String nombTramTrm;
    @Column(name = "NUME_FOLIO_TRM")
    private BigInteger numeFolioTrm;
    @Size(max = 300)
    @Column(name = "DESC_ASUN_TRM")
    private String descAsunTrm;
    @Column(name = "FECH_INGR_TRM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechIngrTrm;
    @Column(name = "NUME_DIAS_TRM")
    private BigInteger numeDiasTrm;
    @Column(name = "FECH_VENC_TRM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechVencTrm;
    @Column(name = "CODI_PRIO_TRM")
    private Integer codiPrioTrm;
    @Column(name = "FLAG_PRIV_TRM")
    private Short flagPrivTrm;
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
    @Column(name = "FLAG_ESTA_TRM")
    private Short flagEstaTrm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trmTramite")
    private List<TrmDocumento> trmDocumentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trmTramite")
    private List<TrmMovimiento> trmMovimientoList;
    @OneToMany(mappedBy = "docuRefeTrm")
    private List<TrmTramite> trmTramiteList;
    @JoinColumn(name = "DOCU_REFE_TRM", referencedColumnName = "IDEN_EXPE_TRM")
    @ManyToOne
    private TrmTramite docuRefeTrm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trmTramite")
    private List<TrmEstatramHis> trmEstatramHisList;
    @JoinColumn(name = "IDEN_PROC_PRC", referencedColumnName = "IDEN_PROC_PRC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MaeProceso maeProceso;

    public TrmTramite() {
    }

    public TrmTramite(BigDecimal idenExpeTrm) {
        this.idenExpeTrm = idenExpeTrm;
    }

    public BigDecimal getIdenExpeTrm() {
        return idenExpeTrm;
    }

    public void setIdenExpeTrm(BigDecimal idenExpeTrm) {
        this.idenExpeTrm = idenExpeTrm;
    }

    public String getNumeGuiaTrm() {
        return numeGuiaTrm;
    }

    public void setNumeGuiaTrm(String numeGuiaTrm) {
        this.numeGuiaTrm = numeGuiaTrm;
    }

    public Integer getTipoTramTrm() {
        return tipoTramTrm;
    }

    public void setTipoTramTrm(Integer tipoTramTrm) {
        this.tipoTramTrm = tipoTramTrm;
    }

    public Integer getCodiOrigTrm() {
        return codiOrigTrm;
    }

    public void setCodiOrigTrm(Integer codiOrigTrm) {
        this.codiOrigTrm = codiOrigTrm;
    }

    public Integer getCodiModaTrm() {
        return codiModaTrm;
    }

    public void setCodiModaTrm(Integer codiModaTrm) {
        this.codiModaTrm = codiModaTrm;
    }

    public String getCodiExpeTrm() {
        return codiExpeTrm;
    }

    public void setCodiExpeTrm(String codiExpeTrm) {
        this.codiExpeTrm = codiExpeTrm;
    }

    public String getCodiBarrTrm() {
        return codiBarrTrm;
    }

    public void setCodiBarrTrm(String codiBarrTrm) {
        this.codiBarrTrm = codiBarrTrm;
    }

    public String getNombTramTrm() {
        return nombTramTrm;
    }

    public void setNombTramTrm(String nombTramTrm) {
        this.nombTramTrm = nombTramTrm;
    }

    public BigInteger getNumeFolioTrm() {
        return numeFolioTrm;
    }

    public void setNumeFolioTrm(BigInteger numeFolioTrm) {
        this.numeFolioTrm = numeFolioTrm;
    }

    public String getDescAsunTrm() {
        return descAsunTrm;
    }

    public void setDescAsunTrm(String descAsunTrm) {
        this.descAsunTrm = descAsunTrm;
    }

    public Date getFechIngrTrm() {
        return fechIngrTrm;
    }

    public void setFechIngrTrm(Date fechIngrTrm) {
        this.fechIngrTrm = fechIngrTrm;
    }

    public BigInteger getNumeDiasTrm() {
        return numeDiasTrm;
    }

    public void setNumeDiasTrm(BigInteger numeDiasTrm) {
        this.numeDiasTrm = numeDiasTrm;
    }

    public Date getFechVencTrm() {
        return fechVencTrm;
    }

    public void setFechVencTrm(Date fechVencTrm) {
        this.fechVencTrm = fechVencTrm;
    }

    public Integer getCodiPrioTrm() {
        return codiPrioTrm;
    }

    public void setCodiPrioTrm(Integer codiPrioTrm) {
        this.codiPrioTrm = codiPrioTrm;
    }

    public Short getFlagPrivTrm() {
        return flagPrivTrm;
    }

    public void setFlagPrivTrm(Short flagPrivTrm) {
        this.flagPrivTrm = flagPrivTrm;
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

    public Short getFlagEstaTrm() {
        return flagEstaTrm;
    }

    public void setFlagEstaTrm(Short flagEstaTrm) {
        this.flagEstaTrm = flagEstaTrm;
    }

    @XmlTransient
    public List<TrmDocumento> getTrmDocumentoList() {
        return trmDocumentoList;
    }

    public void setTrmDocumentoList(List<TrmDocumento> trmDocumentoList) {
        this.trmDocumentoList = trmDocumentoList;
    }

    @XmlTransient
    public List<TrmMovimiento> getTrmMovimientoList() {
        return trmMovimientoList;
    }

    public void setTrmMovimientoList(List<TrmMovimiento> trmMovimientoList) {
        this.trmMovimientoList = trmMovimientoList;
    }

    @XmlTransient
    public List<TrmTramite> getTrmTramiteList() {
        return trmTramiteList;
    }

    public void setTrmTramiteList(List<TrmTramite> trmTramiteList) {
        this.trmTramiteList = trmTramiteList;
    }

    public TrmTramite getDocuRefeTrm() {
        return docuRefeTrm;
    }

    public void setDocuRefeTrm(TrmTramite docuRefeTrm) {
        this.docuRefeTrm = docuRefeTrm;
    }

    @XmlTransient
    public List<TrmEstatramHis> getTrmEstatramHisList() {
        return trmEstatramHisList;
    }

    public void setTrmEstatramHisList(List<TrmEstatramHis> trmEstatramHisList) {
        this.trmEstatramHisList = trmEstatramHisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenExpeTrm != null ? idenExpeTrm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmTramite)) {
            return false;
        }
        TrmTramite other = (TrmTramite) object;
        if ((this.idenExpeTrm == null && other.idenExpeTrm != null) || (this.idenExpeTrm != null && !this.idenExpeTrm.equals(other.idenExpeTrm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmTramite[ idenExpeTrm=" + idenExpeTrm + " ]";
    }

    /**
     * @return the maeProceso
     */
    public MaeProceso getMaeProceso() {
        return maeProceso;
    }

    /**
     * @param maeProceso the maeProceso to set
     */
    public void setMaeProceso(MaeProceso maeProceso) {
        this.maeProceso = maeProceso;
    }

    public MaePersona getCodiPersTrm() {
        return codiPersTrm;
    }

    public void setCodiPersTrm(MaePersona codiPersTrm) {
        this.codiPersTrm = codiPersTrm;
    }

    public CrdSimulacion getIdenSimuSim() {
        return idenSimuSim;
    }

    public void setIdenSimuSim(CrdSimulacion idenSimuSim) {
        this.idenSimuSim = idenSimuSim;
    }
    
}
