/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.tramite.TrmMovimiento;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_AREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeArea.findAll", query = "SELECT m FROM MaeArea m"),
    @NamedQuery(name = "MaeArea.findByCodiAreaAre", query = "SELECT m FROM MaeArea m WHERE m.codiAreaAre = :codiAreaAre"),
    @NamedQuery(name = "MaeArea.findByDescAreaAre", query = "SELECT m FROM MaeArea m WHERE m.descAreaAre = :descAreaAre"),
    @NamedQuery(name = "MaeArea.findByTipoAreaAre", query = "SELECT m FROM MaeArea m WHERE m.tipoAreaAre = :tipoAreaAre"),
    @NamedQuery(name = "MaeArea.findByNumeTeleAre", query = "SELECT m FROM MaeArea m WHERE m.numeTeleAre = :numeTeleAre"),
    @NamedQuery(name = "MaeArea.findByNumeAnexAre", query = "SELECT m FROM MaeArea m WHERE m.numeAnexAre = :numeAnexAre"),
    @NamedQuery(name = "MaeArea.findByUsuaCreaAud", query = "SELECT m FROM MaeArea m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeArea.findByFechCreaAud", query = "SELECT m FROM MaeArea m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeArea.findByUsuaModiAud", query = "SELECT m FROM MaeArea m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeArea.findByFechModiAud", query = "SELECT m FROM MaeArea m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeArea.findByNombEquiAud", query = "SELECT m FROM MaeArea m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeArea.findByNombSopeAud", query = "SELECT m FROM MaeArea m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeArea.findByFlagEstaAre", query = "SELECT m FROM MaeArea m WHERE m.flagEstaAre = :flagEstaAre")})
public class MaeArea implements Serializable {
    @OneToMany(mappedBy = "areaDestMvm")
    private List<TrmMovimiento> trmMovimientoList;
    @OneToMany(mappedBy = "areaOrigMvm")
    private List<TrmMovimiento> trmMovimientoList1;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @TableGenerator(name = "maeAreaSeq",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "MAE_AREA", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="maeAreaSeq") 
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_AREA_ARE")
    private BigDecimal codiAreaAre;
    @Size(max = 120)
    @Column(name = "DESC_AREA_ARE")
    private String descAreaAre;
    @Column(name = "TIPO_AREA_ARE")
    private Integer tipoAreaAre;
    @Size(max = 20)
    @Column(name = "NUME_TELE_ARE")
    @Pattern(regexp="[0-9\\- ]*")
    private String numeTeleAre;
    @Size(max = 5)
    @Column(name = "NUME_ANEX_ARE")
    @Pattern(regexp="[0-9\\- ]*")
    private String numeAnexAre;
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
    @Column(name = "FLAG_ESTA_ARE")
    private Short flagEstaAre;
    @OneToMany(mappedBy = "codiAreaAre")
    private List<MaeProceso> maeProcesoList;
    @OneToMany(mappedBy = "codiArepAre")
    private List<MaeArea> maeAreaList;
    @JoinColumn(name = "CODI_AREP_ARE", referencedColumnName = "IDEN_AREA_ARE")
    @ManyToOne
    private MaeArea codiArepAre;
    @JoinColumn(name = "CODI_MAE_SEDE", referencedColumnName = "CODI_MAE_SEDE")
    @ManyToOne
    private MaeSede codiMaeSede;

    public MaeArea() {
    }

    public MaeArea(BigDecimal codiAreaAre) {
        this.codiAreaAre = codiAreaAre;
    }

    public BigDecimal getCodiAreaAre() {
        return codiAreaAre;
    }

    public void setCodiAreaAre(BigDecimal codiAreaAre) {
        this.codiAreaAre = codiAreaAre;
    }

    public String getDescAreaAre() {
        return descAreaAre;
    }

    public void setDescAreaAre(String descAreaAre) {
        this.descAreaAre = descAreaAre;
    }

    public Integer getTipoAreaAre() {
        return tipoAreaAre;
    }

    public void setTipoAreaAre(Integer tipoAreaAre) {
        this.tipoAreaAre = tipoAreaAre;
    }

    public String getNumeTeleAre() {
        return numeTeleAre;
    }

    public void setNumeTeleAre(String numeTeleAre) {
        this.numeTeleAre = numeTeleAre;
    }

    public String getNumeAnexAre() {
        return numeAnexAre;
    }

    public void setNumeAnexAre(String numeAnexAre) {
        this.numeAnexAre = numeAnexAre;
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

    public Short getFlagEstaAre() {
        return flagEstaAre;
    }

    public void setFlagEstaAre(Short flagEstaAre) {
        this.flagEstaAre = flagEstaAre;
    }

    @XmlTransient
    public List<MaeProceso> getMaeProcesoList() {
        return maeProcesoList;
    }

    public void setMaeProcesoList(List<MaeProceso> maeProcesoList) {
        this.maeProcesoList = maeProcesoList;
    }

    @XmlTransient
    public List<MaeArea> getMaeAreaList() {
        return maeAreaList;
    }

    public void setMaeAreaList(List<MaeArea> maeAreaList) {
        this.maeAreaList = maeAreaList;
    }

    public MaeArea getCodiArepAre() {
        return codiArepAre;
    }

    public void setCodiArepAre(MaeArea codiArepAre) {
        this.codiArepAre = codiArepAre;
    }

    public MaeSede getCodiMaeSede() {
        return codiMaeSede;
    }

    public void setCodiMaeSede(MaeSede codiMaeSede) {
        this.codiMaeSede = codiMaeSede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiAreaAre != null ? codiAreaAre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeArea)) {
            return false;
        }
        MaeArea other = (MaeArea) object;
        if ((this.codiAreaAre == null && other.codiAreaAre != null) || (this.codiAreaAre != null && !this.codiAreaAre.equals(other.codiAreaAre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.MaeArea[ codiAreaAre=" + codiAreaAre + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<TrmMovimiento> getTrmMovimientoList() {
        return trmMovimientoList;
    }

    public void setTrmMovimientoList(List<TrmMovimiento> trmMovimientoList) {
        this.trmMovimientoList = trmMovimientoList;
    }

    @XmlTransient
    @JsonIgnore
    public List<TrmMovimiento> getTrmMovimientoList1() {
        return trmMovimientoList1;
    }

    public void setTrmMovimientoList1(List<TrmMovimiento> trmMovimientoList1) {
        this.trmMovimientoList1 = trmMovimientoList1;
    }
    
}
