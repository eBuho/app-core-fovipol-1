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
public class MaeRequisitoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PROC_PRC")
    private BigInteger idenProcPrc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_MAE_REQ")
    private BigInteger secuMaeReq;

    public MaeRequisitoPK() {
    }

    public MaeRequisitoPK(BigInteger idenProcPrc, BigInteger secuMaeReq) {
        this.idenProcPrc = idenProcPrc;
        this.secuMaeReq = secuMaeReq;
    }

    public BigInteger getIdenProcPrc() {
        return idenProcPrc;
    }

    public void setIdenProcPrc(BigInteger idenProcPrc) {
        this.idenProcPrc = idenProcPrc;
    }

    public BigInteger getSecuMaeReq() {
        return secuMaeReq;
    }

    public void setSecuMaeReq(BigInteger secuMaeReq) {
        this.secuMaeReq = secuMaeReq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenProcPrc != null ? idenProcPrc.hashCode() : 0);
        hash += (secuMaeReq != null ? secuMaeReq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeRequisitoPK)) {
            return false;
        }
        MaeRequisitoPK other = (MaeRequisitoPK) object;
        if ((this.idenProcPrc == null && other.idenProcPrc != null) || (this.idenProcPrc != null && !this.idenProcPrc.equals(other.idenProcPrc))) {
            return false;
        }
        if ((this.secuMaeReq == null && other.secuMaeReq != null) || (this.secuMaeReq != null && !this.secuMaeReq.equals(other.secuMaeReq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeRequisitoPK[ idenProcPrc=" + idenProcPrc + ", secuMaeReq=" + secuMaeReq + " ]";
    }
    
}
