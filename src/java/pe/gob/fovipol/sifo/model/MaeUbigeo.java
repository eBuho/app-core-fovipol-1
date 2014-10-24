/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "MAE_UBIGEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeUbigeo.findAll", query = "SELECT m FROM MaeUbigeo m"),
    @NamedQuery(name = "MaeUbigeo.findByIdenUbigUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.idenUbigUbi = :idenUbigUbi"),
    @NamedQuery(name = "MaeUbigeo.findByNombUbigUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.nombUbigUbi = :nombUbigUbi"),
    @NamedQuery(name = "MaeUbigeo.findByNiveUbigUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.niveUbigUbi = :niveUbigUbi"),
    @NamedQuery(name = "MaeUbigeo.findByUsuaCreaUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.usuaCreaUbi = :usuaCreaUbi"),
    @NamedQuery(name = "MaeUbigeo.findByFechCreaUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.fechCreaUbi = :fechCreaUbi"),
    @NamedQuery(name = "MaeUbigeo.findByUsuaModiUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.usuaModiUbi = :usuaModiUbi"),
    @NamedQuery(name = "MaeUbigeo.findByFechModiUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.fechModiUbi = :fechModiUbi"),
    @NamedQuery(name = "MaeUbigeo.findByFlagEstaUbi", query = "SELECT m FROM MaeUbigeo m WHERE m.flagEstaUbi = :flagEstaUbi")})
public class MaeUbigeo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "IDEN_UBIG_UBI")
    private String idenUbigUbi;
    @Size(max = 50)
    @Column(name = "NOMB_UBIG_UBI")
    private String nombUbigUbi;
    @Column(name = "NIVE_UBIG_UBI")
    private Short niveUbigUbi;
    @Size(max = 15)
    @Column(name = "USUA_CREA_UBI")
    private String usuaCreaUbi;
    @Column(name = "FECH_CREA_UBI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaUbi;
    @Size(max = 15)
    @Column(name = "USUA_MODI_UBI")
    private String usuaModiUbi;
    @Column(name = "FECH_MODI_UBI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechModiUbi;
    @Column(name = "FLAG_ESTA_UBI")
    private Short flagEstaUbi;
    @OneToMany(mappedBy = "ubigNaciPer")
    private List<MaePersona> maePersonaList;
    @OneToMany(mappedBy = "ubigResiPer")
    private List<MaePersona> maePersonaList1;
    @OneToMany(mappedBy = "idenUbipUbi")
    private List<MaeUbigeo> maeUbigeoList;
    @JoinColumn(name = "IDEN_UBIP_UBI", referencedColumnName = "IDEN_UBIG_UBI")
    @ManyToOne
    private MaeUbigeo idenUbipUbi;

    public MaeUbigeo() {
    }

    public MaeUbigeo(String idenUbigUbi) {
        this.idenUbigUbi = idenUbigUbi;
    }

    public String getIdenUbigUbi() {
        return idenUbigUbi;
    }

    public void setIdenUbigUbi(String idenUbigUbi) {
        this.idenUbigUbi = idenUbigUbi;
    }

    public String getNombUbigUbi() {
        return nombUbigUbi;
    }

    public void setNombUbigUbi(String nombUbigUbi) {
        this.nombUbigUbi = nombUbigUbi;
    }

    public Short getNiveUbigUbi() {
        return niveUbigUbi;
    }

    public void setNiveUbigUbi(Short niveUbigUbi) {
        this.niveUbigUbi = niveUbigUbi;
    }

    public String getUsuaCreaUbi() {
        return usuaCreaUbi;
    }

    public void setUsuaCreaUbi(String usuaCreaUbi) {
        this.usuaCreaUbi = usuaCreaUbi;
    }

    public Date getFechCreaUbi() {
        return fechCreaUbi;
    }

    public void setFechCreaUbi(Date fechCreaUbi) {
        this.fechCreaUbi = fechCreaUbi;
    }

    public String getUsuaModiUbi() {
        return usuaModiUbi;
    }

    public void setUsuaModiUbi(String usuaModiUbi) {
        this.usuaModiUbi = usuaModiUbi;
    }

    public Date getFechModiUbi() {
        return fechModiUbi;
    }

    public void setFechModiUbi(Date fechModiUbi) {
        this.fechModiUbi = fechModiUbi;
    }

    public Short getFlagEstaUbi() {
        return flagEstaUbi;
    }

    public void setFlagEstaUbi(Short flagEstaUbi) {
        this.flagEstaUbi = flagEstaUbi;
    }

    @XmlTransient
    public List<MaePersona> getMaePersonaList() {
        return maePersonaList;
    }

    public void setMaePersonaList(List<MaePersona> maePersonaList) {
        this.maePersonaList = maePersonaList;
    }

    @XmlTransient
    public List<MaePersona> getMaePersonaList1() {
        return maePersonaList1;
    }

    public void setMaePersonaList1(List<MaePersona> maePersonaList1) {
        this.maePersonaList1 = maePersonaList1;
    }

    @XmlTransient
    public List<MaeUbigeo> getMaeUbigeoList() {
        return maeUbigeoList;
    }

    public void setMaeUbigeoList(List<MaeUbigeo> maeUbigeoList) {
        this.maeUbigeoList = maeUbigeoList;
    }

    public MaeUbigeo getIdenUbipUbi() {
        return idenUbipUbi;
    }

    public void setIdenUbipUbi(MaeUbigeo idenUbipUbi) {
        this.idenUbipUbi = idenUbipUbi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenUbigUbi != null ? idenUbigUbi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeUbigeo)) {
            return false;
        }
        MaeUbigeo other = (MaeUbigeo) object;
        if ((this.idenUbigUbi == null && other.idenUbigUbi != null) || (this.idenUbigUbi != null && !this.idenUbigUbi.equals(other.idenUbigUbi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.model.MaeUbigeo[ idenUbigUbi=" + idenUbigUbi + " ]";
    }
    
}
