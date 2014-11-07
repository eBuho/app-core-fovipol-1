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
public class CrdCanalcobraPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_CRED_CRD")
    private BigInteger idenCredCrd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_CANA_CDC")
    private short secuCanaCdc;

    public CrdCanalcobraPK() {
    }

    public CrdCanalcobraPK(BigInteger idenCredCrd, short secuCanaCdc) {
        this.idenCredCrd = idenCredCrd;
        this.secuCanaCdc = secuCanaCdc;
    }

    public BigInteger getIdenCredCrd() {
        return idenCredCrd;
    }

    public void setIdenCredCrd(BigInteger idenCredCrd) {
        this.idenCredCrd = idenCredCrd;
    }

    public short getSecuCanaCdc() {
        return secuCanaCdc;
    }

    public void setSecuCanaCdc(short secuCanaCdc) {
        this.secuCanaCdc = secuCanaCdc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenCredCrd != null ? idenCredCrd.hashCode() : 0);
        hash += (int) secuCanaCdc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdCanalcobraPK)) {
            return false;
        }
        CrdCanalcobraPK other = (CrdCanalcobraPK) object;
        if ((this.idenCredCrd == null && other.idenCredCrd != null) || (this.idenCredCrd != null && !this.idenCredCrd.equals(other.idenCredCrd))) {
            return false;
        }
        if (this.secuCanaCdc != other.secuCanaCdc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdCanalcobraPK[ idenCredCrd=" + idenCredCrd + ", secuCanaCdc=" + secuCanaCdc + " ]";
    }
    
}
