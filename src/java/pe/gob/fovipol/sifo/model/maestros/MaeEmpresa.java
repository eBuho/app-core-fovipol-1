/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_EMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeEmpresa.findAll", query = "SELECT m FROM MaeEmpresa m"),
    @NamedQuery(name = "MaeEmpresa.findByCodiEmprEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.codiEmprEmp = :codiEmprEmp"),
    @NamedQuery(name = "MaeEmpresa.findByRazoSociEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.razoSociEmp = :razoSociEmp"),
    @NamedQuery(name = "MaeEmpresa.findByDescDireEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.descDireEmp = :descDireEmp"),
    @NamedQuery(name = "MaeEmpresa.findByNombComeEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.nombComeEmp = :nombComeEmp"),
    @NamedQuery(name = "MaeEmpresa.findByNumeRucEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.numeRucEmp = :numeRucEmp"),
    @NamedQuery(name = "MaeEmpresa.findByReprLegaEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.reprLegaEmp = :reprLegaEmp"),
    @NamedQuery(name = "MaeEmpresa.findByCuenDetrEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.cuenDetrEmp = :cuenDetrEmp"),
    @NamedQuery(name = "MaeEmpresa.findByNumeTeleEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.numeTeleEmp = :numeTeleEmp"),
    @NamedQuery(name = "MaeEmpresa.findByNumeTel2Emp", query = "SELECT m FROM MaeEmpresa m WHERE m.numeTel2Emp = :numeTel2Emp"),
    @NamedQuery(name = "MaeEmpresa.findByPagiWebEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.pagiWebEmp = :pagiWebEmp"),
    @NamedQuery(name = "MaeEmpresa.findByCorrElecEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.corrElecEmp = :corrElecEmp"),
    @NamedQuery(name = "MaeEmpresa.findByFlagReteEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.flagReteEmp = :flagReteEmp"),
    @NamedQuery(name = "MaeEmpresa.findByUsuaCreaAud", query = "SELECT m FROM MaeEmpresa m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeEmpresa.findByFechCreaAud", query = "SELECT m FROM MaeEmpresa m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeEmpresa.findByUsuaModiAud", query = "SELECT m FROM MaeEmpresa m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeEmpresa.findByFechModiAud", query = "SELECT m FROM MaeEmpresa m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeEmpresa.findByNombEquiAud", query = "SELECT m FROM MaeEmpresa m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeEmpresa.findByNombSopeAud", query = "SELECT m FROM MaeEmpresa m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeEmpresa.findByFlagEstaEmp", query = "SELECT m FROM MaeEmpresa m WHERE m.flagEstaEmp = :flagEstaEmp")})
public class MaeEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODI_EMPR_EMP")
    private BigDecimal codiEmprEmp;
    @Size(max = 120)
    @Column(name = "RAZO_SOCI_EMP")
    private String razoSociEmp;
    @Size(max = 120)
    @Column(name = "DESC_DIRE_EMP")
    private String descDireEmp;
    @Size(max = 120)
    @Column(name = "NOMB_COME_EMP")
    private String nombComeEmp;
    @Size(max = 11,min = 11)
    @Column(name = "NUME_RUC_EMP")
    @Pattern(regexp = "[0-9]*")
    private String numeRucEmp;
    @Size(max = 120)
    @Column(name = "REPR_LEGA_EMP")
    private String reprLegaEmp;
    @Size(max = 50)
    @Column(name = "CUEN_DETR_EMP")
    private String cuenDetrEmp;
    @Size(max = 20)    
    @Column(name = "NUME_TELE_EMP")
    @Pattern(regexp="[0-9\\- ]*")
    private String numeTeleEmp;
    @Size(max = 20)
    @Column(name = "NUME_TEL2_EMP")
    @Pattern(regexp="[0-9\\- ]*")
    private String numeTel2Emp;
    @Size(max = 120)
    @Column(name = "PAGI_WEB_EMP")
    private String pagiWebEmp;
    @Size(max = 50)
    @Column(name = "CORR_ELEC_EMP")
    @Pattern(regexp="^$|[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo Invalido")
    private String corrElecEmp;
    @Column(name = "FLAG_RETE_EMP")
    private Short flagReteEmp;
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
    @Column(name = "FLAG_ESTA_EMP")
    private Short flagEstaEmp;

    public MaeEmpresa() {
    }

    public MaeEmpresa(BigDecimal codiEmprEmp) {
        this.codiEmprEmp = codiEmprEmp;
    }

    public BigDecimal getCodiEmprEmp() {
        return codiEmprEmp;
    }

    public void setCodiEmprEmp(BigDecimal codiEmprEmp) {
        this.codiEmprEmp = codiEmprEmp;
    }

    public String getRazoSociEmp() {
        return razoSociEmp;
    }

    public void setRazoSociEmp(String razoSociEmp) {
        this.razoSociEmp = razoSociEmp;
    }

    public String getDescDireEmp() {
        return descDireEmp;
    }

    public void setDescDireEmp(String descDireEmp) {
        this.descDireEmp = descDireEmp;
    }

    public String getNombComeEmp() {
        return nombComeEmp;
    }

    public void setNombComeEmp(String nombComeEmp) {
        this.nombComeEmp = nombComeEmp;
    }

    public String getNumeRucEmp() {
        return numeRucEmp;
    }

    public void setNumeRucEmp(String numeRucEmp) {
        this.numeRucEmp = numeRucEmp;
    }

    public String getReprLegaEmp() {
        return reprLegaEmp;
    }

    public void setReprLegaEmp(String reprLegaEmp) {
        this.reprLegaEmp = reprLegaEmp;
    }

    public String getCuenDetrEmp() {
        return cuenDetrEmp;
    }

    public void setCuenDetrEmp(String cuenDetrEmp) {
        this.cuenDetrEmp = cuenDetrEmp;
    }

    public String getNumeTeleEmp() {
        return numeTeleEmp;
    }

    public void setNumeTeleEmp(String numeTeleEmp) {
        this.numeTeleEmp = numeTeleEmp;
    }

    public String getNumeTel2Emp() {
        return numeTel2Emp;
    }

    public void setNumeTel2Emp(String numeTel2Emp) {
        this.numeTel2Emp = numeTel2Emp;
    }

    public String getPagiWebEmp() {
        return pagiWebEmp;
    }

    public void setPagiWebEmp(String pagiWebEmp) {
        this.pagiWebEmp = pagiWebEmp;
    }

    public String getCorrElecEmp() {
        return corrElecEmp;
    }

    public void setCorrElecEmp(String corrElecEmp) {
        this.corrElecEmp = corrElecEmp;
    }

    public Short getFlagReteEmp() {
        return flagReteEmp;
    }

    public void setFlagReteEmp(Short flagReteEmp) {
        this.flagReteEmp = flagReteEmp;
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

    public Short getFlagEstaEmp() {
        return flagEstaEmp;
    }

    public void setFlagEstaEmp(Short flagEstaEmp) {
        this.flagEstaEmp = flagEstaEmp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEmprEmp != null ? codiEmprEmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeEmpresa)) {
            return false;
        }
        MaeEmpresa other = (MaeEmpresa) object;
        if ((this.codiEmprEmp == null && other.codiEmprEmp != null) || (this.codiEmprEmp != null && !this.codiEmprEmp.equals(other.codiEmprEmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.model.MaeEmpresa[ codiEmprEmp=" + codiEmprEmp + " ]";
    }
    
}
