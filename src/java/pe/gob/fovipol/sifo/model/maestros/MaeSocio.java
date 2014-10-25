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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "MAE_SOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeSocio.findAll", query = "SELECT m FROM MaeSocio m"),
    @NamedQuery(name = "MaeSocio.findByCodiPersPer", query = "SELECT m FROM MaeSocio m WHERE m.codiPersPer = :codiPersPer"),
    @NamedQuery(name = "MaeSocio.findByCodiMigrSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiMigrSoc = :codiMigrSoc"),
    @NamedQuery(name = "MaeSocio.findByTipoPoliSoc", query = "SELECT m FROM MaeSocio m WHERE m.tipoPoliSoc = :tipoPoliSoc"),
    @NamedQuery(name = "MaeSocio.findByCodiCcipSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiCcipSoc = :codiCcipSoc"),
    @NamedQuery(name = "MaeSocio.findByCodiCdfiSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiCdfiSoc = :codiCdfiSoc"),
    @NamedQuery(name = "MaeSocio.findByCodiCajaSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiCajaSoc = :codiCajaSoc"),
    @NamedQuery(name = "MaeSocio.findByCodiMpolSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiMpolSoc = :codiMpolSoc"),
    @NamedQuery(name = "MaeSocio.findByFechAfilSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechAfilSoc = :fechAfilSoc"),
    @NamedQuery(name = "MaeSocio.findByFechDesaSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechDesaSoc = :fechDesaSoc"),
    @NamedQuery(name = "MaeSocio.findByCodiGradSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiGradSoc = :codiGradSoc"),
    @NamedQuery(name = "MaeSocio.findByCodiEspeSoc", query = "SELECT m FROM MaeSocio m WHERE m.codiEspeSoc = :codiEspeSoc"),
    @NamedQuery(name = "MaeSocio.findByFechIescSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechIescSoc = :fechIescSoc"),
    @NamedQuery(name = "MaeSocio.findByFechEescSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechEescSoc = :fechEescSoc"),
    @NamedQuery(name = "MaeSocio.findByFechBajaSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechBajaSoc = :fechBajaSoc"),
    @NamedQuery(name = "MaeSocio.findByUnidTrabSoc", query = "SELECT m FROM MaeSocio m WHERE m.unidTrabSoc = :unidTrabSoc"),
    @NamedQuery(name = "MaeSocio.findByEntiPagoSoc", query = "SELECT m FROM MaeSocio m WHERE m.entiPagoSoc = :entiPagoSoc"),
    @NamedQuery(name = "MaeSocio.findByFechReinSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechReinSoc = :fechReinSoc"),
    @NamedQuery(name = "MaeSocio.findByFlagRenuSoc", query = "SELECT m FROM MaeSocio m WHERE m.flagRenuSoc = :flagRenuSoc"),
    @NamedQuery(name = "MaeSocio.findByFechRenuSoc", query = "SELECT m FROM MaeSocio m WHERE m.fechRenuSoc = :fechRenuSoc"),
    @NamedQuery(name = "MaeSocio.findByUsuaCreaAud", query = "SELECT m FROM MaeSocio m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeSocio.findByFechCreaAud", query = "SELECT m FROM MaeSocio m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeSocio.findByUsuaModiAud", query = "SELECT m FROM MaeSocio m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeSocio.findByFechModiAud", query = "SELECT m FROM MaeSocio m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeSocio.findByNombEquiAud", query = "SELECT m FROM MaeSocio m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeSocio.findByNombSopeAud", query = "SELECT m FROM MaeSocio m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeSocio.findByFlagEstaSoc", query = "SELECT m FROM MaeSocio m WHERE m.flagEstaSoc = :flagEstaSoc")})
public class MaeSocio implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODI_PERS_PER")
    private BigDecimal codiPersPer;
    @Size(max = 20)
    @Column(name = "CODI_MIGR_SOC")
    private String codiMigrSoc;
    @Column(name = "TIPO_POLI_SOC")
    private Integer tipoPoliSoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CODI_CCIP_SOC")
    private String codiCcipSoc;
    @Size(max = 14)
    @Column(name = "CODI_CDFI_SOC")
    private String codiCdfiSoc;
    @Size(max = 26)
    @Column(name = "CODI_CAJA_SOC")
    private String codiCajaSoc;
    @Size(max = 20)
    @Column(name = "CODI_MPOL_SOC")
    private String codiMpolSoc;
    @Column(name = "FECH_AFIL_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechAfilSoc;
    @Column(name = "FECH_DESA_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechDesaSoc;
    @Column(name = "CODI_GRAD_SOC")
    private Integer codiGradSoc;
    @Column(name = "CODI_ESPE_SOC")
    private Integer codiEspeSoc;
    @Column(name = "FECH_IESC_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechIescSoc;
    @Column(name = "FECH_EESC_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEescSoc;
    @Column(name = "FECH_BAJA_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechBajaSoc;
    @Size(max = 20)
    @Column(name = "UNID_TRAB_SOC")
    private String unidTrabSoc;
    @Column(name = "ENTI_PAGO_SOC")
    private Integer entiPagoSoc;
    @Column(name = "FECH_REIN_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechReinSoc;
    @Column(name = "FLAG_RENU_SOC")
    private Short flagRenuSoc;
    @Column(name = "FECH_RENU_SOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechRenuSoc;
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
    @Size(max = 20)
    @Column(name = "NOMB_SOPE_AUD")
    private String nombSopeAud;
    @Column(name = "FLAG_ESTA_SOC")
    private Short flagEstaSoc;
    @JoinColumn(name = "CODI_PERS_PER", referencedColumnName = "CODI_PERS_PER", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MaePersona maePersona;

    public MaeSocio() {
    }

    public MaeSocio(BigDecimal codiPersPer) {
        this.codiPersPer = codiPersPer;
    }

    public MaeSocio(BigDecimal codiPersPer, String codiCcipSoc) {
        this.codiPersPer = codiPersPer;
        this.codiCcipSoc = codiCcipSoc;
    }

    public BigDecimal getCodiPersPer() {
        return codiPersPer;
    }

    public void setCodiPersPer(BigDecimal codiPersPer) {
        this.codiPersPer = codiPersPer;
    }

    public String getCodiMigrSoc() {
        return codiMigrSoc;
    }

    public void setCodiMigrSoc(String codiMigrSoc) {
        this.codiMigrSoc = codiMigrSoc;
    }

    public Integer getTipoPoliSoc() {
        return tipoPoliSoc;
    }

    public void setTipoPoliSoc(Integer tipoPoliSoc) {
        this.tipoPoliSoc = tipoPoliSoc;
    }

    public String getCodiCcipSoc() {
        return codiCcipSoc;
    }

    public void setCodiCcipSoc(String codiCcipSoc) {
        this.codiCcipSoc = codiCcipSoc;
    }

    public String getCodiCdfiSoc() {
        return codiCdfiSoc;
    }

    public void setCodiCdfiSoc(String codiCdfiSoc) {
        this.codiCdfiSoc = codiCdfiSoc;
    }

    public String getCodiCajaSoc() {
        return codiCajaSoc;
    }

    public void setCodiCajaSoc(String codiCajaSoc) {
        this.codiCajaSoc = codiCajaSoc;
    }

    public String getCodiMpolSoc() {
        return codiMpolSoc;
    }

    public void setCodiMpolSoc(String codiMpolSoc) {
        this.codiMpolSoc = codiMpolSoc;
    }

    public Date getFechAfilSoc() {
        return fechAfilSoc;
    }

    public void setFechAfilSoc(Date fechAfilSoc) {
        this.fechAfilSoc = fechAfilSoc;
    }

    public Date getFechDesaSoc() {
        return fechDesaSoc;
    }

    public void setFechDesaSoc(Date fechDesaSoc) {
        this.fechDesaSoc = fechDesaSoc;
    }

    public Integer getCodiGradSoc() {
        return codiGradSoc;
    }

    public void setCodiGradSoc(Integer codiGradSoc) {
        this.codiGradSoc = codiGradSoc;
    }

    public Integer getCodiEspeSoc() {
        return codiEspeSoc;
    }

    public void setCodiEspeSoc(Integer codiEspeSoc) {
        this.codiEspeSoc = codiEspeSoc;
    }

    public Date getFechIescSoc() {
        return fechIescSoc;
    }

    public void setFechIescSoc(Date fechIescSoc) {
        this.fechIescSoc = fechIescSoc;
    }

    public Date getFechEescSoc() {
        return fechEescSoc;
    }

    public void setFechEescSoc(Date fechEescSoc) {
        this.fechEescSoc = fechEescSoc;
    }

    public Date getFechBajaSoc() {
        return fechBajaSoc;
    }

    public void setFechBajaSoc(Date fechBajaSoc) {
        this.fechBajaSoc = fechBajaSoc;
    }

    public String getUnidTrabSoc() {
        return unidTrabSoc;
    }

    public void setUnidTrabSoc(String unidTrabSoc) {
        this.unidTrabSoc = unidTrabSoc;
    }

    public Integer getEntiPagoSoc() {
        return entiPagoSoc;
    }

    public void setEntiPagoSoc(Integer entiPagoSoc) {
        this.entiPagoSoc = entiPagoSoc;
    }

    public Date getFechReinSoc() {
        return fechReinSoc;
    }

    public void setFechReinSoc(Date fechReinSoc) {
        this.fechReinSoc = fechReinSoc;
    }

    public Short getFlagRenuSoc() {
        return flagRenuSoc;
    }

    public void setFlagRenuSoc(Short flagRenuSoc) {
        this.flagRenuSoc = flagRenuSoc;
    }

    public Date getFechRenuSoc() {
        return fechRenuSoc;
    }

    public void setFechRenuSoc(Date fechRenuSoc) {
        this.fechRenuSoc = fechRenuSoc;
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

    public Short getFlagEstaSoc() {
        return flagEstaSoc;
    }

    public void setFlagEstaSoc(Short flagEstaSoc) {
        this.flagEstaSoc = flagEstaSoc;
    }

    public MaePersona getMaePersona() {
        return maePersona;
    }

    public void setMaePersona(MaePersona maePersona) {
        this.maePersona = maePersona;
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
        if (!(object instanceof MaeSocio)) {
            return false;
        }
        MaeSocio other = (MaeSocio) object;
        if ((this.codiPersPer == null && other.codiPersPer != null) || (this.codiPersPer != null && !this.codiPersPer.equals(other.codiPersPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.model.MaeSocio[ codiPersPer=" + codiPersPer + " ]";
    }
    
}
