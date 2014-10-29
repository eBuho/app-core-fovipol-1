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

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_SEGURO_RANGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeSeguroRango.findAll", query = "SELECT m FROM MaeSeguroRango m"),
    @NamedQuery(name = "MaeSeguroRango.findByIdenSeguSeg", query = "SELECT m FROM MaeSeguroRango m WHERE m.maeSeguroRangoPK.idenSeguSeg = :idenSeguSeg"),
    @NamedQuery(name = "MaeSeguroRango.findBySecuSeguSgd", query = "SELECT m FROM MaeSeguroRango m WHERE m.maeSeguroRangoPK.secuSeguSgd = :secuSeguSgd"),
    @NamedQuery(name = "MaeSeguroRango.findByDescNombSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.descNombSgr = :descNombSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByRagnEdaiSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.ragnEdaiSgr = :ragnEdaiSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByRangEdafSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.rangEdafSgr = :rangEdafSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByTasaSeguSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.tasaSeguSgr = :tasaSeguSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByUsuaCreaSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.usuaCreaSgr = :usuaCreaSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByFechCreaSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.fechCreaSgr = :fechCreaSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByUsuaModiSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.usuaModiSgr = :usuaModiSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByFechModiSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.fechModiSgr = :fechModiSgr"),
    @NamedQuery(name = "MaeSeguroRango.findByNombEquiAud", query = "SELECT m FROM MaeSeguroRango m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeSeguroRango.findByNombSopeAud", query = "SELECT m FROM MaeSeguroRango m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeSeguroRango.findByFlagEstaSgr", query = "SELECT m FROM MaeSeguroRango m WHERE m.flagEstaSgr = :flagEstaSgr")})
public class MaeSeguroRango implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaeSeguroRangoPK maeSeguroRangoPK;
    @Size(max = 120)
    @Column(name = "DESC_NOMB_SGR")
    private String descNombSgr;
    @Column(name = "RAGN_EDAI_SGR")
    private BigInteger ragnEdaiSgr;
    @Column(name = "RANG_EDAF_SGR")
    private BigInteger rangEdafSgr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TASA_SEGU_SGR")
    private BigDecimal tasaSeguSgr;
    @Size(max = 15)
    @Column(name = "USUA_CREA_SGR")
    private String usuaCreaSgr;
    @Column(name = "FECH_CREA_SGR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaSgr;
    @Size(max = 15)
    @Column(name = "USUA_MODI_SGR")
    private String usuaModiSgr;
    @Column(name = "FECH_MODI_SGR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechModiSgr;
    @Size(max = 40)
    @Column(name = "NOMB_EQUI_AUD")
    private String nombEquiAud;
    @Size(max = 40)
    @Column(name = "NOMB_SOPE_AUD")
    private String nombSopeAud;
    @Column(name = "FLAG_ESTA_SGR")
    private Short flagEstaSgr;
    @JoinColumn(name = "IDEN_SEGU_SEG", referencedColumnName = "IDEN_SEGU_SEG", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MaeSeguro maeSeguro;

    public MaeSeguroRango() {
    }

    public MaeSeguroRango(MaeSeguroRangoPK maeSeguroRangoPK) {
        this.maeSeguroRangoPK = maeSeguroRangoPK;
    }

    public MaeSeguroRango(BigInteger idenSeguSeg, int secuSeguSgd) {
        this.maeSeguroRangoPK = new MaeSeguroRangoPK(idenSeguSeg, secuSeguSgd);
    }

    public MaeSeguroRangoPK getMaeSeguroRangoPK() {
        return maeSeguroRangoPK;
    }

    public void setMaeSeguroRangoPK(MaeSeguroRangoPK maeSeguroRangoPK) {
        this.maeSeguroRangoPK = maeSeguroRangoPK;
    }

    public String getDescNombSgr() {
        return descNombSgr;
    }

    public void setDescNombSgr(String descNombSgr) {
        this.descNombSgr = descNombSgr;
    }

    public BigInteger getRagnEdaiSgr() {
        return ragnEdaiSgr;
    }

    public void setRagnEdaiSgr(BigInteger ragnEdaiSgr) {
        this.ragnEdaiSgr = ragnEdaiSgr;
    }

    public BigInteger getRangEdafSgr() {
        return rangEdafSgr;
    }

    public void setRangEdafSgr(BigInteger rangEdafSgr) {
        this.rangEdafSgr = rangEdafSgr;
    }

    public BigDecimal getTasaSeguSgr() {
        return tasaSeguSgr;
    }

    public void setTasaSeguSgr(BigDecimal tasaSeguSgr) {
        this.tasaSeguSgr = tasaSeguSgr;
    }

    public String getUsuaCreaSgr() {
        return usuaCreaSgr;
    }

    public void setUsuaCreaSgr(String usuaCreaSgr) {
        this.usuaCreaSgr = usuaCreaSgr;
    }

    public Date getFechCreaSgr() {
        return fechCreaSgr;
    }

    public void setFechCreaSgr(Date fechCreaSgr) {
        this.fechCreaSgr = fechCreaSgr;
    }

    public String getUsuaModiSgr() {
        return usuaModiSgr;
    }

    public void setUsuaModiSgr(String usuaModiSgr) {
        this.usuaModiSgr = usuaModiSgr;
    }

    public Date getFechModiSgr() {
        return fechModiSgr;
    }

    public void setFechModiSgr(Date fechModiSgr) {
        this.fechModiSgr = fechModiSgr;
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

    public Short getFlagEstaSgr() {
        return flagEstaSgr;
    }

    public void setFlagEstaSgr(Short flagEstaSgr) {
        this.flagEstaSgr = flagEstaSgr;
    }

    public MaeSeguro getMaeSeguro() {
        return maeSeguro;
    }

    public void setMaeSeguro(MaeSeguro maeSeguro) {
        this.maeSeguro = maeSeguro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maeSeguroRangoPK != null ? maeSeguroRangoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeSeguroRango)) {
            return false;
        }
        MaeSeguroRango other = (MaeSeguroRango) object;
        if ((this.maeSeguroRangoPK == null && other.maeSeguroRangoPK != null) || (this.maeSeguroRangoPK != null && !this.maeSeguroRangoPK.equals(other.maeSeguroRangoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeSeguroRango[ maeSeguroRangoPK=" + maeSeguroRangoPK + " ]";
    }
    
}
