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
import pe.gob.fovipol.sifo.model.credito.CrdSimulaSeguro;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_SEGURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeSeguro.findAll", query = "SELECT m FROM MaeSeguro m"),
    @NamedQuery(name = "MaeSeguro.findByIdenSeguSeg", query = "SELECT m FROM MaeSeguro m WHERE m.idenSeguSeg = :idenSeguSeg"),
    @NamedQuery(name = "MaeSeguro.findByDescNombSeg", query = "SELECT m FROM MaeSeguro m WHERE m.descNombSeg = :descNombSeg"),
    @NamedQuery(name = "MaeSeguro.findByCodiTipoSeg", query = "SELECT m FROM MaeSeguro m WHERE m.codiTipoSeg = :codiTipoSeg"),
    @NamedQuery(name = "MaeSeguro.findByFechInicSeg", query = "SELECT m FROM MaeSeguro m WHERE m.fechInicSeg = :fechInicSeg"),
    @NamedQuery(name = "MaeSeguro.findByFechFfinSeg", query = "SELECT m FROM MaeSeguro m WHERE m.fechFfinSeg = :fechFfinSeg"),
    @NamedQuery(name = "MaeSeguro.findByDescRutaSeg", query = "SELECT m FROM MaeSeguro m WHERE m.descRutaSeg = :descRutaSeg"),
    @NamedQuery(name = "MaeSeguro.findByUsuaCreaAud", query = "SELECT m FROM MaeSeguro m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeSeguro.findByFechCreaAud", query = "SELECT m FROM MaeSeguro m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeSeguro.findByUsuaModiAud", query = "SELECT m FROM MaeSeguro m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeSeguro.findByFechModiAud", query = "SELECT m FROM MaeSeguro m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeSeguro.findByNombEquiAud", query = "SELECT m FROM MaeSeguro m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeSeguro.findByNombSopeAud", query = "SELECT m FROM MaeSeguro m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeSeguro.findByFlagEstaSeg", query = "SELECT m FROM MaeSeguro m WHERE m.flagEstaSeg = :flagEstaSeg")})
public class MaeSeguro implements Serializable {    
    @OneToMany(mappedBy = "idenSeguSeg")
    private List<CrdSimulaSeguro> crdSimulaSeguroList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_SEGU_SEG")
    private BigDecimal idenSeguSeg;
    @Size(max = 120)
    @Column(name = "DESC_NOMB_SEG")
    private String descNombSeg;
    @Column(name = "CODI_TIPO_SEG")
    private Integer codiTipoSeg;
    @Column(name = "FECH_INIC_SEG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechInicSeg;
    @Column(name = "FECH_FFIN_SEG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechFfinSeg;
    @Size(max = 120)
    @Column(name = "DESC_RUTA_SEG")
    private String descRutaSeg;
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
    @Column(name = "FLAG_ESTA_SEG")
    private Short flagEstaSeg;
    @JoinColumn(name = "IDEN_EMPR_EMP", referencedColumnName = "IDEN_EMPR_EMP")
    @ManyToOne
    private MaeEmpresa idenEmprEmp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maeSeguro")
    private List<MaeSeguroRango> maeSeguroRangoList;

    public MaeSeguro() {
    }

    public MaeSeguro(BigDecimal idenSeguSeg) {
        this.idenSeguSeg = idenSeguSeg;
    }

    public BigDecimal getIdenSeguSeg() {
        return idenSeguSeg;
    }

    public void setIdenSeguSeg(BigDecimal idenSeguSeg) {
        this.idenSeguSeg = idenSeguSeg;
    }

    public String getDescNombSeg() {
        return descNombSeg;
    }

    public void setDescNombSeg(String descNombSeg) {
        this.descNombSeg = descNombSeg;
    }

    public Integer getCodiTipoSeg() {
        return codiTipoSeg;
    }

    public void setCodiTipoSeg(Integer codiTipoSeg) {
        this.codiTipoSeg = codiTipoSeg;
    }

    public Date getFechInicSeg() {
        return fechInicSeg;
    }

    public void setFechInicSeg(Date fechInicSeg) {
        this.fechInicSeg = fechInicSeg;
    }

    public Date getFechFfinSeg() {
        return fechFfinSeg;
    }

    public void setFechFfinSeg(Date fechFfinSeg) {
        this.fechFfinSeg = fechFfinSeg;
    }

    public String getDescRutaSeg() {
        return descRutaSeg;
    }

    public void setDescRutaSeg(String descRutaSeg) {
        this.descRutaSeg = descRutaSeg;
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

    public Short getFlagEstaSeg() {
        return flagEstaSeg;
    }

    public void setFlagEstaSeg(Short flagEstaSeg) {
        this.flagEstaSeg = flagEstaSeg;
    }

    public MaeEmpresa getIdenEmprEmp() {
        return idenEmprEmp;
    }

    public void setIdenEmprEmp(MaeEmpresa idenEmprEmp) {
        this.idenEmprEmp = idenEmprEmp;
    }

    @XmlTransient
    public List<MaeSeguroRango> getMaeSeguroRangoList() {
        return maeSeguroRangoList;
    }

    public void setMaeSeguroRangoList(List<MaeSeguroRango> maeSeguroRangoList) {
        this.maeSeguroRangoList = maeSeguroRangoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenSeguSeg != null ? idenSeguSeg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeSeguro)) {
            return false;
        }
        MaeSeguro other = (MaeSeguro) object;
        if ((this.idenSeguSeg == null && other.idenSeguSeg != null) || (this.idenSeguSeg != null && !this.idenSeguSeg.equals(other.idenSeguSeg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeSeguro[ idenSeguSeg=" + idenSeguSeg + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdSimulaSeguro> getCrdSimulaSeguroList() {
        return crdSimulaSeguroList;
    }

    public void setCrdSimulaSeguroList(List<CrdSimulaSeguro> crdSimulaSeguroList) {
        this.crdSimulaSeguroList = crdSimulaSeguroList;
    }
    
}
