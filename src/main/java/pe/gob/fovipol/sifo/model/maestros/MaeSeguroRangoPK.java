/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.model.maestros;

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
public class MaeSeguroRangoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_SEGU_SEG")
    private BigInteger idenSeguSeg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_SEGU_SGD")
    private int secuSeguSgd;

    public MaeSeguroRangoPK() {
    }

    public MaeSeguroRangoPK(BigInteger idenSeguSeg, int secuSeguSgd) {
        this.idenSeguSeg = idenSeguSeg;
        this.secuSeguSgd = secuSeguSgd;
    }

    public BigInteger getIdenSeguSeg() {
        return idenSeguSeg;
    }

    public void setIdenSeguSeg(BigInteger idenSeguSeg) {
        this.idenSeguSeg = idenSeguSeg;
    }

    public int getSecuSeguSgd() {
        return secuSeguSgd;
    }

    public void setSecuSeguSgd(int secuSeguSgd) {
        this.secuSeguSgd = secuSeguSgd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenSeguSeg != null ? idenSeguSeg.hashCode() : 0);
        hash += (int) secuSeguSgd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeSeguroRangoPK)) {
            return false;
        }
        MaeSeguroRangoPK other = (MaeSeguroRangoPK) object;
        if ((this.idenSeguSeg == null && other.idenSeguSeg != null) || (this.idenSeguSeg != null && !this.idenSeguSeg.equals(other.idenSeguSeg))) {
            return false;
        }
        if (this.secuSeguSgd != other.secuSeguSgd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeSeguroRangoPK[ idenSeguSeg=" + idenSeguSeg + ", secuSeguSgd=" + secuSeguSgd + " ]";
    }
    
}
