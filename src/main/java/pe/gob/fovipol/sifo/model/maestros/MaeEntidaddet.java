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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_ENTIDADDET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeEntidaddet.findAll", query = "SELECT m FROM MaeEntidaddet m"),
    @NamedQuery(name = "MaeEntidaddet.findBySecuEntiDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.secuEntiDet = :secuEntiDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoNumuDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoNumuDet = :valoNumuDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoNumdDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoNumdDet = :valoNumdDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoNumtDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoNumtDet = :valoNumtDet"),
    @NamedQuery(name = "MaeEntidaddet.findByUsuaCreaDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.usuaCreaDet = :usuaCreaDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoDecuDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoDecuDet = :valoDecuDet"),
    @NamedQuery(name = "MaeEntidaddet.findByFechCreaDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.fechCreaDet = :fechCreaDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoDecdDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoDecdDet = :valoDecdDet"),
    @NamedQuery(name = "MaeEntidaddet.findByUsuaModiDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.usuaModiDet = :usuaModiDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoDectDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoDectDet = :valoDectDet"),
    @NamedQuery(name = "MaeEntidaddet.findByFechModiDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.fechModiDet = :fechModiDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoFecuDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoFecuDet = :valoFecuDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoFecdDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoFecdDet = :valoFecdDet"),
    @NamedQuery(name = "MaeEntidaddet.findByFlagEstaDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.flagEstaDet = :flagEstaDet"),
    @NamedQuery(name = "MaeEntidaddet.findByIdenEntiDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.idenEntiDet = :idenEntiDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoFectDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoFectDet = :valoFectDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoCaduDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoCaduDet = :valoCaduDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoCaddDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoCaddDet = :valoCaddDet"),
    @NamedQuery(name = "MaeEntidaddet.findByValoCadtDet", query = "SELECT m FROM MaeEntidaddet m WHERE m.valoCadtDet = :valoCadtDet")})
public class MaeEntidaddet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_ENTI_DET")
    private BigDecimal idenEntiDet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_ENTI_DET")
    private int secuEntiDet;
    @Column(name = "VALO_NUMU_DET")
    private BigInteger valoNumuDet;
    @Column(name = "VALO_NUMD_DET")
    private BigInteger valoNumdDet;
    @Column(name = "VALO_NUMT_DET")
    private BigInteger valoNumtDet;
    @Size(max = 15)
    @Column(name = "USUA_CREA_DET")
    private String usuaCreaDet;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALO_DECU_DET")
    private BigDecimal valoDecuDet;
    @Column(name = "FECH_CREA_DET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaDet;
    @Column(name = "VALO_DECD_DET")
    private BigDecimal valoDecdDet;
    @Size(max = 15)
    @Column(name = "USUA_MODI_DET")
    private String usuaModiDet;
    @Column(name = "VALO_DECT_DET")
    private BigDecimal valoDectDet;
    @Column(name = "FECH_MODI_DET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechModiDet;
    @Column(name = "VALO_FECU_DET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valoFecuDet;
    @Column(name = "VALO_FECD_DET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valoFecdDet;
    @Column(name = "FLAG_ESTA_DET")
    private Short flagEstaDet;
    
    @Column(name = "VALO_FECT_DET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valoFectDet;
    @Size(max = 50)
    @Column(name = "VALO_CADU_DET")
    private String valoCaduDet;
    @Size(max = 50)
    @Column(name = "VALO_CADD_DET")
    private String valoCaddDet;
    @Size(max = 50)
    @Column(name = "VALO_CADT_DET")
    private String valoCadtDet;
    @JoinColumn(name = "CODI_ENTI_ENT", referencedColumnName = "CODI_ENTI_ENT")
    @ManyToOne
    private MaeEntidad codiEntiEnt;

    public MaeEntidaddet() {
    }

    public MaeEntidaddet(BigDecimal idenEntiDet) {
        this.idenEntiDet = idenEntiDet;
    }

    public MaeEntidaddet(BigDecimal idenEntiDet, int secuEntiDet) {
        this.idenEntiDet = idenEntiDet;
        this.secuEntiDet = secuEntiDet;
    }

    public int getSecuEntiDet() {
        return secuEntiDet;
    }

    public void setSecuEntiDet(int secuEntiDet) {
        this.secuEntiDet = secuEntiDet;
    }

    public BigInteger getValoNumuDet() {
        return valoNumuDet;
    }

    public void setValoNumuDet(BigInteger valoNumuDet) {
        this.valoNumuDet = valoNumuDet;
    }

    public BigInteger getValoNumdDet() {
        return valoNumdDet;
    }

    public void setValoNumdDet(BigInteger valoNumdDet) {
        this.valoNumdDet = valoNumdDet;
    }

    public BigInteger getValoNumtDet() {
        return valoNumtDet;
    }

    public void setValoNumtDet(BigInteger valoNumtDet) {
        this.valoNumtDet = valoNumtDet;
    }

    public String getUsuaCreaDet() {
        return usuaCreaDet;
    }

    public void setUsuaCreaDet(String usuaCreaDet) {
        this.usuaCreaDet = usuaCreaDet;
    }

    public BigDecimal getValoDecuDet() {
        return valoDecuDet;
    }

    public void setValoDecuDet(BigDecimal valoDecuDet) {
        this.valoDecuDet = valoDecuDet;
    }

    public Date getFechCreaDet() {
        return fechCreaDet;
    }

    public void setFechCreaDet(Date fechCreaDet) {
        this.fechCreaDet = fechCreaDet;
    }

    public BigDecimal getValoDecdDet() {
        return valoDecdDet;
    }

    public void setValoDecdDet(BigDecimal valoDecdDet) {
        this.valoDecdDet = valoDecdDet;
    }

    public String getUsuaModiDet() {
        return usuaModiDet;
    }

    public void setUsuaModiDet(String usuaModiDet) {
        this.usuaModiDet = usuaModiDet;
    }

    public BigDecimal getValoDectDet() {
        return valoDectDet;
    }

    public void setValoDectDet(BigDecimal valoDectDet) {
        this.valoDectDet = valoDectDet;
    }

    public Date getFechModiDet() {
        return fechModiDet;
    }

    public void setFechModiDet(Date fechModiDet) {
        this.fechModiDet = fechModiDet;
    }

    public Date getValoFecuDet() {
        return valoFecuDet;
    }

    public void setValoFecuDet(Date valoFecuDet) {
        this.valoFecuDet = valoFecuDet;
    }

    public Date getValoFecdDet() {
        return valoFecdDet;
    }

    public void setValoFecdDet(Date valoFecdDet) {
        this.valoFecdDet = valoFecdDet;
    }

    public Short getFlagEstaDet() {
        return flagEstaDet;
    }

    public void setFlagEstaDet(Short flagEstaDet) {
        this.flagEstaDet = flagEstaDet;
    }

    public BigDecimal getIdenEntiDet() {
        return idenEntiDet;
    }

    public void setIdenEntiDet(BigDecimal idenEntiDet) {
        this.idenEntiDet = idenEntiDet;
    }

    public Date getValoFectDet() {
        return valoFectDet;
    }

    public void setValoFectDet(Date valoFectDet) {
        this.valoFectDet = valoFectDet;
    }

    public String getValoCaduDet() {
        return valoCaduDet;
    }

    public void setValoCaduDet(String valoCaduDet) {
        this.valoCaduDet = valoCaduDet;
    }

    public String getValoCaddDet() {
        return valoCaddDet;
    }

    public void setValoCaddDet(String valoCaddDet) {
        this.valoCaddDet = valoCaddDet;
    }

    public String getValoCadtDet() {
        return valoCadtDet;
    }

    public void setValoCadtDet(String valoCadtDet) {
        this.valoCadtDet = valoCadtDet;
    }

    public MaeEntidad getCodiEntiEnt() {
        return codiEntiEnt;
    }

    public void setCodiEntiEnt(MaeEntidad codiEntiEnt) {
        this.codiEntiEnt = codiEntiEnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenEntiDet != null ? idenEntiDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeEntidaddet)) {
            return false;
        }
        MaeEntidaddet other = (MaeEntidaddet) object;
        if ((this.idenEntiDet == null && other.idenEntiDet != null) || (this.idenEntiDet != null && !this.idenEntiDet.equals(other.idenEntiDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.model.MaeEntidaddet[ idenEntiDet=" + idenEntiDet + " ]";
    }
    
}
