/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.tramite;

import java.io.Serializable;
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
import pe.gob.fovipol.sifo.model.maestros.MaeArea;
import pe.gob.fovipol.sifo.model.maestros.MaeProceso;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "TRM_MOVIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrmMovimiento.findAll", query = "SELECT t FROM TrmMovimiento t"),
    @NamedQuery(name = "TrmMovimiento.findByIdenExpeTrm", query = "SELECT t FROM TrmMovimiento t WHERE t.trmMovimientoPK.idenExpeTrm = :idenExpeTrm"),
    @NamedQuery(name = "TrmMovimiento.findBySecuMoviMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.trmMovimientoPK.secuMoviMvm = :secuMoviMvm"),
    @NamedQuery(name = "TrmMovimiento.findByUsuaEnviMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.usuaEnviMvm = :usuaEnviMvm"),
    @NamedQuery(name = "TrmMovimiento.findByFechEnviMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.fechEnviMvm = :fechEnviMvm"),
    @NamedQuery(name = "TrmMovimiento.findByUsuaReceMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.usuaReceMvm = :usuaReceMvm"),
    @NamedQuery(name = "TrmMovimiento.findByFechReceMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.fechReceMvm = :fechReceMvm"),
    @NamedQuery(name = "TrmMovimiento.findByDescInfoMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.descInfoMvm = :descInfoMvm"),
    @NamedQuery(name = "TrmMovimiento.findByUsuaAsigMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.usuaAsigMvm = :usuaAsigMvm"),
    @NamedQuery(name = "TrmMovimiento.findByUsuaTrabMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.usuaTrabMvm = :usuaTrabMvm"),
    @NamedQuery(name = "TrmMovimiento.findByFechAsigMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.fechAsigMvm = :fechAsigMvm"),
    @NamedQuery(name = "TrmMovimiento.findByNumeDiasMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.numeDiasMvm = :numeDiasMvm"),
    @NamedQuery(name = "TrmMovimiento.findByFechVencMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.fechVencMvm = :fechVencMvm"),
    @NamedQuery(name = "TrmMovimiento.findByUsuaCreaAud", query = "SELECT t FROM TrmMovimiento t WHERE t.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "TrmMovimiento.findByFechCreaAud", query = "SELECT t FROM TrmMovimiento t WHERE t.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "TrmMovimiento.findByUsuaModiAud", query = "SELECT t FROM TrmMovimiento t WHERE t.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "TrmMovimiento.findByFechModiAud", query = "SELECT t FROM TrmMovimiento t WHERE t.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "TrmMovimiento.findByNombEquiAud", query = "SELECT t FROM TrmMovimiento t WHERE t.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "TrmMovimiento.findByNombSopeAud", query = "SELECT t FROM TrmMovimiento t WHERE t.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "TrmMovimiento.findByFlagSituMvm", query = "SELECT t FROM TrmMovimiento t WHERE t.flagSituMvm = :flagSituMvm")})
public class TrmMovimiento implements Serializable {
    @JoinColumn(name = "AREA_ORIG_MVM", referencedColumnName = "IDEN_AREA_ARE")
    @ManyToOne
    private MaeArea areaOrigMvm;
    @JoinColumn(name = "AREA_DEST_MVM", referencedColumnName = "IDEN_AREA_ARE")
    @ManyToOne
    private MaeArea areaDestMvm;
    @JoinColumn(name = "IDEN_PROC_PRC", referencedColumnName = "IDEN_PROC_PRC")
    @ManyToOne
    private MaeProceso idenProcPrc;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrmMovimientoPK trmMovimientoPK;
    @Size(max = 15)
    @Column(name = "USUA_ENVI_MVM")
    private String usuaEnviMvm;
    @Column(name = "FECH_ENVI_MVM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEnviMvm;
    @Size(max = 15)
    @Column(name = "USUA_RECE_MVM")
    private String usuaReceMvm;
    @Column(name = "FECH_RECE_MVM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechReceMvm;
    @Size(max = 300)
    @Column(name = "DESC_INFO_MVM")
    private String descInfoMvm;
    @Size(max = 15)
    @Column(name = "USUA_ASIG_MVM")
    private String usuaAsigMvm;
    @Size(max = 15)
    @Column(name = "USUA_TRAB_MVM")
    private String usuaTrabMvm;
    @Column(name = "FECH_ASIG_MVM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechAsigMvm;
    @Column(name = "NUME_DIAS_MVM")
    private BigInteger numeDiasMvm;
    @Column(name = "FECH_VENC_MVM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechVencMvm;
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
    @Column(name = "FLAG_SITU_MVM")
    private Short flagSituMvm;
    @JoinColumn(name = "IDEN_EXPE_TRM", referencedColumnName = "IDEN_EXPE_TRM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TrmTramite trmTramite;

    public TrmMovimiento() {
    }

    public TrmMovimiento(TrmMovimientoPK trmMovimientoPK) {
        this.trmMovimientoPK = trmMovimientoPK;
    }

    public TrmMovimiento(BigInteger idenExpeTrm, int secuMoviMvm) {
        this.trmMovimientoPK = new TrmMovimientoPK(idenExpeTrm, secuMoviMvm);
    }

    public TrmMovimientoPK getTrmMovimientoPK() {
        return trmMovimientoPK;
    }

    public void setTrmMovimientoPK(TrmMovimientoPK trmMovimientoPK) {
        this.trmMovimientoPK = trmMovimientoPK;
    }

    public String getUsuaEnviMvm() {
        return usuaEnviMvm;
    }

    public void setUsuaEnviMvm(String usuaEnviMvm) {
        this.usuaEnviMvm = usuaEnviMvm;
    }

    public Date getFechEnviMvm() {
        return fechEnviMvm;
    }

    public void setFechEnviMvm(Date fechEnviMvm) {
        this.fechEnviMvm = fechEnviMvm;
    }

    public String getUsuaReceMvm() {
        return usuaReceMvm;
    }

    public void setUsuaReceMvm(String usuaReceMvm) {
        this.usuaReceMvm = usuaReceMvm;
    }

    public Date getFechReceMvm() {
        return fechReceMvm;
    }

    public void setFechReceMvm(Date fechReceMvm) {
        this.fechReceMvm = fechReceMvm;
    }

    public String getDescInfoMvm() {
        return descInfoMvm;
    }

    public void setDescInfoMvm(String descInfoMvm) {
        this.descInfoMvm = descInfoMvm;
    }

    public String getUsuaAsigMvm() {
        return usuaAsigMvm;
    }

    public void setUsuaAsigMvm(String usuaAsigMvm) {
        this.usuaAsigMvm = usuaAsigMvm;
    }

    public String getUsuaTrabMvm() {
        return usuaTrabMvm;
    }

    public void setUsuaTrabMvm(String usuaTrabMvm) {
        this.usuaTrabMvm = usuaTrabMvm;
    }

    public Date getFechAsigMvm() {
        return fechAsigMvm;
    }

    public void setFechAsigMvm(Date fechAsigMvm) {
        this.fechAsigMvm = fechAsigMvm;
    }

    public BigInteger getNumeDiasMvm() {
        return numeDiasMvm;
    }

    public void setNumeDiasMvm(BigInteger numeDiasMvm) {
        this.numeDiasMvm = numeDiasMvm;
    }

    public Date getFechVencMvm() {
        return fechVencMvm;
    }

    public void setFechVencMvm(Date fechVencMvm) {
        this.fechVencMvm = fechVencMvm;
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

    public Short getFlagSituMvm() {
        return flagSituMvm;
    }

    public void setFlagSituMvm(Short flagSituMvm) {
        this.flagSituMvm = flagSituMvm;
    }

    public TrmTramite getTrmTramite() {
        return trmTramite;
    }

    public void setTrmTramite(TrmTramite trmTramite) {
        this.trmTramite = trmTramite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trmMovimientoPK != null ? trmMovimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmMovimiento)) {
            return false;
        }
        TrmMovimiento other = (TrmMovimiento) object;
        if ((this.trmMovimientoPK == null && other.trmMovimientoPK != null) || (this.trmMovimientoPK != null && !this.trmMovimientoPK.equals(other.trmMovimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmMovimiento[ trmMovimientoPK=" + trmMovimientoPK + " ]";
    }

    public MaeArea getAreaOrigMvm() {
        return areaOrigMvm;
    }

    public void setAreaOrigMvm(MaeArea areaOrigMvm) {
        this.areaOrigMvm = areaOrigMvm;
    }

    public MaeArea getAreaDestMvm() {
        return areaDestMvm;
    }

    public void setAreaDestMvm(MaeArea areaDestMvm) {
        this.areaDestMvm = areaDestMvm;
    }

    public MaeProceso getIdenProcPrc() {
        return idenProcPrc;
    }

    public void setIdenProcPrc(MaeProceso idenProcPrc) {
        this.idenProcPrc = idenProcPrc;
    }
    
}
