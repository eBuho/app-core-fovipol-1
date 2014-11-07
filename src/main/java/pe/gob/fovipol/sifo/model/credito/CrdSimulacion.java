/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
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
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "CRD_SIMULACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdSimulacion.findAll", query = "SELECT c FROM CrdSimulacion c"),
    @NamedQuery(name = "CrdSimulacion.findByIdenSimuSim", query = "SELECT c FROM CrdSimulacion c WHERE c.idenSimuSim = :idenSimuSim"),
    @NamedQuery(name = "CrdSimulacion.findByCodiSimuSim", query = "SELECT c FROM CrdSimulacion c WHERE c.codiSimuSim = :codiSimuSim"),
    @NamedQuery(name = "CrdSimulacion.findByIngrBrtoSim", query = "SELECT c FROM CrdSimulacion c WHERE c.ingrBrtoSim = :ingrBrtoSim"),
    @NamedQuery(name = "CrdSimulacion.findByDsctOficSim", query = "SELECT c FROM CrdSimulacion c WHERE c.dsctOficSim = :dsctOficSim"),
    @NamedQuery(name = "CrdSimulacion.findByDsctPersSim", query = "SELECT c FROM CrdSimulacion c WHERE c.dsctPersSim = :dsctPersSim"),
    @NamedQuery(name = "CrdSimulacion.findByIngrCombSim", query = "SELECT c FROM CrdSimulacion c WHERE c.ingrCombSim = :ingrCombSim"),
    @NamedQuery(name = "CrdSimulacion.findByOtrsDsctSim", query = "SELECT c FROM CrdSimulacion c WHERE c.otrsDsctSim = :otrsDsctSim"),
    @NamedQuery(name = "CrdSimulacion.findByDescDsctSim", query = "SELECT c FROM CrdSimulacion c WHERE c.descDsctSim = :descDsctSim"),
    @NamedQuery(name = "CrdSimulacion.findByOtroIngrSim", query = "SELECT c FROM CrdSimulacion c WHERE c.otroIngrSim = :otroIngrSim"),
    @NamedQuery(name = "CrdSimulacion.findByDescIngrSim", query = "SELECT c FROM CrdSimulacion c WHERE c.descIngrSim = :descIngrSim"),
    @NamedQuery(name = "CrdSimulacion.findByCapaMcuoSim", query = "SELECT c FROM CrdSimulacion c WHERE c.capaMcuoSim = :capaMcuoSim"),
    @NamedQuery(name = "CrdSimulacion.findByImpoMaxpSim", query = "SELECT c FROM CrdSimulacion c WHERE c.impoMaxpSim = :impoMaxpSim"),
    @NamedQuery(name = "CrdSimulacion.findByDeudOtraSim", query = "SELECT c FROM CrdSimulacion c WHERE c.deudOtraSim = :deudOtraSim"),
    @NamedQuery(name = "CrdSimulacion.findByTasaTeaSim", query = "SELECT c FROM CrdSimulacion c WHERE c.tasaTeaSim = :tasaTeaSim"),
    @NamedQuery(name = "CrdSimulacion.findByPlazPresSim", query = "SELECT c FROM CrdSimulacion c WHERE c.plazPresSim = :plazPresSim"),
    @NamedQuery(name = "CrdSimulacion.findByTasaGadmSim", query = "SELECT c FROM CrdSimulacion c WHERE c.tasaGadmSim = :tasaGadmSim"),
    @NamedQuery(name = "CrdSimulacion.findByPeriGracSim", query = "SELECT c FROM CrdSimulacion c WHERE c.periGracSim = :periGracSim"),
    @NamedQuery(name = "CrdSimulacion.findByAutoCdobSim", query = "SELECT c FROM CrdSimulacion c WHERE c.autoCdobSim = :autoCdobSim"),
    @NamedQuery(name = "CrdSimulacion.findByUsuaCreaAud", query = "SELECT c FROM CrdSimulacion c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdSimulacion.findByFechCreaAud", query = "SELECT c FROM CrdSimulacion c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdSimulacion.findByUsuaModiAud", query = "SELECT c FROM CrdSimulacion c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdSimulacion.findByFechModiAud", query = "SELECT c FROM CrdSimulacion c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdSimulacion.findByNombEquiAud", query = "SELECT c FROM CrdSimulacion c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdSimulacion.findByNombSopeAud", query = "SELECT c FROM CrdSimulacion c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdSimulacion.findByFlagEstaSim", query = "SELECT c FROM CrdSimulacion c WHERE c.flagEstaSim = :flagEstaSim")})
public class CrdSimulacion implements Serializable {   
   
    @OneToMany(mappedBy = "idenSimuSim")
    private List<TrmTramite> trmTramiteList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_SIMU_SIM")
    private BigDecimal idenSimuSim;
    @Size(max = 20)
    @Column(name = "CODI_SIMU_SIM")
    private String codiSimuSim;
    @Column(name = "INGR_BRTO_SIM")
    private BigDecimal ingrBrtoSim;
    @Column(name = "DSCT_OFIC_SIM")
    private BigDecimal dsctOficSim;
    @Column(name = "DSCT_PERS_SIM")
    private BigDecimal dsctPersSim;
    @Column(name = "INGR_COMB_SIM")
    private BigDecimal ingrCombSim;
    @Column(name = "OTRS_DSCT_SIM")
    private BigDecimal otrsDsctSim;
    @Size(max = 300)
    @Column(name = "DESC_DSCT_SIM")
    private String descDsctSim;
    @Column(name = "OTRO_INGR_SIM")
    private BigDecimal otroIngrSim;
    @Size(max = 300)
    @Column(name = "DESC_INGR_SIM")
    private String descIngrSim;
    @Column(name = "CAPA_MCUO_SIM")
    private BigDecimal capaMcuoSim;
    @Column(name = "IMPO_MAXP_SIM")
    private BigDecimal impoMaxpSim;
    @Column(name = "DEUD_OTRA_SIM")
    private BigDecimal deudOtraSim;
    @Column(name = "TASA_TEA_SIM")
    private BigDecimal tasaTeaSim;
    @Column(name = "PLAZ_PRES_SIM")
    private BigInteger plazPresSim;
    @Column(name = "TASA_GADM_SIM")
    private BigDecimal tasaGadmSim;
    @Column(name = "PERI_GRAC_SIM")
    private BigInteger periGracSim;
    @Column(name = "AUTO_CDOB_SIM")
    private Character autoCdobSim;
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
    @Column(name = "FLAG_ESTA_SIM")
    private Short flagEstaSim;
    @JoinColumn(name = "IDEN_PERS_PER", referencedColumnName = "IDEN_PERS_PER")
    @ManyToOne
    private MaeSocio idenPersPer;
    @JoinColumn(name = "IDEN_PROD_PRD", referencedColumnName = "IDEN_PROD_PRD")
    @ManyToOne
    private MaeProducto idenProdPrd;
    @Column(name = "CODI_MONE_CRD")
    private Integer codiMoneCrd;

    public CrdSimulacion() {
    }

    public CrdSimulacion(BigDecimal idenSimuSim) {
        this.idenSimuSim = idenSimuSim;
    }

    public BigDecimal getIdenSimuSim() {
        return idenSimuSim;
    }

    public void setIdenSimuSim(BigDecimal idenSimuSim) {
        this.idenSimuSim = idenSimuSim;
    }

    public String getCodiSimuSim() {
        return codiSimuSim;
    }

    public void setCodiSimuSim(String codiSimuSim) {
        this.codiSimuSim = codiSimuSim;
    }

    public BigDecimal getIngrBrtoSim() {
        return ingrBrtoSim;
    }

    public void setIngrBrtoSim(BigDecimal ingrBrtoSim) {
        this.ingrBrtoSim = ingrBrtoSim;
    }

    public BigDecimal getDsctOficSim() {
        return dsctOficSim;
    }

    public void setDsctOficSim(BigDecimal dsctOficSim) {
        this.dsctOficSim = dsctOficSim;
    }

    public BigDecimal getDsctPersSim() {
        return dsctPersSim;
    }

    public void setDsctPersSim(BigDecimal dsctPersSim) {
        this.dsctPersSim = dsctPersSim;
    }

    public BigDecimal getIngrCombSim() {
        return ingrCombSim;
    }

    public void setIngrCombSim(BigDecimal ingrCombSim) {
        this.ingrCombSim = ingrCombSim;
    }

    public BigDecimal getOtrsDsctSim() {
        return otrsDsctSim;
    }

    public void setOtrsDsctSim(BigDecimal otrsDsctSim) {
        this.otrsDsctSim = otrsDsctSim;
    }

    public String getDescDsctSim() {
        return descDsctSim;
    }

    public void setDescDsctSim(String descDsctSim) {
        this.descDsctSim = descDsctSim;
    }

    public BigDecimal getOtroIngrSim() {
        return otroIngrSim;
    }

    public void setOtroIngrSim(BigDecimal otroIngrSim) {
        this.otroIngrSim = otroIngrSim;
    }

    public String getDescIngrSim() {
        return descIngrSim;
    }

    public void setDescIngrSim(String descIngrSim) {
        this.descIngrSim = descIngrSim;
    }

    public BigDecimal getCapaMcuoSim() {
        return capaMcuoSim;
    }

    public void setCapaMcuoSim(BigDecimal capaMcuoSim) {
        this.capaMcuoSim = capaMcuoSim;
    }

    public BigDecimal getImpoMaxpSim() {
        return impoMaxpSim;
    }

    public void setImpoMaxpSim(BigDecimal impoMaxpSim) {
        this.impoMaxpSim = impoMaxpSim;
    }

    public BigDecimal getDeudOtraSim() {
        return deudOtraSim;
    }

    public void setDeudOtraSim(BigDecimal deudOtraSim) {
        this.deudOtraSim = deudOtraSim;
    }

    public BigDecimal getTasaTeaSim() {
        return tasaTeaSim;
    }

    public void setTasaTeaSim(BigDecimal tasaTeaSim) {
        this.tasaTeaSim = tasaTeaSim;
    }

    public BigInteger getPlazPresSim() {
        return plazPresSim;
    }

    public void setPlazPresSim(BigInteger plazPresSim) {
        this.plazPresSim = plazPresSim;
    }

    public BigDecimal getTasaGadmSim() {
        return tasaGadmSim;
    }

    public void setTasaGadmSim(BigDecimal tasaGadmSim) {
        this.tasaGadmSim = tasaGadmSim;
    }

    public BigInteger getPeriGracSim() {
        return periGracSim;
    }

    public void setPeriGracSim(BigInteger periGracSim) {
        this.periGracSim = periGracSim;
    }

    public Character getAutoCdobSim() {
        return autoCdobSim;
    }

    public void setAutoCdobSim(Character autoCdobSim) {
        this.autoCdobSim = autoCdobSim;
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

    public Short getFlagEstaSim() {
        return flagEstaSim;
    }

    public void setFlagEstaSim(Short flagEstaSim) {
        this.flagEstaSim = flagEstaSim;
    }

    public MaeSocio getIdenPersPer() {
        return idenPersPer;
    }

    public void setIdenPersPer(MaeSocio idenPersPer) {
        this.idenPersPer = idenPersPer;
    }

    public MaeProducto getIdenProdPrd() {
        return idenProdPrd;
    }

    public void setIdenProdPrd(MaeProducto idenProdPrd) {
        this.idenProdPrd = idenProdPrd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenSimuSim != null ? idenSimuSim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdSimulacion)) {
            return false;
        }
        CrdSimulacion other = (CrdSimulacion) object;
        if ((this.idenSimuSim == null && other.idenSimuSim != null) || (this.idenSimuSim != null && !this.idenSimuSim.equals(other.idenSimuSim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String fecha=" ";
        if(getFechCreaAud()!=null){
            DateFormat d=DateFormat.getDateInstance(DateFormat.SHORT);
            fecha=" - "+d.format(fechCreaAud);
        }
        return idenSimuSim + fecha;
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

    @XmlTransient
    @JsonIgnore
    public List<TrmTramite> getTrmTramiteList() {
        return trmTramiteList;
    }

    public void setTrmTramiteList(List<TrmTramite> trmTramiteList) {
        this.trmTramiteList = trmTramiteList;
    }

}
