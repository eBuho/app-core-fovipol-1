/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.credito;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author probook
 */
@Embeddable
public class CrdCreditoSeguroPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_CRED_CRD")
    private BigInteger idenCredCrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_SEGU_CRS")
    private int secuSeguCrs;

    public CrdCreditoSeguroPK() {
    }

    public CrdCreditoSeguroPK(BigInteger idenCredCrd, int secuSeguCrs) {
        this.idenCredCrd = idenCredCrd;
        this.secuSeguCrs = secuSeguCrs;
    }

    public BigInteger getIdenCredCrd() {
        return idenCredCrd;
    }

    public void setIdenCredCrd(BigInteger idenCredCrd) {
        this.idenCredCrd = idenCredCrd;
    }

    public int getSecuSeguCrs() {
        return secuSeguCrs;
    }

    public void setSecuSeguCrs(int secuSeguCrs) {
        this.secuSeguCrs = secuSeguCrs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenCredCrd != null ? idenCredCrd.hashCode() : 0);
        hash += (int) secuSeguCrs;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCreditoSeguroPK)) {
            return false;
        }
        CrdCreditoSeguroPK other = (CrdCreditoSeguroPK) object;
        if ((this.idenCredCrd == null && other.idenCredCrd != null) || (this.idenCredCrd != null && !this.idenCredCrd.equals(other.idenCredCrd))) {
            return false;
        }
        if (this.secuSeguCrs != other.secuSeguCrs) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCreditoSeguroPK[ idenCredCrd=" + idenCredCrd + ", secuSeguCrs=" + secuSeguCrs + " ]";
    }
    
}
