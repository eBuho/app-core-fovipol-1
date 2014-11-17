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
@Table(name = "ADM_PERMISO_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmPermisoMenu.findAll", query = "SELECT a FROM AdmPermisoMenu a"),
    @NamedQuery(name = "AdmPermisoMenu.findByIdenPermPmn", query = "SELECT a FROM AdmPermisoMenu a WHERE a.idenPermPmn = :idenPermPmn"),
    @NamedQuery(name = "AdmPermisoMenu.findByNombPermPmn", query = "SELECT a FROM AdmPermisoMenu a WHERE a.nombPermPmn = :nombPermPmn"),
    @NamedQuery(name = "AdmPermisoMenu.findByNombPercPmn", query = "SELECT a FROM AdmPermisoMenu a WHERE a.nombPercPmn = :nombPercPmn"),
    @NamedQuery(name = "AdmPermisoMenu.findByTipoVisuPmn", query = "SELECT a FROM AdmPermisoMenu a WHERE a.tipoVisuPmn = :tipoVisuPmn"),
    @NamedQuery(name = "AdmPermisoMenu.findByUsuaCreaAud", query = "SELECT a FROM AdmPermisoMenu a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmPermisoMenu.findByFechCreaAud", query = "SELECT a FROM AdmPermisoMenu a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmPermisoMenu.findByUsuaModiAud", query = "SELECT a FROM AdmPermisoMenu a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmPermisoMenu.findByFechModiAud", query = "SELECT a FROM AdmPermisoMenu a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmPermisoMenu.findByNombEquiAud", query = "SELECT a FROM AdmPermisoMenu a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmPermisoMenu.findByNombSopeAud", query = "SELECT a FROM AdmPermisoMenu a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmPermisoMenu.findByFlagEstaPmn", query = "SELECT a FROM AdmPermisoMenu a WHERE a.flagEstaPmn = :flagEstaPmn")})
public class AdmPermisoMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @TableGenerator(name = "secAdmPermisoMenu",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "ADM_PERMISO_MENU", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="secAdmPermisoMenu")
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PERM_PMN", updatable=false)
    private BigDecimal idenPermPmn;
    @Size(max = 120)
    @Column(name = "NOMB_PERM_PMN")
    private String nombPermPmn;
    @Size(max = 120)
    @Column(name = "NOMB_PERC_PMN")
    private String nombPercPmn;
    @Column(name = "TIPO_VISU_PMN")
    private Character tipoVisuPmn;
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
    @Column(name = "FLAG_ESTA_PMN")
    private short flagEstaPmn;
    @JoinColumn(name = "IDEN_MENU_PMN", referencedColumnName = "IDEN_MENU_MNU")
    @ManyToOne
    private AdmMenu idenMenuPmn;
    @OneToMany(mappedBy = "idenPermPmn")
    private List<AdmRolPermenu> admRolPermenuList;

    public AdmPermisoMenu() {
    }

    public AdmPermisoMenu(BigDecimal idenPermPmn) {
        this.idenPermPmn = idenPermPmn;
    }

    public AdmPermisoMenu(BigDecimal idenPermPmn, short flagEstaPmn) {
        this.idenPermPmn = idenPermPmn;
        this.flagEstaPmn = flagEstaPmn;
    }

    public BigDecimal getIdenPermPmn() {
        return idenPermPmn;
    }

    public void setIdenPermPmn(BigDecimal idenPermPmn) {
        this.idenPermPmn = idenPermPmn;
    }

    public String getNombPermPmn() {
        return nombPermPmn;
    }

    public void setNombPermPmn(String nombPermPmn) {
        this.nombPermPmn = nombPermPmn;
    }

    public String getNombPercPmn() {
        return nombPercPmn;
    }

    public void setNombPercPmn(String nombPercPmn) {
        this.nombPercPmn = nombPercPmn;
    }

    public Character getTipoVisuPmn() {
        return tipoVisuPmn;
    }

    public void setTipoVisuPmn(Character tipoVisuPmn) {
        this.tipoVisuPmn = tipoVisuPmn;
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

    public short getFlagEstaPmn() {
        return flagEstaPmn;
    }

    public void setFlagEstaPmn(short flagEstaPmn) {
        this.flagEstaPmn = flagEstaPmn;
    }

    public AdmMenu getIdenMenuPmn() {
        return idenMenuPmn;
    }

    public void setIdenMenuPmn(AdmMenu idenMenuPmn) {
        this.idenMenuPmn = idenMenuPmn;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmRolPermenu> getAdmRolPermenuList() {
        return admRolPermenuList;
    }

    public void setAdmRolPermenuList(List<AdmRolPermenu> admRolPermenuList) {
        this.admRolPermenuList = admRolPermenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenPermPmn != null ? idenPermPmn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmPermisoMenu)) {
            return false;
        }
        AdmPermisoMenu other = (AdmPermisoMenu) object;
        if ((this.idenPermPmn == null && other.idenPermPmn != null) || (this.idenPermPmn != null && !this.idenPermPmn.equals(other.idenPermPmn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmPermisoMenu[ idenPermPmn=" + idenPermPmn + " ]";
    }
    
}
