/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model;

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
@Table(name = "MAE_SEDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeSede.findAll", query = "SELECT m FROM MaeSede m"),
    @NamedQuery(name = "MaeSede.findByCodiMaeSede", query = "SELECT m FROM MaeSede m WHERE m.codiMaeSede = :codiMaeSede"),
    @NamedQuery(name = "MaeSede.findByDescSedeSed", query = "SELECT m FROM MaeSede m WHERE m.descSedeSed = :descSedeSed"),
    @NamedQuery(name = "MaeSede.findByDescDireSede", query = "SELECT m FROM MaeSede m WHERE m.descDireSede = :descDireSede"),
    @NamedQuery(name = "MaeSede.findByPersContSed", query = "SELECT m FROM MaeSede m WHERE m.persContSed = :persContSed"),
    @NamedQuery(name = "MaeSede.findByNumeTeleSed", query = "SELECT m FROM MaeSede m WHERE m.numeTeleSed = :numeTeleSed"),
    @NamedQuery(name = "MaeSede.findByUsuaCreaAud", query = "SELECT m FROM MaeSede m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeSede.findByFechCreaAud", query = "SELECT m FROM MaeSede m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeSede.findByUsuaModiAud", query = "SELECT m FROM MaeSede m WHERE m.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "MaeSede.findByFechModiAud", query = "SELECT m FROM MaeSede m WHERE m.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "MaeSede.findByFlagSedeSed", query = "SELECT m FROM MaeSede m WHERE m.flagSedeSed = :flagSedeSed")})
public class MaeSede implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODI_MAE_SEDE")
    private BigDecimal codiMaeSede;
    @Size(max = 120)
    @Column(name = "DESC_SEDE_SED")
    private String descSedeSed;
    @Size(max = 120)
    @Column(name = "DESC_DIRE_SEDE")
    private String descDireSede;
    @Size(max = 120)
    @Column(name = "PERS_CONT_SED")
    private String persContSed;
    @Size(max = 20)
    @Column(name = "NUME_TELE_SED")
    private String numeTeleSed;
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
    @Column(name = "FLAG_SEDE_SED")
    private Short flagSedeSed;
    @OneToMany(mappedBy = "codiMaeSede")
    private List<MaeArea> maeAreaList;
    @JoinColumn(name = "CODI_EMPR_EMP", referencedColumnName = "CODI_EMPR_EMP")
    @ManyToOne
    private MaeEmpresa codiEmprEmp;

    public MaeSede() {
    }

    public MaeSede(BigDecimal codiMaeSede) {
        this.codiMaeSede = codiMaeSede;
    }

    public BigDecimal getCodiMaeSede() {
        return codiMaeSede;
    }

    public void setCodiMaeSede(BigDecimal codiMaeSede) {
        this.codiMaeSede = codiMaeSede;
    }

    public String getDescSedeSed() {
        return descSedeSed;
    }

    public void setDescSedeSed(String descSedeSed) {
        this.descSedeSed = descSedeSed;
    }

    public String getDescDireSede() {
        return descDireSede;
    }

    public void setDescDireSede(String descDireSede) {
        this.descDireSede = descDireSede;
    }

    public String getPersContSed() {
        return persContSed;
    }

    public void setPersContSed(String persContSed) {
        this.persContSed = persContSed;
    }

    public String getNumeTeleSed() {
        return numeTeleSed;
    }

    public void setNumeTeleSed(String numeTeleSed) {
        this.numeTeleSed = numeTeleSed;
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

    public Short getFlagSedeSed() {
        return flagSedeSed;
    }

    public void setFlagSedeSed(Short flagSedeSed) {
        this.flagSedeSed = flagSedeSed;
    }

    @XmlTransient
    public List<MaeArea> getMaeAreaList() {
        return maeAreaList;
    }

    public void setMaeAreaList(List<MaeArea> maeAreaList) {
        this.maeAreaList = maeAreaList;
    }

    public MaeEmpresa getCodiEmprEmp() {
        return codiEmprEmp;
    }

    public void setCodiEmprEmp(MaeEmpresa codiEmprEmp) {
        this.codiEmprEmp = codiEmprEmp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMaeSede != null ? codiMaeSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeSede)) {
            return false;
        }
        MaeSede other = (MaeSede) object;
        if ((this.codiMaeSede == null && other.codiMaeSede != null) || (this.codiMaeSede != null && !this.codiMaeSede.equals(other.codiMaeSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.MaeSede[ codiMaeSede=" + codiMaeSede + " ]";
    }
    
}
