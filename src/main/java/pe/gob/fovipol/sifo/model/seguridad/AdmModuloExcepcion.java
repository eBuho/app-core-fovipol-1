/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.seguridad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pe.gob.fovipol.sifo.listener.AuditListener;

/**
 *
 * @author eBuho
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "ADM_MODULO_EXCEPCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmModuloExcepcion.findAll", query = "SELECT a FROM AdmModuloExcepcion a"),
    @NamedQuery(name = "AdmModuloExcepcion.findByIdenModuMde", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.idenModuMde = :idenModuMde"),
    @NamedQuery(name = "AdmModuloExcepcion.findByUsuaCreaAud", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "AdmModuloExcepcion.findByFechCreaAud", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "AdmModuloExcepcion.findByUsuaModiAud", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "AdmModuloExcepcion.findByFechModiAud", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "AdmModuloExcepcion.findByNombEquiAud", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "AdmModuloExcepcion.findByNombSopeAud", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "AdmModuloExcepcion.findByFlagEstaMde", query = "SELECT a FROM AdmModuloExcepcion a WHERE a.flagEstaMde = :flagEstaMde")})
public class AdmModuloExcepcion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @TableGenerator(name = "secAdmModuloExcepcion",table = "SIFO.adm_secuencia",valueColumnName = "gene_val", pkColumnName = "iden_gene_tab",pkColumnValue = "ADM_MODULO_EXCEPCION", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="secAdmModuloExcepcion")
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_MODU_MDE", updatable=false)
    private BigDecimal idenModuMde;
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
    @Column(name = "FLAG_ESTA_MDE")
    private short flagEstaMde;
    @JoinColumn(name = "IDEN_MEMO_MMD", referencedColumnName = "IDEN_MEMO_MMD")
    @ManyToOne
    private AdmMenuModulo idenMemoMmd;
    @JoinColumn(name = "IDEN_MORO_MDR", referencedColumnName = "IDEN_MORO_MDR")
    @ManyToOne
    private AdmModuloRol idenMoroMdr;

    public AdmModuloExcepcion() {
    }

    public AdmModuloExcepcion(BigDecimal idenModuMde) {
        this.idenModuMde = idenModuMde;
    }

    public AdmModuloExcepcion(BigDecimal idenModuMde, short flagEstaMde) {
        this.idenModuMde = idenModuMde;
        this.flagEstaMde = flagEstaMde;
    }

    public BigDecimal getIdenModuMde() {
        return idenModuMde;
    }

    public void setIdenModuMde(BigDecimal idenModuMde) {
        this.idenModuMde = idenModuMde;
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

    public short getFlagEstaMde() {
        return flagEstaMde;
    }

    public void setFlagEstaMde(short flagEstaMde) {
        this.flagEstaMde = flagEstaMde;
    }

    public AdmMenuModulo getIdenMemoMmd() {
        return idenMemoMmd;
    }

    public void setIdenMemoMmd(AdmMenuModulo idenMemoMmd) {
        this.idenMemoMmd = idenMemoMmd;
    }

    public AdmModuloRol getIdenMoroMdr() {
        return idenMoroMdr;
    }

    public void setIdenMoroMdr(AdmModuloRol idenMoroMdr) {
        this.idenMoroMdr = idenMoroMdr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenModuMde != null ? idenModuMde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmModuloExcepcion)) {
            return false;
        }
        AdmModuloExcepcion other = (AdmModuloExcepcion) object;
        if ((this.idenModuMde == null && other.idenModuMde != null) || (this.idenModuMde != null && !this.idenModuMde.equals(other.idenModuMde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.seguridad.AdmModuloExcepcion[ idenModuMde=" + idenModuMde + " ]";
    }
    
}
