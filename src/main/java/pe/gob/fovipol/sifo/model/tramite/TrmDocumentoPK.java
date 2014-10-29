/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.tramite;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ebuho
 */
@Embeddable
public class TrmDocumentoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_EXPE_TRM")
    private BigInteger idenExpeTrm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_DOCU_DOC")
    private int secuDocuDoc;

    public TrmDocumentoPK() {
    }

    public TrmDocumentoPK(BigInteger idenExpeTrm, int secuDocuDoc) {
        this.idenExpeTrm = idenExpeTrm;
        this.secuDocuDoc = secuDocuDoc;
    }

    public BigInteger getIdenExpeTrm() {
        return idenExpeTrm;
    }

    public void setIdenExpeTrm(BigInteger idenExpeTrm) {
        this.idenExpeTrm = idenExpeTrm;
    }

    public int getSecuDocuDoc() {
        return secuDocuDoc;
    }

    public void setSecuDocuDoc(int secuDocuDoc) {
        this.secuDocuDoc = secuDocuDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenExpeTrm != null ? idenExpeTrm.hashCode() : 0);
        hash += (int) secuDocuDoc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmDocumentoPK)) {
            return false;
        }
        TrmDocumentoPK other = (TrmDocumentoPK) object;
        if ((this.idenExpeTrm == null && other.idenExpeTrm != null) || (this.idenExpeTrm != null && !this.idenExpeTrm.equals(other.idenExpeTrm))) {
            return false;
        }
        if (this.secuDocuDoc != other.secuDocuDoc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK[ idenExpeTrm=" + idenExpeTrm + ", secuDocuDoc=" + secuDocuDoc + " ]";
    }
    
}
