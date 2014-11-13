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
public class CrdCuotaSeguroPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_CRED_CRD")
    private BigInteger idenCredCrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_CUOT_CUO")
    private int secuCuotCuo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_SEGU_CCS")
    private int secuSeguCcs;

    public CrdCuotaSeguroPK() {
    }

    public CrdCuotaSeguroPK(BigInteger idenCredCrd, int secuCuotCuo, int secuSeguCcs) {
        this.idenCredCrd = idenCredCrd;
        this.secuCuotCuo = secuCuotCuo;
        this.secuSeguCcs = secuSeguCcs;
    }

    public BigInteger getIdenCredCrd() {
        return idenCredCrd;
    }

    public void setIdenCredCrd(BigInteger idenCredCrd) {
        this.idenCredCrd = idenCredCrd;
    }

    public int getSecuCuotCuo() {
        return secuCuotCuo;
    }

    public void setSecuCuotCuo(int secuCuotCuo) {
        this.secuCuotCuo = secuCuotCuo;
    }

    public int getSecuSeguCcs() {
        return secuSeguCcs;
    }

    public void setSecuSeguCcs(int secuSeguCcs) {
        this.secuSeguCcs = secuSeguCcs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenCredCrd != null ? idenCredCrd.hashCode() : 0);
        hash += (int) secuCuotCuo;
        hash += (int) secuSeguCcs;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCuotaSeguroPK)) {
            return false;
        }
        CrdCuotaSeguroPK other = (CrdCuotaSeguroPK) object;
        if ((this.idenCredCrd == null && other.idenCredCrd != null) || (this.idenCredCrd != null && !this.idenCredCrd.equals(other.idenCredCrd))) {
            return false;
        }
        if (this.secuCuotCuo != other.secuCuotCuo) {
            return false;
        }
        if (this.secuSeguCcs != other.secuSeguCcs) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCuotaSeguroPK[ idenCredCrd=" + idenCredCrd + ", secuCuotCuo=" + secuCuotCuo + ", secuSeguCcs=" + secuSeguCcs + " ]";
    }
    
}
