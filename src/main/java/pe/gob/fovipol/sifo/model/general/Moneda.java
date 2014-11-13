package pe.gob.fovipol.sifo.model.general;

import java.io.Serializable;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;

/**
 *
 * @author ebuho
 */
public class Moneda implements Serializable{
    private int codiMoneMon = 0;
    private String nombMoneMon = "";

    public Moneda() {
    }

    public Moneda(MaeEntidaddet detalle) {
        this.codiMoneMon = detalle.getSecuEntiDet();
        this.nombMoneMon = detalle.getValoCaduDet();
    }

    /**
     * @return the codiMoneMon
     */
    public int getCodiMoneMon() {
        return codiMoneMon;
    }

    /**
     * @param codiMoneMon the codiMoneMon to set
     */
    public void setCodiMoneMon(int codiMoneMon) {
        this.codiMoneMon = codiMoneMon;
    }

    /**
     * @return the nombMoneMon
     */
    public String getNombMoneMon() {
        return nombMoneMon;
    }

    /**
     * @param nombMoneMon the nombMoneMon to set
     */
    public void setNombMoneMon(String nombMoneMon) {
        this.nombMoneMon = nombMoneMon;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if (this.codiMoneMon != other.codiMoneMon) {
            return false;
        }
        return true;
    }
}
