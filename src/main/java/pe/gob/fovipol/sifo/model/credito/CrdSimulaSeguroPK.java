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
public class CrdSimulaSeguroPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_SIMU_SIM")
    private BigInteger idenSimuSim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_SEGU_SSG")
    private int secuSeguSsg;

    public CrdSimulaSeguroPK() {
    }

    public CrdSimulaSeguroPK(BigInteger idenSimuSim, int secuSeguSsg) {
        this.idenSimuSim = idenSimuSim;
        this.secuSeguSsg = secuSeguSsg;
    }

    public BigInteger getIdenSimuSim() {
        return idenSimuSim;
    }

    public void setIdenSimuSim(BigInteger idenSimuSim) {
        this.idenSimuSim = idenSimuSim;
    }

    public int getSecuSeguSsg() {
        return secuSeguSsg;
    }

    public void setSecuSeguSsg(int secuSeguSsg) {
        this.secuSeguSsg = secuSeguSsg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenSimuSim != null ? idenSimuSim.hashCode() : 0);
        hash += (int) secuSeguSsg;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdSimulaSeguroPK)) {
            return false;
        }
        CrdSimulaSeguroPK other = (CrdSimulaSeguroPK) object;
        if ((this.idenSimuSim == null && other.idenSimuSim != null) || (this.idenSimuSim != null && !this.idenSimuSim.equals(other.idenSimuSim))) {
            return false;
        }
        if (this.secuSeguSsg != other.secuSeguSsg) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.credito.CrdSimulaSeguroPK[ idenSimuSim=" + idenSimuSim + ", secuSeguSsg=" + secuSeguSsg + " ]";
    }
    
}
