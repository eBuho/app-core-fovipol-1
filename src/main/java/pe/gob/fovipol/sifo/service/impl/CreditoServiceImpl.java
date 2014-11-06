package pe.gob.fovipol.sifo.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pe.gob.fovipol.sifo.controller.util.Cuota;
import pe.gob.fovipol.sifo.dao.MaeSeguroRangoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguroRango;
import pe.gob.fovipol.sifo.service.CreditoService;

@Stateless
public class CreditoServiceImpl implements CreditoService {

    @EJB
    private MaeSeguroRangoFacade ejbSeguroRangoFacade;

    @Override
    public BigDecimal calcularMaximaCuota(BigDecimal remuneracionConsolidada, BigDecimal descuentoMaximo, BigDecimal descuentoOficial, BigDecimal descuentoPersonal, BigDecimal combustible) {
        BigDecimal maximaCuota = BigDecimal.ZERO;
        maximaCuota = remuneracionConsolidada.multiply(descuentoMaximo).divide(new BigDecimal(100));
        maximaCuota = maximaCuota.add(descuentoOficial.negate());
        maximaCuota = maximaCuota.add(descuentoPersonal.negate()).add(combustible);
        return maximaCuota;
    }

    @Override
    public BigDecimal calcularMaximoPrestamo(BigDecimal totalAporte, BigInteger maximoPorProducto,
            BigDecimal otrasDeudas, BigDecimal minimaDeuda,BigDecimal otrosIngresos,BigDecimal prestamoAnterior) {
        BigDecimal maximaPrestamo = BigDecimal.ZERO;
        maximaPrestamo = totalAporte.multiply(new BigDecimal(maximoPorProducto));
        maximaPrestamo=maximaPrestamo.add(prestamoAnterior.negate());
        if(otrosIngresos==null || otrosIngresos.compareTo(BigDecimal.ZERO)==0){
            if (minimaDeuda != null && minimaDeuda != BigDecimal.ZERO && otrasDeudas.compareTo(minimaDeuda) == 1)
                maximaPrestamo = maximaPrestamo.add(minimaDeuda).add(otrasDeudas.negate());
        }                        
        return maximaPrestamo;
    }

    @Override
    public int calcularEdad(Date nacimiento, Date fin) {
        Calendar nac = Calendar.getInstance();
        nac.setTime(nacimiento);
        Calendar hoy = Calendar.getInstance();
        hoy.setTime(fin);
        int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
        if (hoy.get(Calendar.MONTH) < nac.get(Calendar.MONTH)) {
            edad--;
        } else if (hoy.get(Calendar.MONTH) == nac.get(Calendar.MONTH)
                && hoy.get(Calendar.DAY_OF_MONTH) < nac.get(Calendar.DAY_OF_MONTH)) {
            edad--;
        }
        return edad;
    }

    @Override
    public BigDecimal calcularCuotaMontoMensual(BigDecimal monto, int numeroCuotas, BigDecimal interes) {
        BigDecimal montoMensual;
        double tem=interes.divide(new BigDecimal(100)).doubleValue()+1;
        double expo=BigDecimal.ONE.divide(new BigDecimal(12),10,RoundingMode.HALF_UP).doubleValue();
        tem=Math.pow(tem, expo);
        tem=tem-1;
        BigDecimal tasa = new BigDecimal(tem);
        montoMensual = monto.multiply(tasa);
        BigDecimal auxi = tasa.add(BigDecimal.ONE);
        auxi = auxi.pow(numeroCuotas);
        montoMensual = montoMensual.multiply(auxi).divide(auxi.add(BigDecimal.ONE.negate()), 2, RoundingMode.HALF_UP);
        return montoMensual;
    }

    @Override
    public BigDecimal calcularCuotaMontoTotal(BigDecimal montoMensual, int numeroCuotas, BigDecimal interes) {
        BigDecimal totPagar;
        double tem=interes.divide(new BigDecimal(100)).doubleValue()+1;
        double expo=BigDecimal.ONE.divide(new BigDecimal(12),10,RoundingMode.HALF_UP).doubleValue();
        tem=Math.pow(tem, expo);
        tem=tem-1;
        BigDecimal tasa = new BigDecimal(tem);
        BigDecimal auxi = tasa.add(BigDecimal.ONE);
        auxi = auxi.pow(numeroCuotas);
        totPagar = montoMensual.multiply(auxi.add(BigDecimal.ONE.negate()));
        totPagar = totPagar.divide(auxi.multiply(tasa), 2, RoundingMode.HALF_UP);
        return totPagar;
    }

    @Override
    public List<Cuota> calcularCuotas(List<MaeSeguro> seguros, int ciclica, BigDecimal interes,
            BigDecimal montoPrestamo, BigInteger numeroCuotas, BigDecimal cuotaMensual, Date fechaNacimiento) {
        List<Cuota> cuotasSimulacion = new ArrayList<>();
        List<List<MaeSeguroRango>> rangos;
        rangos=new ArrayList<>();
        for(MaeSeguro aux:seguros){
            List<MaeSeguroRango> lista=ejbSeguroRangoFacade.findBySeguro(aux.getIdenSeguSeg());
            rangos.add(lista);            
        }
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, ciclica);
        int mes;
        double tem=interes.divide(new BigDecimal(100)).doubleValue()+1;
        double expo=BigDecimal.ONE.divide(new BigDecimal(12),10,RoundingMode.HALF_UP).doubleValue();
        tem=Math.pow(tem, expo);
        tem=tem-1;
        BigDecimal tasa = new BigDecimal(tem);
        BigDecimal montoaux = montoPrestamo;
        BigDecimal totalAmortizacion = BigDecimal.ZERO;
        BigDecimal totalSeguro = BigDecimal.ZERO;
        BigDecimal totalCuota = BigDecimal.ZERO;
        BigDecimal totalInteres = BigDecimal.ZERO;
        for (int i = 0; i < numeroCuotas.intValue(); i++) {
            Cuota c = new Cuota();
            mes = today.get(Calendar.MONTH);
            today.set(Calendar.MONTH, mes + 1);
            c.setSaldoInicial(montoaux);
            c.setCuota(cuotaMensual);
            c.setInteres(montoaux.multiply(tasa).divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));
            c.setAmortizado(c.getCuota().add(c.getInteres().negate()));
            montoaux = montoaux.add(c.getAmortizado().negate());
            c.setNumero(i + 1);
            c.setFechaPago(today.getTime());
            c.setEdad(calcularEdad(fechaNacimiento, c.getFechaPago()));
            totalAmortizacion = totalAmortizacion.add(c.getAmortizado());
            totalCuota = totalCuota.add(c.getCuota());
            totalInteres = totalInteres.add(c.getInteres());
            BigDecimal seguro=BigDecimal.ZERO;
            for(List<MaeSeguroRango> listas:rangos){
                if(!estaIncluido(c.getEdad(), new BigDecimal(listas.get(0).getRagnEdaiSgr()),new BigDecimal(listas.get(0).getRangEdafSgr()))){
                    listas.remove(0);
                }
                BigDecimal auxiliarDegravamen = c.getSaldoInicial().multiply(listas.get(0).getTasaSeguSgr());
                auxiliarDegravamen = auxiliarDegravamen.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP);
                seguro=seguro.add(auxiliarDegravamen);
            }
            c.setDegravamen(seguro);            
            totalSeguro = totalSeguro.add(c.getDegravamen());
            cuotasSimulacion.add(c);
        }
        cuotasSimulacion.get(0).setTotalAmortizacion(totalAmortizacion);
        cuotasSimulacion.get(0).setTotalCuota(totalCuota);
        cuotasSimulacion.get(0).setTotalInteres(totalInteres);
        cuotasSimulacion.get(0).setTotalSeguro(totalSeguro);
        return cuotasSimulacion;
    }

    public boolean estaIncluido(int edad, BigDecimal rangoInferior, BigDecimal rangoSuperior) {
        BigDecimal auxBigDecimal = new BigDecimal(edad);
        if (rangoInferior.compareTo(auxBigDecimal) != 1 && auxBigDecimal.compareTo(rangoSuperior) != 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean comprobarEdadFinPago(Date fechaNacimiento, int ciclica, int numeroCuotas, List<MaeSeguro> seguros) {
        int edadFinPrestamo;
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, ciclica);
        today.set(Calendar.MONTH,today.get(Calendar.MONTH)+numeroCuotas);
        edadFinPrestamo=calcularEdad(fechaNacimiento, today.getTime());
        boolean estaOk=true;
        for(MaeSeguro aux:seguros){
            if(!ejbSeguroRangoFacade.findByEdad(aux.getIdenSeguSeg(), edadFinPrestamo))
                estaOk=false;
        }
        return estaOk;
    }
}
