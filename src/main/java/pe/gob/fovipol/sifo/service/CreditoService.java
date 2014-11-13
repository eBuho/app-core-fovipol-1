/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import pe.gob.fovipol.sifo.controller.util.Cuota;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author probook
 */
public interface CreditoService {
    public boolean generarCuotas(TrmTramite tramite,CrdCredito credito,List<Cuota> cuotas,List<MaeSeguro> seguros);
    //Calcula la máxima cuota en una simulación
    public BigDecimal calcularMaximaCuota(BigDecimal remuneracionConsolidada,BigDecimal descuentoMaximo,BigDecimal descuentoOficial,
            BigDecimal descuentoPersonal,BigDecimal combustible);
    //Calcula el máximo préstamo en una simulación
    public BigDecimal calcularMaximoPrestamo(BigDecimal totalAporte,BigInteger maximoPorProducto,
            BigDecimal otrasDeudas,BigDecimal minimaDeuda,BigDecimal otrosIngresos,BigDecimal prestamoAnterior);
    //Calcula la edad de una persona
    public int calcularEdad(Date nacimiento,Date fin);
    //Calcula el monto mensual a pagar para un préstamo (monto) a n(numeroCuotas) con un interes
    public BigDecimal calcularCuotaMontoMensual(BigDecimal monto,int numeroCuotas,BigDecimal interes);
    //Calcula el monto de prestamo a partir del monto mensual a pagar, el número de cuotas y el interes
    public BigDecimal calcularCuotaMontoTotal(BigDecimal montoMensual,int numeroCuotas,BigDecimal interes);
    //Devuelve las cuotas
    public List<Cuota> calcularCuotas(List<MaeSeguro> seguros, int ciclica, BigDecimal interes, 
            BigDecimal montoPrestamo,BigInteger numeroCuotas,BigDecimal cuotaMensual,
            Date fechaNacimiento,CrdSimulacion simulacion);
    public boolean comprobarEdadFinPago(Date fechaNacimiento,int ciclica,int numeroCuotas,List<MaeSeguro> seguros);

    /**
     *
     * @param fechaInicio
     * @param fechaFinal
     * @param monedaId
     * @param estado
     * @return
     */
    public List<CrdCredito> obtenerCreditos(Date fechaInicio, Date fechaFinal,
            MaeEntidaddet moneda, Integer estado);
    public List<CrdCredito> obtenerCreditosProductos(Date fechaInicio, Date fechaFinal,
            MaeProducto producto, MaeEntidaddet moneda, Integer estado);
}
