/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_INMUEBLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeInmueble.findAll", query = "SELECT m FROM MaeInmueble m"),
    @NamedQuery(name = "MaeInmueble.findByIdenInmuImb", query = "SELECT m FROM MaeInmueble m WHERE m.idenInmuImb = :idenInmuImb"),
    @NamedQuery(name = "MaeInmueble.findByDescDireImb", query = "SELECT m FROM MaeInmueble m WHERE m.descDireImb = :descDireImb"),
    @NamedQuery(name = "MaeInmueble.findByMotiBienImb", query = "SELECT m FROM MaeInmueble m WHERE m.motiBienImb = :motiBienImb"),
    @NamedQuery(name = "MaeInmueble.findByNumeLoteImb", query = "SELECT m FROM MaeInmueble m WHERE m.numeLoteImb = :numeLoteImb"),
    @NamedQuery(name = "MaeInmueble.findByLetrMznaImb", query = "SELECT m FROM MaeInmueble m WHERE m.letrMznaImb = :letrMznaImb"),
    @NamedQuery(name = "MaeInmueble.findByRutaFotoImb", query = "SELECT m FROM MaeInmueble m WHERE m.rutaFotoImb = :rutaFotoImb"),
    @NamedQuery(name = "MaeInmueble.findByRutaPlanImb", query = "SELECT m FROM MaeInmueble m WHERE m.rutaPlanImb = :rutaPlanImb"),
    @NamedQuery(name = "MaeInmueble.findByTipoZonaImb", query = "SELECT m FROM MaeInmueble m WHERE m.tipoZonaImb = :tipoZonaImb"),
    @NamedQuery(name = "MaeInmueble.findByFlagEstaImb", query = "SELECT m FROM MaeInmueble m WHERE m.flagEstaImb = :flagEstaImb")})
public class MaeInmueble implements Serializable {
    @OneToMany(mappedBy = "idenInmuImb")
    private List<CrdCredito> crdCreditoList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_INMU_IMB")
    private BigDecimal idenInmuImb;
    @Size(max = 300)
    @Column(name = "DESC_DIRE_IMB")
    private String descDireImb;
    @Column(name = "MOTI_BIEN_IMB")
    private Integer motiBienImb;
    @Size(max = 10)
    @Column(name = "NUME_LOTE_IMB")
    private String numeLoteImb;
    @Size(max = 5)
    @Column(name = "LETR_MZNA_IMB")
    private String letrMznaImb;
    @Size(max = 200)
    @Column(name = "RUTA_FOTO_IMB")
    private String rutaFotoImb;
    @Size(max = 200)
    @Column(name = "RUTA_PLAN_IMB")
    private String rutaPlanImb;
    @Column(name = "TIPO_ZONA_IMB")
    private Integer tipoZonaImb;
    @Column(name = "FLAG_ESTA_IMB")
    private Short flagEstaImb;
    @JoinColumn(name = "IDEN_UBIG_UBI", referencedColumnName = "IDEN_UBIG_UBI")
    @ManyToOne
    private MaeUbigeo idenUbigUbi;

    public MaeInmueble() {
    }

    public MaeInmueble(BigDecimal idenInmuImb) {
        this.idenInmuImb = idenInmuImb;
    }

    public BigDecimal getIdenInmuImb() {
        return idenInmuImb;
    }

    public void setIdenInmuImb(BigDecimal idenInmuImb) {
        this.idenInmuImb = idenInmuImb;
    }

    public String getDescDireImb() {
        return descDireImb;
    }

    public void setDescDireImb(String descDireImb) {
        this.descDireImb = descDireImb;
    }

    public Integer getMotiBienImb() {
        return motiBienImb;
    }

    public void setMotiBienImb(Integer motiBienImb) {
        this.motiBienImb = motiBienImb;
    }

    public String getNumeLoteImb() {
        return numeLoteImb;
    }

    public void setNumeLoteImb(String numeLoteImb) {
        this.numeLoteImb = numeLoteImb;
    }

    public String getLetrMznaImb() {
        return letrMznaImb;
    }

    public void setLetrMznaImb(String letrMznaImb) {
        this.letrMznaImb = letrMznaImb;
    }

    public String getRutaFotoImb() {
        return rutaFotoImb;
    }

    public void setRutaFotoImb(String rutaFotoImb) {
        this.rutaFotoImb = rutaFotoImb;
    }

    public String getRutaPlanImb() {
        return rutaPlanImb;
    }

    public void setRutaPlanImb(String rutaPlanImb) {
        this.rutaPlanImb = rutaPlanImb;
    }

    public Integer getTipoZonaImb() {
        return tipoZonaImb;
    }

    public void setTipoZonaImb(Integer tipoZonaImb) {
        this.tipoZonaImb = tipoZonaImb;
    }

    public Short getFlagEstaImb() {
        return flagEstaImb;
    }

    public void setFlagEstaImb(Short flagEstaImb) {
        this.flagEstaImb = flagEstaImb;
    }

    public MaeUbigeo getIdenUbigUbi() {
        return idenUbigUbi;
    }

    public void setIdenUbigUbi(MaeUbigeo idenUbigUbi) {
        this.idenUbigUbi = idenUbigUbi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenInmuImb != null ? idenInmuImb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeInmueble)) {
            return false;
        }
        MaeInmueble other = (MaeInmueble) object;
        if ((this.idenInmuImb == null && other.idenInmuImb != null) || (this.idenInmuImb != null && !this.idenInmuImb.equals(other.idenInmuImb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeInmueble[ idenInmuImb=" + idenInmuImb + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdCredito> getCrdCreditoList() {
        return crdCreditoList;
    }

    public void setCrdCreditoList(List<CrdCredito> crdCreditoList) {
        this.crdCreditoList = crdCreditoList;
    }
    
}
