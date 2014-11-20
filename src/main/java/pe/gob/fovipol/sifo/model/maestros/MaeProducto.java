/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeProducto.findAll", query = "SELECT m FROM MaeProducto m"),
    @NamedQuery(name = "MaeProducto.findByIdenProdPrd", query = "SELECT m FROM MaeProducto m WHERE m.idenProdPrd = :idenProdPrd"),
    @NamedQuery(name = "MaeProducto.findByCodiLinePrd", query = "SELECT m FROM MaeProducto m WHERE m.codiLinePrd = :codiLinePrd"),
    @NamedQuery(name = "MaeProducto.findByNombProdPrd", query = "SELECT m FROM MaeProducto m WHERE m.nombProdPrd = :nombProdPrd"),
    @NamedQuery(name = "MaeProducto.findByCantVecePrd", query = "SELECT m FROM MaeProducto m WHERE m.cantVecePrd = :cantVecePrd"),
    @NamedQuery(name = "MaeProducto.findByMaxiPeriPrd", query = "SELECT m FROM MaeProducto m WHERE m.maxiPeriPrd = :maxiPeriPrd"),
    @NamedQuery(name = "MaeProducto.findByFechIvigPrd", query = "SELECT m FROM MaeProducto m WHERE m.fechIvigPrd = :fechIvigPrd"),
    @NamedQuery(name = "MaeProducto.findByFechFvigPrd", query = "SELECT m FROM MaeProducto m WHERE m.fechFvigPrd = :fechFvigPrd"),
    @NamedQuery(name = "MaeProducto.findByTasaIntePrd", query = "SELECT m FROM MaeProducto m WHERE m.tasaIntePrd = :tasaIntePrd"),
    @NamedQuery(name = "MaeProducto.findByTasaGadmPrd", query = "SELECT m FROM MaeProducto m WHERE m.tasaGadmPrd = :tasaGadmPrd"),
    @NamedQuery(name = "MaeProducto.findByMontDeudPrd", query = "SELECT m FROM MaeProducto m WHERE m.montDeudPrd = :montDeudPrd"),
    @NamedQuery(name = "MaeProducto.findByAutoCdobPrd", query = "SELECT m FROM MaeProducto m WHERE m.autoCdobPrd = :autoCdobPrd"),
    @NamedQuery(name = "MaeProducto.findByDescRequPrd", query = "SELECT m FROM MaeProducto m WHERE m.descRequPrd = :descRequPrd"),
    @NamedQuery(name = "MaeProducto.findByFormCalcPrd", query = "SELECT m FROM MaeProducto m WHERE m.formCalcPrd = :formCalcPrd"),
    @NamedQuery(name = "MaeProducto.findByUsuaCreaAud", query = "SELECT m FROM MaeProducto m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeProducto.findByFechCreaAud", query = "SELECT m FROM MaeProducto m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeProducto.findByUsuaModiAud", query = "SELECT m FROM MaeProducto m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeProducto.findByFechModAud", query = "SELECT m FROM MaeProducto m WHERE m.fechModAud = :fechModAud"),
    @NamedQuery(name = "MaeProducto.findByNombEquiAud", query = "SELECT m FROM MaeProducto m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeProducto.findByNombSopeAud", query = "SELECT m FROM MaeProducto m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeProducto.findByFlagEstaPrd", query = "SELECT m FROM MaeProducto m WHERE m.flagEstaPrd = :flagEstaPrd")})
public class MaeProducto implements Serializable {
    @Column(name = "DIAS_VALI_PRD")
    private Integer diasValiPrd;
    @Column(name = "TASA_PREM_PRD")
    private BigDecimal tasaPremPrd;
    @Column(name = "TASA_CAST_PRD")
    private BigDecimal tasaCastPrd;
    @Column(name = "AUTO_CDOB_PRD")
    private Short autoCdobPrd;
    @Column(name = "MONT_COBD_PRD")
    private BigDecimal montCobdPrd;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PROD_PRD")
    private BigDecimal idenProdPrd;
    @Column(name = "CODI_LINE_PRD")
    private Integer codiLinePrd;
    @Size(max = 120)
    @Column(name = "NOMB_PROD_PRD")
    private String nombProdPrd;
    @Column(name = "CANT_VECE_PRD")
    private BigInteger cantVecePrd;
    @Column(name = "MAXI_PERI_PRD")
    private BigInteger maxiPeriPrd;
    @Column(name = "FECH_IVIG_PRD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechIvigPrd;
    @Column(name = "FECH_FVIG_PRD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechFvigPrd;
    @Column(name = "TASA_INTE_PRD")
    private BigDecimal tasaIntePrd;
    @Column(name = "TASA_GADM_PRD")
    private BigDecimal tasaGadmPrd;
    @Column(name = "MONT_DEUD_PRD")
    private BigDecimal montDeudPrd;
    @Size(max = 2000)
    @Column(name = "DESC_REQU_PRD")
    private String descRequPrd;
    @Size(max = 120)
    @Column(name = "FORM_CALC_PRD")
    private String formCalcPrd;
    @Size(max = 15)
    @Column(name = "USUA_CREA_AUD")
    private String usuaCreaAud;
    @Column(name = "FECH_CREA_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaAud;
    @Size(max = 15)
    @Column(name = "USUA_MODI_AUD")
    private String usuaModiAud;
    @Column(name = "FECH_MOD_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechModAud;
    @Size(max = 40)
    @Column(name = "NOMB_EQUI_AUD")
    private String nombEquiAud;
    @Size(max = 40)
    @Column(name = "NOMB_SOPE_AUD")
    private String nombSopeAud;
    @Column(name = "FLAG_ESTA_PRD")
    private Short flagEstaPrd;
    @JoinColumn(name = "IDEN_PROC_PRC", referencedColumnName = "IDEN_PROC_PRC")
    @ManyToOne
    private MaeProceso idenProcPrc;
    @OneToMany(mappedBy = "idenProdPrd")
    private List<CrdSimulacion> crdSimulacionList;
    @Column(name = "CODI_MONE_CRD")
    private Integer codiMoneCrd;

    public MaeProducto() {
    }

    public MaeProducto(BigDecimal idenProdPrd) {
        this.idenProdPrd = idenProdPrd;
    }

    public MaeProducto(BigDecimal idenProdPrd, short autoCdobPrd) {
        this.idenProdPrd = idenProdPrd;
        this.autoCdobPrd = autoCdobPrd;
    }

    public BigDecimal getIdenProdPrd() {
        return idenProdPrd;
    }

    public void setIdenProdPrd(BigDecimal idenProdPrd) {
        this.idenProdPrd = idenProdPrd;
    }

    public Integer getCodiLinePrd() {
        return codiLinePrd;
    }

    public void setCodiLinePrd(Integer codiLinePrd) {
        this.codiLinePrd = codiLinePrd;
    }

    public String getNombProdPrd() {
        return nombProdPrd;
    }

    public void setNombProdPrd(String nombProdPrd) {
        this.nombProdPrd = nombProdPrd;
    }

    public BigInteger getCantVecePrd() {
        return cantVecePrd;
    }

    public void setCantVecePrd(BigInteger cantVecePrd) {
        this.cantVecePrd = cantVecePrd;
    }

    public BigInteger getMaxiPeriPrd() {
        return maxiPeriPrd;
    }

    public void setMaxiPeriPrd(BigInteger maxiPeriPrd) {
        this.maxiPeriPrd = maxiPeriPrd;
    }

    public Date getFechIvigPrd() {
        return fechIvigPrd;
    }

    public void setFechIvigPrd(Date fechIvigPrd) {
        this.fechIvigPrd = fechIvigPrd;
    }

    public Date getFechFvigPrd() {
        return fechFvigPrd;
    }

    public void setFechFvigPrd(Date fechFvigPrd) {
        this.fechFvigPrd = fechFvigPrd;
    }

    public BigDecimal getTasaIntePrd() {
        return tasaIntePrd;
    }

    public void setTasaIntePrd(BigDecimal tasaIntePrd) {
        this.tasaIntePrd = tasaIntePrd;
    }

    public BigDecimal getTasaGadmPrd() {
        return tasaGadmPrd;
    }

    public void setTasaGadmPrd(BigDecimal tasaGadmPrd) {
        this.tasaGadmPrd = tasaGadmPrd;
    }

    public BigDecimal getMontDeudPrd() {
        return montDeudPrd;
    }

    public void setMontDeudPrd(BigDecimal montDeudPrd) {
        this.montDeudPrd = montDeudPrd;
    }

    public String getDescRequPrd() {
        return descRequPrd;
    }

    public void setDescRequPrd(String descRequPrd) {
        this.descRequPrd = descRequPrd;
    }

    public String getFormCalcPrd() {
        return formCalcPrd;
    }

    public void setFormCalcPrd(String formCalcPrd) {
        this.formCalcPrd = formCalcPrd;
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

    public Date getFechModAud() {
        return fechModAud;
    }

    public void setFechModAud(Date fechModAud) {
        this.fechModAud = fechModAud;
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

    public Short getFlagEstaPrd() {
        return flagEstaPrd;
    }

    public void setFlagEstaPrd(Short flagEstaPrd) {
        this.flagEstaPrd = flagEstaPrd;
    }

    public MaeProceso getIdenProcPrc() {
        return idenProcPrc;
    }

    public void setIdenProcPrc(MaeProceso idenProcPrc) {
        this.idenProcPrc = idenProcPrc;
    }

    @XmlTransient
    public List<CrdSimulacion> getCrdSimulacionList() {
        return crdSimulacionList;
    }

    public void setCrdSimulacionList(List<CrdSimulacion> crdSimulacionList) {
        this.crdSimulacionList = crdSimulacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenProdPrd != null ? idenProdPrd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeProducto)) {
            return false;
        }
        MaeProducto other = (MaeProducto) object;
        if ((this.idenProdPrd == null && other.idenProdPrd != null) || (this.idenProdPrd != null && !this.idenProdPrd.equals(other.idenProdPrd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.consulta.MaeProducto[ idenProdPrd=" + idenProdPrd + " ]";
    }

    public BigDecimal getMontCobdPrd() {
        return montCobdPrd;
    }

    public void setMontCobdPrd(BigDecimal montCobdPrd) {
        this.montCobdPrd = montCobdPrd;
    }

    /**
     * @return the codiMoneCrd
     */
    public Integer getCodiMoneCrd() {
        return codiMoneCrd;
    }

    /**
     * @param codiMoneCrd the codiMoneCrd to set
     */
    public void setCodiMoneCrd(Integer codiMoneCrd) {
        this.codiMoneCrd = codiMoneCrd;
    }

    public Short getAutoCdobPrd() {
        return autoCdobPrd;
    }

    public void setAutoCdobPrd(Short autoCdobPrd) {
        this.autoCdobPrd = autoCdobPrd;
    }

    public Integer getDiasValiPrd() {
        return diasValiPrd;
    }

    public void setDiasValiPrd(Integer diasValiPrd) {
        this.diasValiPrd = diasValiPrd;
    }

    public BigDecimal getTasaPremPrd() {
        return tasaPremPrd;
    }

    public void setTasaPremPrd(BigDecimal tasaPremPrd) {
        this.tasaPremPrd = tasaPremPrd;
    }

    public BigDecimal getTasaCastPrd() {
        return tasaCastPrd;
    }

    public void setTasaCastPrd(BigDecimal tasaCastPrd) {
        this.tasaCastPrd = tasaCastPrd;
    }
    
}
