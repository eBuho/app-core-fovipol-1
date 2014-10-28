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
public class TrmEstatramHisPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEN_EXPE_TRM")
    private BigInteger idenExpeTrm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECU_ESTA_HIS")
    private int secuEstaHis;

    public TrmEstatramHisPK() {
    }

    public TrmEstatramHisPK(BigInteger idenExpeTrm, int secuEstaHis) {
        this.idenExpeTrm = idenExpeTrm;
        this.secuEstaHis = secuEstaHis;
    }

    public BigInteger getIdenExpeTrm() {
        return idenExpeTrm;
    }

    public void setIdenExpeTrm(BigInteger idenExpeTrm) {
        this.idenExpeTrm = idenExpeTrm;
    }

    public int getSecuEstaHis() {
        return secuEstaHis;
    }

    public void setSecuEstaHis(int secuEstaHis) {
        this.secuEstaHis = secuEstaHis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenExpeTrm != null ? idenExpeTrm.hashCode() : 0);
        hash += (int) secuEstaHis;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrmEstatramHisPK)) {
            return false;
        }
        TrmEstatramHisPK other = (TrmEstatramHisPK) object;
        if ((this.idenExpeTrm == null && other.idenExpeTrm != null) || (this.idenExpeTrm != null && !this.idenExpeTrm.equals(other.idenExpeTrm))) {
            return false;
        }
        if (this.secuEstaHis != other.secuEstaHis) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.TrmEstatramHisPK[ idenExpeTrm=" + idenExpeTrm + ", secuEstaHis=" + secuEstaHis + " ]";
    }
    
}
