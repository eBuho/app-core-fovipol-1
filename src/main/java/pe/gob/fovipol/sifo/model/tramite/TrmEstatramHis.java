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

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "TRM_ESTATRAM_HIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrmEstatramHis.findAll", query = "SELECT t FROM TrmEstatramHis t"),
    @NamedQuery(name = "TrmEstatramHis.findByIdenExpeTrm", query = "SELECT t FROM TrmEstatramHis t WHERE t.trmEstatramHisPK.idenExpeTrm = :idenExpeTrm"),
    @NamedQuery(name = "TrmEstatramHis.findBySecuEstaHis", query = "SELECT t FROM TrmEstatramHis t WHERE t.trmEstatramHisPK.secuEstaHis = :secuEstaHis"),
    @NamedQuery(name = "TrmEstatramHis.findByCodiEstaHis", query = "SELECT t FROM TrmEstatramHis t WHERE t.codiEstaHis = :codiEstaHis"),
    @NamedQuery(name = "TrmEstatramHis.findByFechEstaHis", query = "SELECT t FROM TrmEstatramHis t WHERE t.fechEstaHis = :fechEstaHis"),
    @NamedQuery(name = "TrmEstatramHis.findByUsuaCreaAud", query = "SELECT t FROM TrmEstatramHis t WHERE t.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "TrmEstatramHis.findByFechCreaAud", query = "SELECT t FROM TrmEstatramHis t WHERE t.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "TrmEstatramHis.findByUsuaModiAud", query = "SELECT t FROM TrmEstatramHis t WHERE t.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "TrmEstatramHis.findByFechModiAud", query = "SELECT t FROM TrmEstatramHis t WHERE t.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "TrmEstatramHis.findByNombEquiAud", query = "SELECT t FROM TrmEstatramHis t WHERE t.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "TrmEstatramHis.findByNombSopeAud", query = "SELECT t FROM TrmEstatramHis t WHERE t.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "TrmEstatramHis.findByFlagEstaHis", query = "SELECT t FROM TrmEstatramHis t WHERE t.flagEstaHis = :flagEstaHis")})
public class TrmEstatramHis implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrmEstatramHisPK trmEstatramHisPK;
    @Size(max = 20)
    @Column(name = "CODI_ESTA_HIS")
    private String codiEstaHis;
    @Column(name = "FECH_ESTA_HIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEstaHis;
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
    @Column(name = "FLAG_ESTA_HIS")
    private Short flagEstaHis;
    @JoinColumn(name = "IDEN_EXPE_TRM", referencedColumnName = "IDEN_EXPE_TRM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TrmTramite trmTramite;

    public TrmEstatramHis() {
    }

    public TrmEstatramHis(TrmEstatramHisPK trmEstatramHisPK) {
        this.trmEstatramHisPK = trmEstatramHisPK;
    }

    public TrmEstatramHis(BigInteger idenExpeTrm, int secuEstaHis) {
        this.trmEstatramHisPK = new TrmEstatramHisPK(idenExpeTrm, secuEstaHis);
    }

    public TrmEstatramHisPK getTrmEstatramHisPK() {
        return trmEstatramHisPK;
    }

    public void setTrmEstatramHisPK(TrmEstatramHisPK trmEstatramHisPK) {
        this.trmEstatramHisPK = trmEstatramHisPK;
    }

    public String getCodiEstaHis() {
        return codiEstaHis;
    }

    public void setCodiEstaHis(String codiEstaHis) {
        this.codiEstaHis = codiEstaHis;
    }

    public Date getFechEstaHis() {
        return fechEstaHis;
    }

    public void setFechEstaHis(Date fechEstaHis) {
        this.fechEstaHis = fechEstaHis;
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

    public Short getFlagEstaHis() {
        return flagEstaHis;
    }

    public void setFlagEstaHis(Short flagEstaHis) {
        this.flagEstaHis = flagEstaHis;
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
        hash += (trmEstatramHisPK != null ? trmEstatramHisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmEstatramHis)) {
            return false;
        }
        TrmEstatramHis other = (TrmEstatramHis) object;
        if ((this.trmEstatramHisPK == null && other.trmEstatramHisPK != null) || (this.trmEstatramHisPK != null && !this.trmEstatramHisPK.equals(other.trmEstatramHisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmEstatramHis[ trmEstatramHisPK=" + trmEstatramHisPK + " ]";
    }
    
}
