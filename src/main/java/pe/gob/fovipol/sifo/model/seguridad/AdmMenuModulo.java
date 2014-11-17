/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.seguridad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.listener.AuditListener;

/**
 *
 * @author eBuho
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "ADM_MENU_MODULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmMenuModulo.findAll", query = "SELECT a FROM AdmMenuModulo a"),
    @NamedQuery(name = "AdmMenuModulo.findByIdenMemoMmd", query = "SELECT a FROM AdmMenuModulo a WHERE a.idenMemoMmd = :idenMemoMmd"),
    @NamedQuery(name = "AdmMenuModulo.findByUsuaCreaAud", query = "SELECT a FROM AdmMenuModulo a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmMenuModulo.findByFechCreaAud", query = "SELECT a FROM AdmMenuModulo a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmMenuModulo.findByUsuaModiAud", query = "SELECT a FROM AdmMenuModulo a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmMenuModulo.findByFechModiAud", query = "SELECT a FROM AdmMenuModulo a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmMenuModulo.findByNombEquiAud", query = "SELECT a FROM AdmMenuModulo a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmMenuModulo.findByNombSopeAud", query = "SELECT a FROM AdmMenuModulo a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmMenuModulo.findByFlagEstaMmd", query = "SELECT a FROM AdmMenuModulo a WHERE a.flagEstaMmd = :flagEstaMmd")})
public class AdmMenuModulo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @TableGenerator(name = "secAdmMenuModulo",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "ADM_MENU_MODULO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="secAdmMenuModulo") 
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_MEMO_MMD", updatable=false)
    private BigDecimal idenMemoMmd;
    @Size(max = 15)
    @Column(name = "USUA_CREA_AUD", updatable=false)
    private String usuaCreaAud;
    @Column(name = "FECH_CREA_AUD", updatable=false)
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
    @Column(name = "FLAG_ESTA_MMD")
    private short flagEstaMmd;
    @JoinColumn(name = "IDEN_MENU_MNU", referencedColumnName = "IDEN_MENU_MNU")
    @ManyToOne
    private AdmMenu idenMenuMnu;
    @JoinColumn(name = "IDEN_MODU_MOD", referencedColumnName = "IDEN_MODU_MOD")
    @ManyToOne
    private AdmModulo idenModuMod;
    @OneToMany(mappedBy = "idenMemoMmd")
    private List<AdmModuloExcepcion> admModuloExcepcionList;

    public AdmMenuModulo() {
    }

    public AdmMenuModulo(BigDecimal idenMemoMmd) {
        this.idenMemoMmd = idenMemoMmd;
    }

    public AdmMenuModulo(BigDecimal idenMemoMmd, short flagEstaMmd) {
        this.idenMemoMmd = idenMemoMmd;
        this.flagEstaMmd = flagEstaMmd;
    }

    public BigDecimal getIdenMemoMmd() {
        return idenMemoMmd;
    }

    public void setIdenMemoMmd(BigDecimal idenMemoMmd) {
        this.idenMemoMmd = idenMemoMmd;
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

    public short getFlagEstaMmd() {
        return flagEstaMmd;
    }

    public void setFlagEstaMmd(short flagEstaMmd) {
        this.flagEstaMmd = flagEstaMmd;
    }

    public AdmMenu getIdenMenuMnu() {
        return idenMenuMnu;
    }

    public void setIdenMenuMnu(AdmMenu idenMenuMnu) {
        this.idenMenuMnu = idenMenuMnu;
    }

    public AdmModulo getIdenModuMod() {
        return idenModuMod;
    }

    public void setIdenModuMod(AdmModulo idenModuMod) {
        this.idenModuMod = idenModuMod;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmModuloExcepcion> getAdmModuloExcepcionList() {
        return admModuloExcepcionList;
    }

    public void setAdmModuloExcepcionList(List<AdmModuloExcepcion> admModuloExcepcionList) {
        this.admModuloExcepcionList = admModuloExcepcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenMemoMmd != null ? idenMemoMmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmMenuModulo)) {
            return false;
        }
        AdmMenuModulo other = (AdmMenuModulo) object;
        if ((this.idenMemoMmd == null && other.idenMemoMmd != null) || (this.idenMemoMmd != null && !this.idenMemoMmd.equals(other.idenMemoMmd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmMenuModulo[ idenMemoMmd=" + idenMemoMmd + " ]";
    }
    
}
