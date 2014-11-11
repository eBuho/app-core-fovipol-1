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
public class MaeProcesoestadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_PROC_PRC")
    private BigInteger idenProcPrc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_ESTA_PRE")
    private int secuEstaPre;

    public MaeProcesoestadoPK() {
    }

    public MaeProcesoestadoPK(BigInteger idenProcPrc, int secuEstaPre) {
        this.idenProcPrc = idenProcPrc;
        this.secuEstaPre = secuEstaPre;
    }

    public BigInteger getIdenProcPrc() {
        return idenProcPrc;
    }

    public void setIdenProcPrc(BigInteger idenProcPrc) {
        this.idenProcPrc = idenProcPrc;
    }

    public int getSecuEstaPre() {
        return secuEstaPre;
    }

    public void setSecuEstaPre(int secuEstaPre) {
        this.secuEstaPre = secuEstaPre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenProcPrc != null ? idenProcPrc.hashCode() : 0);
        hash += (int) secuEstaPre;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaeProcesoestadoPK)) {
            return false;
        }
        MaeProcesoestadoPK other = (MaeProcesoestadoPK) object;
        if ((this.idenProcPrc == null && other.idenProcPrc != null) || (this.idenProcPrc != null && !this.idenProcPrc.equals(other.idenProcPrc))) {
            return false;
        }
        if (this.secuEstaPre != other.secuEstaPre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.maestros.MaeProcesoestadoPK[ idenProcPrc=" + idenProcPrc + ", secuEstaPre=" + secuEstaPre + " ]";
    }
    
}
