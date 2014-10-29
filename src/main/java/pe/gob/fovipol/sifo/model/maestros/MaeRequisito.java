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
 * @author probook
 */
@Entity
@Table(name = "MAE_REQUISITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeRequisito.findAll", query = "SELECT m FROM MaeRequisito m"),
    @NamedQuery(name = "MaeRequisito.findByIdenProcPrc", query = "SELECT m FROM MaeRequisito m WHERE m.maeRequisitoPK.idenProcPrc = :idenProcPrc"),
    @NamedQuery(name = "MaeRequisito.findBySecuMaeReq", query = "SELECT m FROM MaeRequisito m WHERE m.maeRequisitoPK.secuMaeReq = :secuMaeReq"),
    @NamedQuery(name = "MaeRequisito.findByNombRequReq", query = "SELECT m FROM MaeRequisito m WHERE m.nombRequReq = :nombRequReq"),
    @NamedQuery(name = "MaeRequisito.findByCodiTipoReq", query = "SELECT m FROM MaeRequisito m WHERE m.codiTipoReq = :codiTipoReq"),
    @NamedQuery(name = "MaeRequisito.findByDescObseReq", query = "SELECT m FROM MaeRequisito m WHERE m.descObseReq = :descObseReq"),
    @NamedQuery(name = "MaeRequisito.findBySecuOrdeReq", query = "SELECT m FROM MaeRequisito m WHERE m.secuOrdeReq = :secuOrdeReq"),
    @NamedQuery(name = "MaeRequisito.findByUsuaCreaAud", query = "SELECT m FROM MaeRequisito m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeRequisito.findByFechCreaAud", query = "SELECT m FROM MaeRequisito m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeRequisito.findByUsuaModiAud", query = "SELECT m FROM MaeRequisito m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeRequisito.findByFechModiAud", query = "SELECT m FROM MaeRequisito m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeRequisito.findByNombEquiAud", query = "SELECT m FROM MaeRequisito m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeRequisito.findByNombSopeAud", query = "SELECT m FROM MaeRequisito m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeRequisito.findByFlagEstaReq", query = "SELECT m FROM MaeRequisito m WHERE m.flagEstaReq = :flagEstaReq")})
public class MaeRequisito implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaeRequisitoPK maeRequisitoPK;
    @Size(max = 120)
    @Column(name = "NOMB_REQU_REQ")
    private String nombRequReq;
    @Column(name = "CODI_TIPO_REQ")
    private Integer codiTipoReq;
    @Size(max = 300)
    @Column(name = "DESC_OBSE_REQ")
    private String descObseReq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_ORDE_REQ")
    private short secuOrdeReq;
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
    @Column(name = "FLAG_ESTA_REQ")
    private Short flagEstaReq;
    @JoinColumn(name = "IDEN_PROC_PRC", referencedColumnName = "IDEN_PROC_PRC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MaeProceso maeProceso;

    public MaeRequisito() {
    }

    public MaeRequisito(MaeRequisitoPK maeRequisitoPK) {
        this.maeRequisitoPK = maeRequisitoPK;
    }

    public MaeRequisito(MaeRequisitoPK maeRequisitoPK, short secuOrdeReq) {
        this.maeRequisitoPK = maeRequisitoPK;
        this.secuOrdeReq = secuOrdeReq;
    }

    public MaeRequisito(BigInteger idenProcPrc, BigInteger secuMaeReq) {
        this.maeRequisitoPK = new MaeRequisitoPK(idenProcPrc, secuMaeReq);
    }

    public MaeRequisitoPK getMaeRequisitoPK() {
        return maeRequisitoPK;
    }

    public void setMaeRequisitoPK(MaeRequisitoPK maeRequisitoPK) {
        this.maeRequisitoPK = maeRequisitoPK;
    }

    public String getNombRequReq() {
        return nombRequReq;
    }

    public void setNombRequReq(String nombRequReq) {
        this.nombRequReq = nombRequReq;
    }

    public Integer getCodiTipoReq() {
        return codiTipoReq;
    }

    public void setCodiTipoReq(Integer codiTipoReq) {
        this.codiTipoReq = codiTipoReq;
    }

    public String getDescObseReq() {
        return descObseReq;
    }

    public void setDescObseReq(String descObseReq) {
        this.descObseReq = descObseReq;
    }

    public short getSecuOrdeReq() {
        return secuOrdeReq;
    }

    public void setSecuOrdeReq(short secuOrdeReq) {
        this.secuOrdeReq = secuOrdeReq;
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

    public Short getFlagEstaReq() {
        return flagEstaReq;
    }

    public void setFlagEstaReq(Short flagEstaReq) {
        this.flagEstaReq = flagEstaReq;
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
        hash += (maeRequisitoPK != null ? maeRequisitoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeRequisito)) {
            return false;
        }
        MaeRequisito other = (MaeRequisito) object;
        if ((this.maeRequisitoPK == null && other.maeRequisitoPK != null) || (this.maeRequisitoPK != null && !this.maeRequisitoPK.equals(other.maeRequisitoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeRequisito[ maeRequisitoPK=" + maeRequisitoPK + " ]";
    }
    
}
