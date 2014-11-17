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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeEmpleado.findAll", query = "SELECT m FROM MaeEmpleado m"),
    @NamedQuery(name = "MaeEmpleado.findByIdenPersPer", query = "SELECT m FROM MaeEmpleado m WHERE m.idenPersPer = :idenPersPer"),
    @NamedQuery(name = "MaeEmpleado.findByCodiEmplEmp", query = "SELECT m FROM MaeEmpleado m WHERE m.codiEmplEmp = :codiEmplEmp"),
    @NamedQuery(name = "MaeEmpleado.findByFechContEmp", query = "SELECT m FROM MaeEmpleado m WHERE m.fechContEmp = :fechContEmp"),
    @NamedQuery(name = "MaeEmpleado.findByCodiCargEmp", query = "SELECT m FROM MaeEmpleado m WHERE m.codiCargEmp = :codiCargEmp"),
    @NamedQuery(name = "MaeEmpleado.findByNumeCtacEmp", query = "SELECT m FROM MaeEmpleado m WHERE m.numeCtacEmp = :numeCtacEmp"),
    @NamedQuery(name = "MaeEmpleado.findByCodiEntiBan", query = "SELECT m FROM MaeEmpleado m WHERE m.codiEntiBan = :codiEntiBan"),
    @NamedQuery(name = "MaeEmpleado.findByImpoSuelEmp", query = "SELECT m FROM MaeEmpleado m WHERE m.impoSuelEmp = :impoSuelEmp"),
    @NamedQuery(name = "MaeEmpleado.findByUsuaCreaAud", query = "SELECT m FROM MaeEmpleado m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeEmpleado.findByFechCreaAud", query = "SELECT m FROM MaeEmpleado m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeEmpleado.findByUsuaModiAud", query = "SELECT m FROM MaeEmpleado m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeEmpleado.findByFechModiAud", query = "SELECT m FROM MaeEmpleado m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeEmpleado.findByNombEquiAud", query = "SELECT m FROM MaeEmpleado m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeEmpleado.findByNombSopeAud", query = "SELECT m FROM MaeEmpleado m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeEmpleado.findByFlagEstaEmp", query = "SELECT m FROM MaeEmpleado m WHERE m.flagEstaEmp = :flagEstaEmp")})
public class MaeEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PERS_PER")
    private BigDecimal idenPersPer;
    @Size(max = 20)
    @Column(name = "CODI_EMPL_EMP")
    private String codiEmplEmp;
    @Column(name = "FECH_CONT_EMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechContEmp;
    @Column(name = "CODI_CARG_EMP")
    private Integer codiCargEmp;
    @Size(max = 50)
    @Column(name = "NUME_CTAC_EMP")
    private String numeCtacEmp;
    @Column(name = "CODI_ENTI_BAN")
    private Integer codiEntiBan;
    @Column(name = "IMPO_SUEL_EMP")
    private BigDecimal impoSuelEmp;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "FLAG_ESTA_EMP")
    private short flagEstaEmp;
    @JoinColumn(name = "IDEN_PERS_PER", referencedColumnName = "IDEN_PERS_PER", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MaePersona maePersona;
    @OneToMany(mappedBy = "codiSupePer")
    private List<MaeEmpleado> maeEmpleadoList;
    @JoinColumn(name = "CODI_SUPE_PER", referencedColumnName = "IDEN_PERS_PER")
    @ManyToOne
    private MaeEmpleado codiSupePer;
    @JoinColumn(name = "IDEN_AREA_ARE", referencedColumnName = "IDEN_AREA_ARE")
    @ManyToOne
    private MaeArea idenAreaAre;

    public MaeEmpleado() {
    }

    public MaeEmpleado(BigDecimal idenPersPer) {
        this.idenPersPer = idenPersPer;
    }

    public MaeEmpleado(BigDecimal idenPersPer, short flagEstaEmp) {
        this.idenPersPer = idenPersPer;
        this.flagEstaEmp = flagEstaEmp;
    }

    public BigDecimal getIdenPersPer() {
        return idenPersPer;
    }

    public void setIdenPersPer(BigDecimal idenPersPer) {
        this.idenPersPer = idenPersPer;
    }

    public String getCodiEmplEmp() {
        return codiEmplEmp;
    }

    public void setCodiEmplEmp(String codiEmplEmp) {
        this.codiEmplEmp = codiEmplEmp;
    }

    public Date getFechContEmp() {
        return fechContEmp;
    }

    public void setFechContEmp(Date fechContEmp) {
        this.fechContEmp = fechContEmp;
    }

    public Integer getCodiCargEmp() {
        return codiCargEmp;
    }

    public void setCodiCargEmp(Integer codiCargEmp) {
        this.codiCargEmp = codiCargEmp;
    }

    public String getNumeCtacEmp() {
        return numeCtacEmp;
    }

    public void setNumeCtacEmp(String numeCtacEmp) {
        this.numeCtacEmp = numeCtacEmp;
    }

    public Integer getCodiEntiBan() {
        return codiEntiBan;
    }

    public void setCodiEntiBan(Integer codiEntiBan) {
        this.codiEntiBan = codiEntiBan;
    }

    public BigDecimal getImpoSuelEmp() {
        return impoSuelEmp;
    }

    public void setImpoSuelEmp(BigDecimal impoSuelEmp) {
        this.impoSuelEmp = impoSuelEmp;
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

    public short getFlagEstaEmp() {
        return flagEstaEmp;
    }

    public void setFlagEstaEmp(short flagEstaEmp) {
        this.flagEstaEmp = flagEstaEmp;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
    }

    @XmlTransient
    @JsonIgnore
    public List<MaeEmpleado> getMaeEmpleadoList() {
        return maeEmpleadoList;
    }

    public void setMaeEmpleadoList(List<MaeEmpleado> maeEmpleadoList) {
        this.maeEmpleadoList = maeEmpleadoList;
    }

    public MaeEmpleado getCodiSupePer() {
        return codiSupePer;
    }

    public void setCodiSupePer(MaeEmpleado codiSupePer) {
        this.codiSupePer = codiSupePer;
    }

    public MaeArea getIdenAreaAre() {
        return idenAreaAre;
    }

    public void setIdenAreaAre(MaeArea idenAreaAre) {
        this.idenAreaAre = idenAreaAre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenPersPer != null ? idenPersPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeEmpleado)) {
            return false;
        }
        MaeEmpleado other = (MaeEmpleado) object;
        if ((this.idenPersPer == null && other.idenPersPer != null) || (this.idenPersPer != null && !this.idenPersPer.equals(other.idenPersPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeEmpleado[ idenPersPer=" + idenPersPer + " ]";
    }
    
}
