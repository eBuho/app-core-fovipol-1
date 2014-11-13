/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.controller.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import pe.gob.fovipol.sifo.model.credito.CrdCuotaSeguro;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;

/**
 *
 * @author probook
 */
public class Cuota {
    private int numero;
    private Date fechaPago;
    private BigDecimal saldoInicial;
    private BigDecimal capital;
    private BigDecimal interes;
    private BigDecimal cuota;
    private BigDecimal degravamen;
    private int edad;
    private BigDecimal amortizado;
    private BigDecimal totalAmortizacion;
    private BigDecimal totalInteres;
    private BigDecimal totalSeguro;
    private BigDecimal totalCuota;
    private List<CrdCuotaSeguro> cuotasSeguro;
    private CrdSimulacion simulacion;
    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the saldoInicial
     */
    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    /**
     * @param saldoInicial the saldoInicial to set
     */
    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    /**
     * @return the capital
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * @return the interes
     */
    public BigDecimal getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    /**
     * @return the cuota
     */
    public BigDecimal getCuota() {
        return cuota;
    }

    /**
     * @param cuota the cuota to set
     */
    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    /**
     * @return the amortizado
     */
    public BigDecimal getAmortizado() {
        return amortizado;
    }

    /**
     * @param amortizado the amortizado to set
     */
    public void setAmortizado(BigDecimal amortizado) {
        this.amortizado = amortizado;
    }

    /**
     * @return the degravamen
     */
    public BigDecimal getDegravamen() {
        return degravamen;
    }

    /**
     * @param degravamen the degravamen to set
     */
    public void setDegravamen(BigDecimal degravamen) {
        this.degravamen = degravamen;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the totalAmortizacion
     */
    public BigDecimal getTotalAmortizacion() {
        return totalAmortizacion;
    }

    /**
     * @param totalAmortizacion the totalAmortizacion to set
     */
    public void setTotalAmortizacion(BigDecimal totalAmortizacion) {
        this.totalAmortizacion = totalAmortizacion;
    }

    /**
     * @return the totalInteres
     */
    public BigDecimal getTotalInteres() {
        return totalInteres;
    }

    /**
     * @param totalInteres the totalInteres to set
     */
    public void setTotalInteres(BigDecimal totalInteres) {
        this.totalInteres = totalInteres;
    }

    /**
     * @return the totalSeguro
     */
    public BigDecimal getTotalSeguro() {
        return totalSeguro;
    }

    /**
     * @param totalSeguro the totalSeguro to set
     */
    public void setTotalSeguro(BigDecimal totalSeguro) {
        this.totalSeguro = totalSeguro;
    }

    /**
     * @return the totalCuota
     */
    public BigDecimal getTotalCuota() {
        return totalCuota;
    }

    /**
     * @param totalCuota the totalCuota to set
     */
    public void setTotalCuota(BigDecimal totalCuota) {
        this.totalCuota = totalCuota;
    }

    /**
     * @return the cuotasSeguro
     */
    public List<CrdCuotaSeguro> getCuotasSeguro() {
        return cuotasSeguro;
    }

    /**
     * @param cuotasSeguro the cuotasSeguro to set
     */
    public void setCuotasSeguro(List<CrdCuotaSeguro> cuotasSeguro) {
        this.cuotasSeguro = cuotasSeguro;
    }

    /**
     * @return the simulacion
     */
    public CrdSimulacion getSimulacion() {
        return simulacion;
    }

    /**
     * @param simulacion the simulacion to set
     */
    public void setSimulacion(CrdSimulacion simulacion) {
        this.simulacion = simulacion;
    }
}
