/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_PROCESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeProceso.findAll", query = "SELECT m FROM MaeProceso m"),
    @NamedQuery(name = "MaeProceso.findByCodiProcPrc", query = "SELECT m FROM MaeProceso m WHERE m.codiProcPrc = :codiProcPrc"),
    @NamedQuery(name = "MaeProceso.findByNombProcPrc", query = "SELECT m FROM MaeProceso m WHERE m.nombProcPrc = :nombProcPrc"),
    @NamedQuery(name = "MaeProceso.findByTiemDemoPrc", query = "SELECT m FROM MaeProceso m WHERE m.tiemDemoPrc = :tiemDemoPrc"),
    @NamedQuery(name = "MaeProceso.findByNiveProcPrc", query = "SELECT m FROM MaeProceso m WHERE m.niveProcPrc = :niveProcPrc"),
    @NamedQuery(name = "MaeProceso.findByOrdeSecuPrc", query = "SELECT m FROM MaeProceso m WHERE m.ordeSecuPrc = :ordeSecuPrc"),
    @NamedQuery(name = "MaeProceso.findByUsuaCreaAud", query = "SELECT m FROM MaeProceso m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeProceso.findByFechCreaAud", query = "SELECT m FROM MaeProceso m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeProceso.findByUsuaModiAud", query = "SELECT m FROM MaeProceso m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeProceso.findByFechModiAud", query = "SELECT m FROM MaeProceso m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeProceso.findByNombEquiAud", query = "SELECT m FROM MaeProceso m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeProceso.findByNombSopeAud", query = "SELECT m FROM MaeProceso m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeProceso.findByFlagEstaPrc", query = "SELECT m FROM MaeProceso m WHERE m.flagEstaPrc = :flagEstaPrc")})
public class MaeProceso implements Serializable {
    @Column(name = "NIVE_PROC_PRC")
    private Short niveProcPrc;
    @Column(name = "ORDE_SECU_PRC")
    private Integer ordeSecuPrc;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maeProceso")
    private List<MaeProcesoestado> maeProcesoestadoList;
    @Column(name = "SECU_ESTA_PRC")
    private Integer secuEstaPrc;
    @Column(name = "FLAG_ALER_PRC")
    private Character flagAlerPrc;
    @Size(max = 120)
    @Column(name = "ASUN_EMAI_PRC")
    private String asunEmaiPrc;
    @Size(max = 500)
    @Column(name = "CABE_EMAI_PRC")
    private String cabeEmaiPrc;
    @Size(max = 500)
    @Column(name = "PIEP_EMAI_PRC")
    private String piepEmaiPrc;    
    @Size(max = 100)
    @Column(name = "PAGI_PROC_PRC")
    private String pagiProcPrc;
    @OneToMany(mappedBy = "idenProcPrc")
    private List<TrmMovimiento> trmMovimientoList;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maeProceso")
    private List<MaeRequisito> maeRequisitoList;
    @Column(name = "TIPO_PROC_PRC")
    private Character tipoProcPrc;
    @OneToMany(mappedBy = "idenProcPrc")
    private Collection<MaeProducto> maeProductoCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PROC_PRC")
    private BigDecimal codiProcPrc;
    @Size(max = 120)
    @Column(name = "NOMB_PROC_PRC")
    private String nombProcPrc;
    @Column(name = "TIEM_DEMO_PRC")
    private BigDecimal tiemDemoPrc;
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
    @Column(name = "FLAG_ESTA_PRC")
    private Short flagEstaPrc;    
    @JoinColumn(name = "IDEN_AREA_ARE", referencedColumnName = "IDEN_AREA_ARE")
    @ManyToOne
    private MaeArea codiAreaAre;
    @OneToMany(mappedBy = "codiPropPrc")
    private List<MaeProceso> maeProcesoList;
    @JoinColumn(name = "CODI_PROP_PRC", referencedColumnName = "IDEN_PROC_PRC")
    @ManyToOne
    private MaeProceso codiPropPrc;

    public MaeProceso() {
    }

    public MaeProceso(BigDecimal codiProcPrc) {
        this.codiProcPrc = codiProcPrc;
    }

    public BigDecimal getCodiProcPrc() {
        return codiProcPrc;
    }

    public void setCodiProcPrc(BigDecimal codiProcPrc) {
        this.codiProcPrc = codiProcPrc;
    }

    public String getNombProcPrc() {
        return nombProcPrc;
    }

    public void setNombProcPrc(String nombProcPrc) {
        this.nombProcPrc = nombProcPrc;
    }

    public BigDecimal getTiemDemoPrc() {
        return tiemDemoPrc;
    }

    public void setTiemDemoPrc(BigDecimal tiemDemoPrc) {
        this.tiemDemoPrc = tiemDemoPrc;
    }

    public Short getNiveProcPrc() {
        return niveProcPrc;
    }

    public void setNiveProcPrc(Short niveProcPrc) {
        this.niveProcPrc = niveProcPrc;
    }

    public Integer getOrdeSecuPrc() {
        return ordeSecuPrc;
    }

    public void setOrdeSecuPrc(Integer ordeSecuPrc) {
        this.ordeSecuPrc = ordeSecuPrc;
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

    public Short getFlagEstaPrc() {
        return flagEstaPrc;
    }

    public void setFlagEstaPrc(Short flagEstaPrc) {
        this.flagEstaPrc = flagEstaPrc;
    }

    public MaeArea getCodiAreaAre() {
        return codiAreaAre;
    }

    public void setCodiAreaAre(MaeArea codiAreaAre) {
        this.codiAreaAre = codiAreaAre;
    }

    @XmlTransient
    public List<MaeProceso> getMaeProcesoList() {
        return maeProcesoList;
    }

    public void setMaeProcesoList(List<MaeProceso> maeProcesoList) {
        this.maeProcesoList = maeProcesoList;
    }

    public MaeProceso getCodiPropPrc() {
        return codiPropPrc;
    }

    public void setCodiPropPrc(MaeProceso codiPropPrc) {
        this.codiPropPrc = codiPropPrc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiProcPrc != null ? codiProcPrc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeProceso)) {
            return false;
        }
        MaeProceso other = (MaeProceso) object;
        if ((this.codiProcPrc == null && other.codiProcPrc != null) || (this.codiProcPrc != null && !this.codiProcPrc.equals(other.codiProcPrc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.MaeProceso[ codiProcPrc=" + codiProcPrc + " ]";
    }

    public Character getTipoProcPrc() {
        return tipoProcPrc;
    }

    public void setTipoProcPrc(Character tipoProcPrc) {
        this.tipoProcPrc = tipoProcPrc;
    }

    @XmlTransient
    public Collection<MaeProducto> getMaeProductoCollection() {
        return maeProductoCollection;
    }

    public void setMaeProductoCollection(Collection<MaeProducto> maeProductoCollection) {
        this.maeProductoCollection = maeProductoCollection;
    }


    @XmlTransient
    public List<MaeRequisito> getMaeRequisitoList() {
        return maeRequisitoList;
    }

    public void setMaeRequisitoList(List<MaeRequisito> maeRequisitoList) {
        this.maeRequisitoList = maeRequisitoList;
    }

    /**
     * @return the pagiProcPrc
     */
    public String getPagiProcPrc() {
        return pagiProcPrc;
    }

    /**
     * @param pagiProcPrc the pagiProcPrc to set
     */
    public void setPagiProcPrc(String pagiProcPrc) {
        this.pagiProcPrc = pagiProcPrc;
    }

    @XmlTransient
    @JsonIgnore
    public List<TrmMovimiento> getTrmMovimientoList() {
        return trmMovimientoList;
    }

    public void setTrmMovimientoList(List<TrmMovimiento> trmMovimientoList) {
        this.trmMovimientoList = trmMovimientoList;
    }

    public Integer getSecuEstaPrc() {
        return secuEstaPrc;
    }

    public void setSecuEstaPrc(Integer secuEstaPrc) {
        this.secuEstaPrc = secuEstaPrc;
    }

    public Character getFlagAlerPrc() {
        return flagAlerPrc;
    }

    public void setFlagAlerPrc(Character flagAlerPrc) {
        this.flagAlerPrc = flagAlerPrc;
    }

    public String getAsunEmaiPrc() {
        return asunEmaiPrc;
    }

    public void setAsunEmaiPrc(String asunEmaiPrc) {
        this.asunEmaiPrc = asunEmaiPrc;
    }

    public String getCabeEmaiPrc() {
        return cabeEmaiPrc;
    }

    public void setCabeEmaiPrc(String cabeEmaiPrc) {
        this.cabeEmaiPrc = cabeEmaiPrc;
    }

    public String getPiepEmaiPrc() {
        return piepEmaiPrc;
    }

    public void setPiepEmaiPrc(String piepEmaiPrc) {
        this.piepEmaiPrc = piepEmaiPrc;
    }

    @XmlTransient
    @JsonIgnore
    public List<MaeProcesoestado> getMaeProcesoestadoList() {
        return maeProcesoestadoList;
    }

    public void setMaeProcesoestadoList(List<MaeProcesoestado> maeProcesoestadoList) {
        this.maeProcesoestadoList = maeProcesoestadoList;
    }

}
