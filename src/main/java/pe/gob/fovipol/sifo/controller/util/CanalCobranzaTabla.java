/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller.util;

import pe.gob.fovipol.sifo.model.credito.CrdCanalcobra;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;

/**
 *
 * @author probook
 */
public class CanalCobranzaTabla {
    private CrdCanalcobra canalCobranza;
    private MaeEntidaddet canal;
    private boolean seleccionado;

    /**
     * @return the canalCobranza
     */
    public CrdCanalcobra getCanalCobranza() {
        return canalCobranza;
    }

    /**
     * @param canalCobranza the canalCobranza to set
     */
    public void setCanalCobranza(CrdCanalcobra canalCobranza) {
        this.canalCobranza = canalCobranza;
    }

    /**
     * @return the canal
     */
    public MaeEntidaddet getCanal() {
        return canal;
    }

    /**
     * @param canal the canal to set
     */
    public void setCanal(MaeEntidaddet canal) {
        this.canal = canal;
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
