/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author probook
 */
@Entity
@Table(name = "MAE_TIPO_CAMBIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaeTipoCambio.findAll", query = "SELECT m FROM MaeTipoCambio m"),
    @NamedQuery(name = "MaeTipoCambio.findByIdenTcamTcm", query = "SELECT m FROM MaeTipoCambio m WHERE m.idenTcamTcm = :idenTcamTcm"),
    @NamedQuery(name = "MaeTipoCambio.findByCodiMoneCrd", query = "SELECT m FROM MaeTipoCambio m WHERE m.codiMoneCrd = :codiMoneCrd"),
    @NamedQuery(name = "MaeTipoCambio.findByValoCambTcm", query = "SELECT m FROM MaeTipoCambio m WHERE m.valoCambTcm = :valoCambTcm"),
    @NamedQuery(name = "MaeTipoCambio.findByFechInicTcm", query = "SELECT m FROM MaeTipoCambio m WHERE m.fechInicTcm = :fechInicTcm"),
    @NamedQuery(name = "MaeTipoCambio.findByFechFinaTcm", query = "SELECT m FROM MaeTipoCambio m WHERE m.fechFinaTcm = :fechFinaTcm"),
    @NamedQuery(name = "MaeTipoCambio.findByUsuaCreaAud", query = "SELECT m FROM MaeTipoCambio m WHERE m.usuaCreaAud = :usuaCreaAud"),
    @NamedQuery(name = "MaeTipoCambio.findByFechCreaAud", query = "SELECT m FROM MaeTipoCambio m WHERE m.fechCreaAud = :fechCreaAud"),
    @NamedQuery(name = "MaeTipoCambio.findByNombEquiAud", query = "SELECT m FROM MaeTipoCambio m WHERE m.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "MaeTipoCambio.findByNombSopeAud", query = "SELECT m FROM MaeTipoCambio m WHERE m.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "MaeTipoCambio.findByFlgEstaTcm", query = "SELECT m FROM MaeTipoCambio m WHERE m.flgEstaTcm = :flgEstaTcm")})
public class MaeTipoCambio implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_TCAM_TCM")
    private BigDecimal idenTcamTcm;
    @Column(name = "CODI_MONE_CRD")
    private Integer codiMoneCrd;
    @Column(name = "VALO_CAMB_TCM")
    private BigDecimal valoCambTcm;
    @Column(name = "FECH_INIC_TCM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechInicTcm;
    @Column(name = "FECH_FINA_TCM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechFinaTcm;
    @Size(max = 15)
    @Column(name = "USUA_CREA_AUD")
    private String usuaCreaAud;
    @Column(name = "FECH_CREA_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreaAud;
    @Size(max = 40)
    @Column(name = "NOMB_EQUI_AUD")
    private String nombEquiAud;
    @Size(max = 40)
    @Column(name = "NOMB_SOPE_AUD")
    private String nombSopeAud;
    @Column(name = "FLG_ESTA_TCM")
    private Short flgEstaTcm;
    @OneToMany(mappedBy = "idenTcamTcm")
    private List<CrdCreditoCuota> crdCreditoCuotaList;

    public MaeTipoCambio() {
    }

    public MaeTipoCambio(BigDecimal idenTcamTcm) {
        this.idenTcamTcm = idenTcamTcm;
    }

    public BigDecimal getIdenTcamTcm() {
        return idenTcamTcm;
    }

    public void setIdenTcamTcm(BigDecimal idenTcamTcm) {
        this.idenTcamTcm = idenTcamTcm;
    }

    public Integer getCodiMoneCrd() {
        return codiMoneCrd;
    }

    public void setCodiMoneCrd(Integer codiMoneCrd) {
        this.codiMoneCrd = codiMoneCrd;
    }

    public BigDecimal getValoCambTcm() {
        return valoCambTcm;
    }

    public void setValoCambTcm(BigDecimal valoCambTcm) {
        this.valoCambTcm = valoCambTcm;
    }

    public Date getFechInicTcm() {
        return fechInicTcm;
    }

    public void setFechInicTcm(Date fechInicTcm) {
        this.fechInicTcm = fechInicTcm;
    }

    public Date getFechFinaTcm() {
        return fechFinaTcm;
    }

    public void setFechFinaTcm(Date fechFinaTcm) {
        this.fechFinaTcm = fechFinaTcm;
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

    public Short getFlgEstaTcm() {
        return flgEstaTcm;
    }

    public void setFlgEstaTcm(Short flgEstaTcm) {
        this.flgEstaTcm = flgEstaTcm;
    }

    @XmlTransient
    @JsonIgnore
    public List<CrdCreditoCuota> getCrdCreditoCuotaList() {
        return crdCreditoCuotaList;
    }

    public void setCrdCreditoCuotaList(List<CrdCreditoCuota> crdCreditoCuotaList) {
        this.crdCreditoCuotaList = crdCreditoCuotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenTcamTcm != null ? idenTcamTcm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeTipoCambio)) {
            return false;
        }
        MaeTipoCambio other = (MaeTipoCambio) object;
        if ((this.idenTcamTcm == null && other.idenTcamTcm != null) || (this.idenTcamTcm != null && !this.idenTcamTcm.equals(other.idenTcamTcm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.MaeTipoCambio[ idenTcamTcm=" + idenTcamTcm + " ]";
    }
    
}
