/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import pe.gob.fovipol.sifo.listener.AuditListener;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_PROCESOESTADO")
@XmlRootElement
@EntityListeners(value = AuditListener.class)
@NamedQueries({
    @NamedQuery(name = "MaeProcesoestado.findAll", query = "SELECT m FROM MaeProcesoestado m"),
    @NamedQuery(name = "MaeProcesoestado.findByIdenProcPrc", query = "SELECT m FROM MaeProcesoestado m WHERE m.maeProcesoestadoPK.idenProcPrc = :idenProcPrc"),
    @NamedQuery(name = "MaeProcesoestado.findBySecuEstaPre", query = "SELECT m FROM MaeProcesoestado m WHERE m.maeProcesoestadoPK.secuEstaPre = :secuEstaPre"),
    @NamedQuery(name = "MaeProcesoestado.findByNombEstaPre", query = "SELECT m FROM MaeProcesoestado m WHERE m.nombEstaPre = :nombEstaPre"),
    @NamedQuery(name = "MaeProcesoestado.findByRutaIconPre", query = "SELECT m FROM MaeProcesoestado m WHERE m.rutaIconPre = :rutaIconPre"),
    @NamedQuery(name = "MaeProcesoestado.findByUsuaCreaAud", query = "SELECT m FROM MaeProcesoestado m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeProcesoestado.findByFechCreaAud", query = "SELECT m FROM MaeProcesoestado m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeProcesoestado.findByUsuaModiAud", query = "SELECT m FROM MaeProcesoestado m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeProcesoestado.findByFechModiAud", query = "SELECT m FROM MaeProcesoestado m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeProcesoestado.findByNombEquiAud", query = "SELECT m FROM MaeProcesoestado m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeProcesoestado.findByNombSopeAud", query = "SELECT m FROM MaeProcesoestado m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeProcesoestado.findByFlagEstaPrc", query = "SELECT m FROM MaeProcesoestado m WHERE m.flagEstaPrc = :flagEstaPrc")})
public class MaeProcesoestado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaeProcesoestadoPK maeProcesoestadoPK;
    @Size(max = 50)
    @Column(name = "NOMB_ESTA_PRE")
    private String nombEstaPre;
    @Size(max = 200)
    @Column(name = "RUTA_ICON_PRE")
    private String rutaIconPre;
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
    @Column(name = "FLAG_ESTA_PRC")
    private short flagEstaPrc;
    @JoinColumn(name = "IDEN_PROC_PRC", referencedColumnName = "IDEN_PROC_PRC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MaeProceso maeProceso;

    public MaeProcesoestado() {
    }

    public MaeProcesoestado(MaeProcesoestadoPK maeProcesoestadoPK) {
        this.maeProcesoestadoPK = maeProcesoestadoPK;
    }

    public MaeProcesoestado(MaeProcesoestadoPK maeProcesoestadoPK, short flagEstaPrc) {
        this.maeProcesoestadoPK = maeProcesoestadoPK;
        this.flagEstaPrc = flagEstaPrc;
    }

    public MaeProcesoestado(BigInteger idenProcPrc, int secuEstaPre) {
        this.maeProcesoestadoPK = new MaeProcesoestadoPK(idenProcPrc, secuEstaPre);
    }

    public MaeProcesoestadoPK getMaeProcesoestadoPK() {
        return maeProcesoestadoPK;
    }

    public void setMaeProcesoestadoPK(MaeProcesoestadoPK maeProcesoestadoPK) {
        this.maeProcesoestadoPK = maeProcesoestadoPK;
    }

    public String getNombEstaPre() {
        return nombEstaPre;
    }

    public void setNombEstaPre(String nombEstaPre) {
        this.nombEstaPre = nombEstaPre;
    }

    public String getRutaIconPre() {
        return rutaIconPre;
    }

    public void setRutaIconPre(String rutaIconPre) {
        this.rutaIconPre = rutaIconPre;
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

    public short getFlagEstaPrc() {
        return flagEstaPrc;
    }

    public void setFlagEstaPrc(short flagEstaPrc) {
        this.flagEstaPrc = flagEstaPrc;
    }

    public MaeProceso getMaeProceso() {
        return maeProceso;
    }

    public void setMaeProceso(MaeProceso maeProceso) {
        this.maeProceso = maeProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maeProcesoestadoPK != null ? maeProcesoestadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeProcesoestado)) {
            return false;
        }
        MaeProcesoestado other = (MaeProcesoestado) object;
        if ((this.maeProcesoestadoPK == null && other.maeProcesoestadoPK != null) || (this.maeProcesoestadoPK != null && !this.maeProcesoestadoPK.equals(other.maeProcesoestadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeProcesoestado[ maeProcesoestadoPK=" + maeProcesoestadoPK + " ]";
    }
    
}
