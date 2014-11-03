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
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;

/**
 *
 * @author probook
 */
public interface CreditoService {
    //Calcula la máxima cuota en una simulación
    public BigDecimal calcularMaximaCuota(BigDecimal remuneracionConsolidada,BigDecimal descuentoMaximo,BigDecimal descuentoOficial,
            BigDecimal descuentoPersonal,BigDecimal combustible);
    //Calcula el máximo préstamo en una simulación
    public BigDecimal calcularMaximoPrestamo(BigDecimal totalAporte,BigInteger maximoPorProducto,
            BigDecimal otrasDeudas,BigDecimal minimaDeuda,BigDecimal otrosIngresos);
    //Calcula la edad de una persona
    public int calcularEdad(Date nacimiento,Date fin);
    //Calcula el monto mensual a pagar para un préstamo (monto) a n(numeroCuotas) con un interes
    public BigDecimal calcularCuotaMontoMensual(BigDecimal monto,int numeroCuotas,BigDecimal interes);
    //Calcula el monto de prestamo a partir del monto mensual a pagar, el número de cuotas y el interes
    public BigDecimal calcularCuotaMontoTotal(BigDecimal montoMensual,int numeroCuotas,BigDecimal interes);
    public List<Cuota> calcularCuotas(List<MaeSeguro> seguros, int ciclica, BigDecimal interes, 
            BigDecimal montoPrestamo,BigInteger numeroCuotas,BigDecimal cuotaMensual,Date fechaNacimiento);
    public boolean comprobarEdadFinPago(Date fechaNacimiento,int ciclica,int numeroCuotas,List<MaeSeguro> seguros);
}
