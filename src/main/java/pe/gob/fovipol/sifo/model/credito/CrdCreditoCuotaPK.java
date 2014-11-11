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
public class CrdCreditoCuotaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_CRED_CRD")
    private BigInteger idenCredCrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_CUOT_CUO")
    private int secuCuotCuo;

    public CrdCreditoCuotaPK() {
    }

    public CrdCreditoCuotaPK(BigInteger idenCredCrd, int secuCuotCuo) {
        this.idenCredCrd = idenCredCrd;
        this.secuCuotCuo = secuCuotCuo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenCredCrd != null ? idenCredCrd.hashCode() : 0);
        hash += (int) secuCuotCuo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCreditoCuotaPK)) {
            return false;
        }
        CrdCreditoCuotaPK other = (CrdCreditoCuotaPK) object;
        if ((this.idenCredCrd == null && other.idenCredCrd != null) || (this.idenCredCrd != null && !this.idenCredCrd.equals(other.idenCredCrd))) {
            return false;
        }
        if (this.secuCuotCuo != other.secuCuotCuo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK[ idenCredCrd=" + idenCredCrd + ", secuCuotCuo=" + secuCuotCuo + " ]";
    }
    
}
