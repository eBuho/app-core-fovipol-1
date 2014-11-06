/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.tramite;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pe.gob.fovipol.sifo.model.maestros.MaeEmpresa;
import pe.gob.fovipol.sifo.model.maestros.MaeRequisito;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "TRM_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrmDocumento.findAll", query = "SELECT t FROM TrmDocumento t"),
    @NamedQuery(name = "TrmDocumento.findByIdenExpeTrm", query = "SELECT t FROM TrmDocumento t WHERE t.trmDocumentoPK.idenExpeTrm = :idenExpeTrm"),
    @NamedQuery(name = "TrmDocumento.findBySecuDocuDoc", query = "SELECT t FROM TrmDocumento t WHERE t.trmDocumentoPK.secuDocuDoc = :secuDocuDoc"),
    @NamedQuery(name = "TrmDocumento.findByFechVigeDoc", query = "SELECT t FROM TrmDocumento t WHERE t.fechVigeDoc = :fechVigeDoc"),
    @NamedQuery(name = "TrmDocumento.findByFechEmisDoc", query = "SELECT t FROM TrmDocumento t WHERE t.fechEmisDoc = :fechEmisDoc"),
    @NamedQuery(name = "TrmDocumento.findByNumeFoliDoc", query = "SELECT t FROM TrmDocumento t WHERE t.numeFoliDoc = :numeFoliDoc"),
    @NamedQuery(name = "TrmDocumento.findByDescObseDoc", query = "SELECT t FROM TrmDocumento t WHERE t.descObseDoc = :descObseDoc"),
    @NamedQuery(name = "TrmDocumento.findByNombRutaDoc", query = "SELECT t FROM TrmDocumento t WHERE t.nombRutaDoc = :nombRutaDoc"),
    @NamedQuery(name = "TrmDocumento.findByFlagFisiDoc", query = "SELECT t FROM TrmDocumento t WHERE t.flagFisiDoc = :flagFisiDoc"),
    @NamedQuery(name = "TrmDocumento.findByFlagPertDoc", query = "SELECT t FROM TrmDocumento t WHERE t.flagPertDoc = :flagPertDoc"),
    @NamedQuery(name = "TrmDocumento.findByDescNombDoc", query = "SELECT t FROM TrmDocumento t WHERE t.descNombDoc = :descNombDoc"),
    @NamedQuery(name = "TrmDocumento.findByUsuaRegiAud", query = "SELECT t FROM TrmDocumento t WHERE t.usuaRegiAud = :usuaRegiAud"),
    @NamedQuery(name = "TrmDocumento.findByFechRegiAud", query = "SELECT t FROM TrmDocumento t WHERE t.fechRegiAud = :fechRegiAud"),
    @NamedQuery(name = "TrmDocumento.findByUsuaModiAud", query = "SELECT t FROM TrmDocumento t WHERE t.usuaModiAud = :usuaModiAud"),
    @NamedQuery(name = "TrmDocumento.findByFechModiAud", query = "SELECT t FROM TrmDocumento t WHERE t.fechModiAud = :fechModiAud"),
    @NamedQuery(name = "TrmDocumento.findByNombEquiAud", query = "SELECT t FROM TrmDocumento t WHERE t.nombEquiAud = :nombEquiAud"),
    @NamedQuery(name = "TrmDocumento.findByNombSopeAud", query = "SELECT t FROM TrmDocumento t WHERE t.nombSopeAud = :nombSopeAud"),
    @NamedQuery(name = "TrmDocumento.findByFlagEstaDoc", query = "SELECT t FROM TrmDocumento t WHERE t.flagEstaDoc = :flagEstaDoc")})
public class TrmDocumento implements Serializable {
    @JoinColumns({
        @JoinColumn(name = "IDEN_PROC_PRC", referencedColumnName = "IDEN_PROC_PRC"),
        @JoinColumn(name = "SECU_MAE_REQ", referencedColumnName = "SECU_MAE_REQ")})
    @ManyToOne
    private MaeRequisito maeRequisito;
    @JoinColumn(name = "EMPR_EXPI_DOC", referencedColumnName = "IDEN_EMPR_EMP")
    @ManyToOne
    private MaeEmpresa emprExpiDoc;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrmDocumentoPK trmDocumentoPK;
    @Column(name = "FECH_VIGE_DOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechVigeDoc;
    @Column(name = "FECH_EMIS_DOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEmisDoc;
    @Column(name = "NUME_FOLI_DOC")
    private Integer numeFoliDoc;
    @Size(max = 300)
    @Column(name = "DESC_OBSE_DOC")
    private String descObseDoc;
    @Size(max = 200)
    @Column(name = "NOMB_RUTA_DOC")
    private String nombRutaDoc;
    @Column(name = "FLAG_FISI_DOC")
    private Character flagFisiDoc;
    @Column(name = "FLAG_PERT_DOC")
    private Character flagPertDoc;
    @Size(max = 120)
    @Column(name = "DESC_NOMB_DOC")
    private String descNombDoc;
    @Size(max = 15)
    @Column(name = "USUA_REGI_AUD")
    private String usuaRegiAud;
    @Column(name = "FECH_REGI_AUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechRegiAud;
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
    @Column(name = "FLAG_ESTA_DOC")
    private Short flagEstaDoc;
    @JoinColumn(name = "IDEN_EXPE_TRM", referencedColumnName = "IDEN_EXPE_TRM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TrmTramite trmTramite;

    public TrmDocumento() {
    }

    public TrmDocumento(TrmDocumentoPK trmDocumentoPK) {
        this.trmDocumentoPK = trmDocumentoPK;
    }

    public TrmDocumento(BigInteger idenExpeTrm, int secuDocuDoc) {
        this.trmDocumentoPK = new TrmDocumentoPK(idenExpeTrm, secuDocuDoc);
    }

    public TrmDocumentoPK getTrmDocumentoPK() {
        return trmDocumentoPK;
    }

    public void setTrmDocumentoPK(TrmDocumentoPK trmDocumentoPK) {
        this.trmDocumentoPK = trmDocumentoPK;
    }

    public Date getFechVigeDoc() {
        return fechVigeDoc;
    }

    public void setFechVigeDoc(Date fechVigeDoc) {
        this.fechVigeDoc = fechVigeDoc;
    }

    public Date getFechEmisDoc() {
        return fechEmisDoc;
    }

    public void setFechEmisDoc(Date fechEmisDoc) {
        this.fechEmisDoc = fechEmisDoc;
    }

    public Integer getNumeFoliDoc() {
        return numeFoliDoc;
    }

    public void setNumeFoliDoc(Integer numeFoliDoc) {
        this.numeFoliDoc = numeFoliDoc;
    }

    public String getDescObseDoc() {
        return descObseDoc;
    }

    public void setDescObseDoc(String descObseDoc) {
        this.descObseDoc = descObseDoc;
    }

    public String getNombRutaDoc() {
        return nombRutaDoc;
    }

    public void setNombRutaDoc(String nombRutaDoc) {
        this.nombRutaDoc = nombRutaDoc;
    }

    public Character getFlagFisiDoc() {
        return flagFisiDoc;
    }

    public void setFlagFisiDoc(Character flagFisiDoc) {
        this.flagFisiDoc = flagFisiDoc;
    }

    public Character getFlagPertDoc() {
        return flagPertDoc;
    }

    public void setFlagPertDoc(Character flagPertDoc) {
        this.flagPertDoc = flagPertDoc;
    }

    public String getDescNombDoc() {
        return descNombDoc;
    }

    public void setDescNombDoc(String descNombDoc) {
        this.descNombDoc = descNombDoc;
    }

    public String getUsuaRegiAud() {
        return usuaRegiAud;
    }

    public void setUsuaRegiAud(String usuaRegiAud) {
        this.usuaRegiAud = usuaRegiAud;
    }

    public Date getFechRegiAud() {
        return fechRegiAud;
    }

    public void setFechRegiAud(Date fechRegiAud) {
        this.fechRegiAud = fechRegiAud;
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

    public Short getFlagEstaDoc() {
        return flagEstaDoc;
    }

    public void setFlagEstaDoc(Short flagEstaDoc) {
        this.flagEstaDoc = flagEstaDoc;
    }

    public TrmTramite getTrmTramite() {
        return trmTramite;
    }

    public void setTrmTramite(TrmTramite trmTramite) {
        this.trmTramite = trmTramite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trmDocumentoPK != null ? trmDocumentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmDocumento)) {
            return false;
        }
        TrmDocumento other = (TrmDocumento) object;
        if ((this.trmDocumentoPK == null && other.trmDocumentoPK != null) || (this.trmDocumentoPK != null && !this.trmDocumentoPK.equals(other.trmDocumentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmDocumento[ trmDocumentoPK=" + trmDocumentoPK + " ]";
    }

    public MaeRequisito getMaeRequisito() {
        return maeRequisito;
    }

    public void setMaeRequisito(MaeRequisito maeRequisito) {
        this.maeRequisito = maeRequisito;
    }

    public MaeEmpresa getEmprExpiDoc() {
        return emprExpiDoc;
    }

    public void setEmprExpiDoc(MaeEmpresa emprExpiDoc) {
        this.emprExpiDoc = emprExpiDoc;
    }
    
}
