/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

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
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "CRD_SIMULA_SEGURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdSimulaSeguro.findAll", query = "SELECT c FROM CrdSimulaSeguro c"),
    @NamedQuery(name = "CrdSimulaSeguro.findByIdenSimuSim", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.crdSimulaSeguroPK.idenSimuSim = :idenSimuSim"),
    @NamedQuery(name = "CrdSimulaSeguro.findBySecuSeguSsg", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.crdSimulaSeguroPK.secuSeguSsg = :secuSeguSsg"),
    @NamedQuery(name = "CrdSimulaSeguro.findByUsuaCreaAud", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdSimulaSeguro.findByFechCreaAud", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdSimulaSeguro.findByUsuaModiAud", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdSimulaSeguro.findByFechModiAud", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdSimulaSeguro.findByNombEquiAud", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdSimulaSeguro.findByNombSopeAud", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdSimulaSeguro.findByFlagEstaSsg", query = "SELECT c FROM CrdSimulaSeguro c WHERE c.flagEstaSsg = :flagEstaSsg")})
public class CrdSimulaSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrdSimulaSeguroPK crdSimulaSeguroPK;
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
    @Column(name = "FLAG_ESTA_SSG")
    private short flagEstaSsg;
    @JoinColumn(name = "IDEN_SEGU_SEG", referencedColumnName = "IDEN_SEGU_SEG")
    @ManyToOne
    private MaeSeguro idenSeguSeg;
    @JoinColumn(name = "IDEN_SIMU_SIM", referencedColumnName = "IDEN_SIMU_SIM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CrdSimulacion crdSimulacion;

    public CrdSimulaSeguro() {
    }

    public CrdSimulaSeguro(CrdSimulaSeguroPK crdSimulaSeguroPK) {
        this.crdSimulaSeguroPK = crdSimulaSeguroPK;
    }

    public CrdSimulaSeguro(CrdSimulaSeguroPK crdSimulaSeguroPK, short flagEstaSsg) {
        this.crdSimulaSeguroPK = crdSimulaSeguroPK;
        this.flagEstaSsg = flagEstaSsg;
    }

    public CrdSimulaSeguro(BigInteger idenSimuSim, int secuSeguSsg) {
        this.crdSimulaSeguroPK = new CrdSimulaSeguroPK(idenSimuSim, secuSeguSsg);
    }

    public CrdSimulaSeguroPK getCrdSimulaSeguroPK() {
        return crdSimulaSeguroPK;
    }

    public void setCrdSimulaSeguroPK(CrdSimulaSeguroPK crdSimulaSeguroPK) {
        this.crdSimulaSeguroPK = crdSimulaSeguroPK;
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

    public short getFlagEstaSsg() {
        return flagEstaSsg;
    }

    public void setFlagEstaSsg(short flagEstaSsg) {
        this.flagEstaSsg = flagEstaSsg;
    }

    public MaeSeguro getIdenSeguSeg() {
        return idenSeguSeg;
    }

    public void setIdenSeguSeg(MaeSeguro idenSeguSeg) {
        this.idenSeguSeg = idenSeguSeg;
    }

    public CrdSimulacion getCrdSimulacion() {
        return crdSimulacion;
    }

    public void setCrdSimulacion(CrdSimulacion crdSimulacion) {
        this.crdSimulacion = crdSimulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crdSimulaSeguroPK != null ? crdSimulaSeguroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdSimulaSeguro)) {
            return false;
        }
        CrdSimulaSeguro other = (CrdSimulaSeguro) object;
        if ((this.crdSimulaSeguroPK == null && other.crdSimulaSeguroPK != null) || (this.crdSimulaSeguroPK != null && !this.crdSimulaSeguroPK.equals(other.crdSimulaSeguroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdSimulaSeguro[ crdSimulaSeguroPK=" + crdSimulaSeguroPK + " ]";
    }
    
}
