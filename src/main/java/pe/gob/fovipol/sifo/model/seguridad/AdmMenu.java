/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.seguridad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

/**
 *
 * @author eBuho
 */
@Entity
@Table(name = "ADM_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmMenu.findAll", query = "SELECT a FROM AdmMenu a"),
    @NamedQuery(name = "AdmMenu.findByIdenMenuMnu", query = "SELECT a FROM AdmMenu a WHERE a.idenMenuMnu = :idenMenuMnu"),
    @NamedQuery(name = "AdmMenu.findByNombMenuMnu", query = "SELECT a FROM AdmMenu a WHERE a.nombMenuMnu = :nombMenuMnu"),
    @NamedQuery(name = "AdmMenu.findByPagiMenuMnu", query = "SELECT a FROM AdmMenu a WHERE a.pagiMenuMnu = :pagiMenuMnu"),
    @NamedQuery(name = "AdmMenu.findByDescPrmtMnu", query = "SELECT a FROM AdmMenu a WHERE a.descPrmtMnu = :descPrmtMnu"),
    @NamedQuery(name = "AdmMenu.findByUsuaCreaAud", query = "SELECT a FROM AdmMenu a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmMenu.findByFechCreaAud", query = "SELECT a FROM AdmMenu a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmMenu.findByUsuaModiAud", query = "SELECT a FROM AdmMenu a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmMenu.findByFechModiAud", query = "SELECT a FROM AdmMenu a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmMenu.findByNombEquiAud", query = "SELECT a FROM AdmMenu a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmMenu.findByNombSopeAud", query = "SELECT a FROM AdmMenu a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmMenu.findByFlagEstaMnu", query = "SELECT a FROM AdmMenu a WHERE a.flagEstaMnu = :flagEstaMnu")})
public class AdmMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id 
    @TableGenerator(name = "admMenuSeq",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "ADM_MENU", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="admMenuSeq") 
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_MENU_MNU")
    private long idenMenuMnu;
    @Size(max = 120)
    @Column(name = "NOMB_MENU_MNU")
    private String nombMenuMnu;
    @Size(max = 300)
    @Column(name = "PAGI_MENU_MNU")
    private String pagiMenuMnu;
    @Size(max = 120)
    @Column(name = "DESC_PRMT_MNU")
    private String descPrmtMnu;
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
    @Column(name = "FLAG_ESTA_MNU")
    private short flagEstaMnu;
    @OneToMany(mappedBy = "idenMenuMnu")
    private List<AdmMenuModulo> admMenuModuloList;
    @OneToMany(mappedBy = "idenMenuPmn")
    private List<AdmPermisoMenu> admPermisoMenuList;

    public AdmMenu() {
    }

    public AdmMenu(long idenMenuMnu) {
        this.idenMenuMnu = idenMenuMnu;
    }

    public AdmMenu(long idenMenuMnu, short flagEstaMnu) {
        this.idenMenuMnu = idenMenuMnu;
        this.flagEstaMnu = flagEstaMnu;
    }

    public long getIdenMenuMnu() {
        return idenMenuMnu;
    }

    public void setIdenMenuMnu(long idenMenuMnu) {
        this.idenMenuMnu = idenMenuMnu;
    }

    public String getNombMenuMnu() {
        return nombMenuMnu;
    }

    public void setNombMenuMnu(String nombMenuMnu) {
        this.nombMenuMnu = nombMenuMnu;
    }

    public String getPagiMenuMnu() {
        return pagiMenuMnu;
    }

    public void setPagiMenuMnu(String pagiMenuMnu) {
        this.pagiMenuMnu = pagiMenuMnu;
    }

    public String getDescPrmtMnu() {
        return descPrmtMnu;
    }

    public void setDescPrmtMnu(String descPrmtMnu) {
        this.descPrmtMnu = descPrmtMnu;
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

    public short getFlagEstaMnu() {
        return flagEstaMnu;
    }

    public void setFlagEstaMnu(short flagEstaMnu) {
        this.flagEstaMnu = flagEstaMnu;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmMenuModulo> getAdmMenuModuloList() {
        return admMenuModuloList;
    }

    public void setAdmMenuModuloList(List<AdmMenuModulo> admMenuModuloList) {
        this.admMenuModuloList = admMenuModuloList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmPermisoMenu> getAdmPermisoMenuList() {
        return admPermisoMenuList;
    }

    public void setAdmPermisoMenuList(List<AdmPermisoMenu> admPermisoMenuList) {
        this.admPermisoMenuList = admPermisoMenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idenMenuMnu;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmMenu[ idenMenuMnu=" + idenMenuMnu + " ]";
    }
    
}
