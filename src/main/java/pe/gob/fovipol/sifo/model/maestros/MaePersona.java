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
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaePersona.findAll", query = "SELECT m FROM MaePersona m"),
    @NamedQuery(name = "MaePersona.findByCodiPersPer", query = "SELECT m FROM MaePersona m WHERE m.codiPersPer = :codiPersPer"),
    @NamedQuery(name = "MaePersona.findByApelPatePer", query = "SELECT m FROM MaePersona m WHERE m.apelPatePer = :apelPatePer"),
    @NamedQuery(name = "MaePersona.findByApelMatePer", query = "SELECT m FROM MaePersona m WHERE m.apelMatePer = :apelMatePer"),
    @NamedQuery(name = "MaePersona.findByNombPersPer", query = "SELECT m FROM MaePersona m WHERE m.nombPersPer = :nombPersPer"),
    @NamedQuery(name = "MaePersona.findByNombCompPer", query = "SELECT m FROM MaePersona m WHERE m.nombCompPer = :nombCompPer"),
    @NamedQuery(name = "MaePersona.findByTipoIdenPer", query = "SELECT m FROM MaePersona m WHERE m.tipoIdenPer = :tipoIdenPer"),
    @NamedQuery(name = "MaePersona.findByNumeIdenPer", query = "SELECT m FROM MaePersona m WHERE m.numeIdenPer = :numeIdenPer"),
    @NamedQuery(name = "MaePersona.findByDirePersPer", query = "SELECT m FROM MaePersona m WHERE m.direPersPer = :direPersPer"),
    @NamedQuery(name = "MaePersona.findByEstaCiviPer", query = "SELECT m FROM MaePersona m WHERE m.estaCiviPer = :estaCiviPer"),
    @NamedQuery(name = "MaePersona.findBySexoPersPer", query = "SELECT m FROM MaePersona m WHERE m.sexoPersPer = :sexoPersPer"),
    @NamedQuery(name = "MaePersona.findByFechNaciPer", query = "SELECT m FROM MaePersona m WHERE m.fechNaciPer = :fechNaciPer"),
    @NamedQuery(name = "MaePersona.findByNumeTelcPer", query = "SELECT m FROM MaePersona m WHERE m.numeTelcPer = :numeTelcPer"),
    @NamedQuery(name = "MaePersona.findByNumeTeltPer", query = "SELECT m FROM MaePersona m WHERE m.numeTeltPer = :numeTeltPer"),
    @NamedQuery(name = "MaePersona.findByCorrElecPer", query = "SELECT m FROM MaePersona m WHERE m.corrElecPer = :corrElecPer"),
    @NamedQuery(name = "MaePersona.findByNumeCeluPer", query = "SELECT m FROM MaePersona m WHERE m.numeCeluPer = :numeCeluPer"),
    @NamedQuery(name = "MaePersona.findByNumeCel2Per", query = "SELECT m FROM MaePersona m WHERE m.numeCel2Per = :numeCel2Per"),
    @NamedQuery(name = "MaePersona.findByFechFallPer", query = "SELECT m FROM MaePersona m WHERE m.fechFallPer = :fechFallPer"),
    @NamedQuery(name = "MaePersona.findByTipoProfPer", query = "SELECT m FROM MaePersona m WHERE m.tipoProfPer = :tipoProfPer"),
    @NamedQuery(name = "MaePersona.findByRefeUbigPer", query = "SELECT m FROM MaePersona m WHERE m.refeUbigPer = :refeUbigPer"),
    @NamedQuery(name = "MaePersona.findByMediTallPer", query = "SELECT m FROM MaePersona m WHERE m.mediTallPer = :mediTallPer"),
    @NamedQuery(name = "MaePersona.findByRutaFotoPer", query = "SELECT m FROM MaePersona m WHERE m.rutaFotoPer = :rutaFotoPer"),
    @NamedQuery(name = "MaePersona.findByFlagBloqPer", query = "SELECT m FROM MaePersona m WHERE m.flagBloqPer = :flagBloqPer"),
    @NamedQuery(name = "MaePersona.findByMotiBloqPer", query = "SELECT m FROM MaePersona m WHERE m.motiBloqPer = :motiBloqPer"),
    @NamedQuery(name = "MaePersona.findByFlagHomoPer", query = "SELECT m FROM MaePersona m WHERE m.flagHomoPer = :flagHomoPer"),
    @NamedQuery(name = "MaePersona.findByDocuHomoPer", query = "SELECT m FROM MaePersona m WHERE m.docuHomoPer = :docuHomoPer"),
    @NamedQuery(name = "MaePersona.findByGrpoSangPer", query = "SELECT m FROM MaePersona m WHERE m.grpoSangPer = :grpoSangPer"),
    @NamedQuery(name = "MaePersona.findByGradParePer", query = "SELECT m FROM MaePersona m WHERE m.gradParePer = :gradParePer"),
    @NamedQuery(name = "MaePersona.findByUsuaCreaAud", query = "SELECT m FROM MaePersona m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaePersona.findByFechCreaAud", query = "SELECT m FROM MaePersona m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaePersona.findByUsuaModiAud", query = "SELECT m FROM MaePersona m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaePersona.findByFechModiAud", query = "SELECT m FROM MaePersona m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaePersona.findByNombEquiAud", query = "SELECT m FROM MaePersona m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaePersona.findByNombSopeAud", query = "SELECT m FROM MaePersona m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaePersona.findByFlagEstaPer", query = "SELECT m FROM MaePersona m WHERE m.flagEstaPer = :flagEstaPer")})
public class MaePersona implements Serializable {
    @Column(name = "TIPO_IDEN_PER")
    private BigDecimal tipoIdenPer;    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PERS_PER")
    private BigDecimal codiPersPer;
    @Size(max = 40)
    @Column(name = "APEL_PATE_PER")
    private String apelPatePer;
    @Size(max = 40)
    @Column(name = "APEL_MATE_PER")
    private String apelMatePer;
    @Size(max = 40)
    @Column(name = "NOMB_PERS_PER")
    private String nombPersPer;
    @Size(max = 120)
    @Column(name = "NOMB_COMP_PER")
    private String nombCompPer;
    @Size(max = 15)
    @Column(name = "NUME_IDEN_PER")
    @Pattern(regexp = "[0-9]*")
    private String numeIdenPer;
    @Size(max = 120)
    @Column(name = "DIRE_PERS_PER")
    private String direPersPer;
    @Column(name = "ESTA_CIVI_PER")
    private Integer estaCiviPer;
    @Column(name = "SEXO_PERS_PER")
    private Integer sexoPersPer;
    @Column(name = "FECH_NACI_PER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechNaciPer;
    @Size(max = 20)
    @Column(name = "NUME_TELC_PER")
    @Pattern(regexp="[0-9\\- ]*")
    private String numeTelcPer;
    @Size(max = 20)
    @Column(name = "NUME_TELT_PER")
    @Pattern(regexp="[0-9\\- ]*")
    private String numeTeltPer;
    @Size(max = 50)
    @Column(name = "CORR_ELEC_PER")
    @Pattern(regexp="^$|[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo Invalido")
    private String corrElecPer;
    @Size(max = 20)
    @Column(name = "NUME_CELU_PER")
    private String numeCeluPer;
    @Size(max = 20)
    @Column(name = "NUME_CEL2_PER")
    private String numeCel2Per;
    @Column(name = "FECH_FALL_PER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechFallPer;
    @Column(name = "TIPO_PROF_PER")
    private Integer tipoProfPer;
    @Size(max = 6)
    @Column(name = "REFE_UBIG_PER")
    private String refeUbigPer;
    @Column(name = "MEDI_TALL_PER")
    private BigDecimal mediTallPer;
    @Size(max = 300)
    @Column(name = "RUTA_FOTO_PER")
    private String rutaFotoPer;
    @Column(name = "FLAG_BLOQ_PER")
    private Short flagBloqPer;
    @Column(name = "MOTI_BLOQ_PER")
    private Integer motiBloqPer;
    @Column(name = "FLAG_HOMO_PER")
    private Short flagHomoPer;
    @Size(max = 15)
    @Column(name = "DOCU_HOMO_PER")
    private String docuHomoPer;
    @Size(max = 10)
    @Column(name = "GRPO_SANG_PER")
    private String grpoSangPer;
    @Column(name = "GRAD_PARE_PER")
    private Integer gradParePer;
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
    @Column(name = "FLAG_ESTA_PER")
    private Short flagEstaPer;
    @OneToMany(mappedBy = "codiPerpPer")
    private List<MaePersona> maePersonaList;
    @JoinColumn(name = "CODI_PERP_PER", referencedColumnName = "IDEN_PERS_PER")
    @ManyToOne
    private MaePersona codiPerpPer;
    @JoinColumn(name = "UBIG_NACI_PER", referencedColumnName = "IDEN_UBIG_UBI")
    @ManyToOne
    private MaeUbigeo ubigNaciPer;
    @JoinColumn(name = "UBIG_RESI_PER", referencedColumnName = "IDEN_UBIG_UBI")
    @ManyToOne
    private MaeUbigeo ubigResiPer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "maePersona")
    private MaeSocio maeSocio;

    public MaePersona() {
    }

    public MaePersona(BigDecimal codiPersPer) {
        this.codiPersPer = codiPersPer;
    }

    public BigDecimal getCodiPersPer() {
        return codiPersPer;
    }

    public void setCodiPersPer(BigDecimal codiPersPer) {
        this.codiPersPer = codiPersPer;
    }

    public String getApelPatePer() {
        return apelPatePer;
    }

    public void setApelPatePer(String apelPatePer) {
        this.apelPatePer = apelPatePer;
    }

    public String getApelMatePer() {
        return apelMatePer;
    }

    public void setApelMatePer(String apelMatePer) {
        this.apelMatePer = apelMatePer;
    }

    public String getNombPersPer() {
        return nombPersPer;
    }

    public void setNombPersPer(String nombPersPer) {
        this.nombPersPer = nombPersPer;
    }

    public String getNombCompPer() {
        return nombCompPer;
    }

    public void setNombCompPer(String nombCompPer) {
        this.nombCompPer = nombCompPer;
    }

    public BigDecimal getTipoIdenPer() {
        return tipoIdenPer;
    }

    public void setTipoIdenPer(BigDecimal tipoIdenPer) {
        this.tipoIdenPer = tipoIdenPer;
    }

    public String getNumeIdenPer() {
        return numeIdenPer;
    }

    public void setNumeIdenPer(String numeIdenPer) {
        this.numeIdenPer = numeIdenPer;
    }

    public String getDirePersPer() {
        return direPersPer;
    }

    public void setDirePersPer(String direPersPer) {
        this.direPersPer = direPersPer;
    }

    public Integer getEstaCiviPer() {
        return estaCiviPer;
    }

    public void setEstaCiviPer(Integer estaCiviPer) {
        this.estaCiviPer = estaCiviPer;
    }

    public Integer getSexoPersPer() {
        return sexoPersPer;
    }

    public void setSexoPersPer(Integer sexoPersPer) {
        this.sexoPersPer = sexoPersPer;
    }

    public Date getFechNaciPer() {
        return fechNaciPer;
    }

    public void setFechNaciPer(Date fechNaciPer) {
        this.fechNaciPer = fechNaciPer;
    }

    public String getNumeTelcPer() {
        return numeTelcPer;
    }

    public void setNumeTelcPer(String numeTelcPer) {
        this.numeTelcPer = numeTelcPer;
    }

    public String getNumeTeltPer() {
        return numeTeltPer;
    }

    public void setNumeTeltPer(String numeTeltPer) {
        this.numeTeltPer = numeTeltPer;
    }

    public String getCorrElecPer() {
        return corrElecPer;
    }

    public void setCorrElecPer(String corrElecPer) {
        this.corrElecPer = corrElecPer;
    }

    public String getNumeCeluPer() {
        return numeCeluPer;
    }

    public void setNumeCeluPer(String numeCeluPer) {
        this.numeCeluPer = numeCeluPer;
    }

    public String getNumeCel2Per() {
        return numeCel2Per;
    }

    public void setNumeCel2Per(String numeCel2Per) {
        this.numeCel2Per = numeCel2Per;
    }

    public Date getFechFallPer() {
        return fechFallPer;
    }

    public void setFechFallPer(Date fechFallPer) {
        this.fechFallPer = fechFallPer;
    }

    public Integer getTipoProfPer() {
        return tipoProfPer;
    }

    public void setTipoProfPer(Integer tipoProfPer) {
        this.tipoProfPer = tipoProfPer;
    }

    public String getRefeUbigPer() {
        return refeUbigPer;
    }

    public void setRefeUbigPer(String refeUbigPer) {
        this.refeUbigPer = refeUbigPer;
    }

    public BigDecimal getMediTallPer() {
        return mediTallPer;
    }

    public void setMediTallPer(BigDecimal mediTallPer) {
        this.mediTallPer = mediTallPer;
    }

    public String getRutaFotoPer() {
        return rutaFotoPer;
    }

    public void setRutaFotoPer(String rutaFotoPer) {
        this.rutaFotoPer = rutaFotoPer;
    }

    public Short getFlagBloqPer() {
        return flagBloqPer;
    }

    public void setFlagBloqPer(Short flagBloqPer) {
        this.flagBloqPer = flagBloqPer;
    }

    public Integer getMotiBloqPer() {
        return motiBloqPer;
    }

    public void setMotiBloqPer(Integer motiBloqPer) {
        this.motiBloqPer = motiBloqPer;
    }

    public Short getFlagHomoPer() {
        return flagHomoPer;
    }

    public void setFlagHomoPer(Short flagHomoPer) {
        this.flagHomoPer = flagHomoPer;
    }

    public String getDocuHomoPer() {
        return docuHomoPer;
    }

    public void setDocuHomoPer(String docuHomoPer) {
        this.docuHomoPer = docuHomoPer;
    }

    public String getGrpoSangPer() {
        return grpoSangPer;
    }

    public void setGrpoSangPer(String grpoSangPer) {
        this.grpoSangPer = grpoSangPer;
    }

    public Integer getGradParePer() {
        return gradParePer;
    }

    public void setGradParePer(Integer gradParePer) {
        this.gradParePer = gradParePer;
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

    public Short getFlagEstaPer() {
        return flagEstaPer;
    }

    public void setFlagEstaPer(Short flagEstaPer) {
        this.flagEstaPer = flagEstaPer;
    }

    @XmlTransient
    public List<MaePersona> getMaePersonaList() {
        return maePersonaList;
    }

    public void setMaePersonaList(List<MaePersona> maePersonaList) {
        this.maePersonaList = maePersonaList;
    }

    public MaePersona getCodiPerpPer() {
        return codiPerpPer;
    }

    public void setCodiPerpPer(MaePersona codiPerpPer) {
        this.codiPerpPer = codiPerpPer;
    }

    public MaeUbigeo getUbigNaciPer() {
        return ubigNaciPer;
    }

    public void setUbigNaciPer(MaeUbigeo ubigNaciPer) {
        this.ubigNaciPer = ubigNaciPer;
    }

    public MaeUbigeo getUbigResiPer() {
        return ubigResiPer;
    }

    public void setUbigResiPer(MaeUbigeo ubigResiPer) {
        this.ubigResiPer = ubigResiPer;
    }

    public MaeSocio getMaeSocio() {
        return maeSocio;
    }

    public void setMaeSocio(MaeSocio maeSocio) {
        this.maeSocio = maeSocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiPersPer != null ? codiPersPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaePersona)) {
            return false;
        }
        MaePersona other = (MaePersona) object;
        if ((this.codiPersPer == null && other.codiPersPer != null) || (this.codiPersPer != null && !this.codiPersPer.equals(other.codiPersPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.model.MaePersona[ codiPersPer=" + codiPersPer + " ]";
    }

}
