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
public class TrmMovimientoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_EXPE_TRM")
    private BigInteger idenExpeTrm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_MOVI_MVM")
    private int secuMoviMvm;

    public TrmMovimientoPK() {
    }

    public TrmMovimientoPK(BigInteger idenExpeTrm, int secuMoviMvm) {
        this.idenExpeTrm = idenExpeTrm;
        this.secuMoviMvm = secuMoviMvm;
    }

    public BigInteger getIdenExpeTrm() {
        return idenExpeTrm;
    }

    public void setIdenExpeTrm(BigInteger idenExpeTrm) {
        this.idenExpeTrm = idenExpeTrm;
    }

    public int getSecuMoviMvm() {
        return secuMoviMvm;
    }

    public void setSecuMoviMvm(int secuMoviMvm) {
        this.secuMoviMvm = secuMoviMvm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenExpeTrm != null ? idenExpeTrm.hashCode() : 0);
        hash += (int) secuMoviMvm;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmMovimientoPK)) {
            return false;
        }
        TrmMovimientoPK other = (TrmMovimientoPK) object;
        if ((this.idenExpeTrm == null && other.idenExpeTrm != null) || (this.idenExpeTrm != null && !this.idenExpeTrm.equals(other.idenExpeTrm))) {
            return false;
        }
        if (this.secuMoviMvm != other.secuMoviMvm) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmMovimientoPK[ idenExpeTrm=" + idenExpeTrm + ", secuMoviMvm=" + secuMoviMvm + " ]";
    }
    
}
