/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

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
 * @author probook
 */
@Entity
@Table(name = "CRD_ESTACRED_HIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdEstacredHis.findAll", query = "SELECT c FROM CrdEstacredHis c"),
    @NamedQuery(name = "CrdEstacredHis.findByIdenCredCrd", query = "SELECT c FROM CrdEstacredHis c WHERE c.crdEstacredHisPK.idenCredCrd = :idenCredCrd"),
    @NamedQuery(name = "CrdEstacredHis.findBySecuHistCrh", query = "SELECT c FROM CrdEstacredHis c WHERE c.crdEstacredHisPK.secuHistCrh = :secuHistCrh"),
    @NamedQuery(name = "CrdEstacredHis.findByCodiEstaCrh", query = "SELECT c FROM CrdEstacredHis c WHERE c.codiEstaCrh = :codiEstaCrh"),
    @NamedQuery(name = "CrdEstacredHis.findByUsuaCreaAud", query = "SELECT c FROM CrdEstacredHis c WHERE c.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "CrdEstacredHis.findByFechCreaAud", query = "SELECT c FROM CrdEstacredHis c WHERE c.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "CrdEstacredHis.findByUsuaModiAud", query = "SELECT c FROM CrdEstacredHis c WHERE c.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "CrdEstacredHis.findByFechModiAud", query = "SELECT c FROM CrdEstacredHis c WHERE c.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "CrdEstacredHis.findByNombEquiAud", query = "SELECT c FROM CrdEstacredHis c WHERE c.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "CrdEstacredHis.findByNombSopeAud", query = "SELECT c FROM CrdEstacredHis c WHERE c.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "CrdEstacredHis.findByFlagEstaCrh", query = "SELECT c FROM CrdEstacredHis c WHERE c.flagEstaCrh = :flagEstaCrh")})
public class CrdEstacredHis implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CrdEstacredHisPK crdEstacredHisPK;
    @Column(name = "CODI_ESTA_CRH")
    private Integer codiEstaCrh;
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
    @Column(name = "FLAG_ESTA_CRH")
    private Short flagEstaCrh;
    @JoinColumn(name = "IDEN_CRED_CRD", referencedColumnName = "IDEN_CRED_CRD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CrdCredito crdCredito;

    public CrdEstacredHis() {
    }

    public CrdEstacredHis(CrdEstacredHisPK crdEstacredHisPK) {
        this.crdEstacredHisPK = crdEstacredHisPK;
    }

    public CrdEstacredHis(BigInteger idenCredCrd, int secuHistCrh) {
        this.crdEstacredHisPK = new CrdEstacredHisPK(idenCredCrd, secuHistCrh);
    }

    public CrdEstacredHisPK getCrdEstacredHisPK() {
        return crdEstacredHisPK;
    }

    public void setCrdEstacredHisPK(CrdEstacredHisPK crdEstacredHisPK) {
        this.crdEstacredHisPK = crdEstacredHisPK;
    }

    public Integer getCodiEstaCrh() {
        return codiEstaCrh;
    }

    public void setCodiEstaCrh(Integer codiEstaCrh) {
        this.codiEstaCrh = codiEstaCrh;
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

    public Short getFlagEstaCrh() {
        return flagEstaCrh;
    }

    public void setFlagEstaCrh(Short flagEstaCrh) {
        this.flagEstaCrh = flagEstaCrh;
    }

    public CrdCredito getCrdCredito() {
        return crdCredito;
    }

    public void setCrdCredito(CrdCredito crdCredito) {
        this.crdCredito = crdCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crdEstacredHisPK != null ? crdEstacredHisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdEstacredHis)) {
            return false;
        }
        CrdEstacredHis other = (CrdEstacredHis) object;
        if ((this.crdEstacredHisPK == null && other.crdEstacredHisPK != null) || (this.crdEstacredHisPK != null && !this.crdEstacredHisPK.equals(other.crdEstacredHisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdEstacredHis[ crdEstacredHisPK=" + crdEstacredHisPK + " ]";
    }
    
}
