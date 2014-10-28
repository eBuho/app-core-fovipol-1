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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;

/**
 *
 * @author ebuho
 */
@Entity
@Table(name = "CRD_SIMULACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrdSimulacion.findAll", query = "SELECT c FROM CrdSimulacion c"),
    @NamedQuery(name = "CrdSimulacion.findBySecuSimuSim", query = "SELECT c FROM CrdSimulacion c WHERE c.secuSimuSim = :secuSimuSim"),
    @NamedQuery(name = "CrdSimulacion.findByTasaInteSim", query = "SELECT c FROM CrdSimulacion c WHERE c.tasaInteSim = :tasaInteSim"),
    @NamedQuery(name = "CrdSimulacion.findByNumeCuotSim", query = "SELECT c FROM CrdSimulacion c WHERE c.numeCuotSim = :numeCuotSim")})
public class CrdSimulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "SECU_SIMU_SIM")
    private String secuSimuSim;
    @Column(name = "TASA_INTE_SIM")
    private BigInteger tasaInteSim;
    @Size(max = 20)
    @Column(name = "NUME_CUOT_SIM")
    private String numeCuotSim;
    @JoinColumn(name = "IDEN_PROD_PRD", referencedColumnName = "IDEN_PROD_PRD")
    @ManyToOne
    private MaeProducto idenProdPrd;

    public CrdSimulacion() {
    }

    public CrdSimulacion(String secuSimuSim) {
        this.secuSimuSim = secuSimuSim;
    }

    public String getSecuSimuSim() {
        return secuSimuSim;
    }

    public void setSecuSimuSim(String secuSimuSim) {
        this.secuSimuSim = secuSimuSim;
    }

    public BigInteger getTasaInteSim() {
        return tasaInteSim;
    }

    public void setTasaInteSim(BigInteger tasaInteSim) {
        this.tasaInteSim = tasaInteSim;
    }

    public String getNumeCuotSim() {
        return numeCuotSim;
    }

    public void setNumeCuotSim(String numeCuotSim) {
        this.numeCuotSim = numeCuotSim;
    }

    public MaeProducto getIdenProdPrd() {
        return idenProdPrd;
    }

    public void setIdenProdPrd(MaeProducto idenProdPrd) {
        this.idenProdPrd = idenProdPrd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuSimuSim != null ? secuSimuSim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrdSimulacion)) {
            return false;
        }
        CrdSimulacion other = (CrdSimulacion) object;
        if ((this.secuSimuSim == null && other.secuSimuSim != null) || (this.secuSimuSim != null && !this.secuSimuSim.equals(other.secuSimuSim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.gob.fovipol.sifo.model.tramite.CrdSimulacion[ secuSimuSim=" + secuSimuSim + " ]";
    }
    
}
