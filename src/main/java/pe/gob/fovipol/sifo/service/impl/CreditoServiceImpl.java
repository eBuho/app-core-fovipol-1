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
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoCuotaFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCreditoSeguroFacade;
import pe.gob.fovipol.sifo.dao.credito.CrdCuotaSeguroFacade;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.credito.CrdCreditoCuota;
import pe.gob.fovipol.sifo.model.credito.CrdCreditoCuotaPK;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.credito.CrdCreditoSeguro;
import pe.gob.fovipol.sifo.model.credito.CrdCreditoSeguroPK;
import pe.gob.fovipol.sifo.model.credito.CrdCuotaSeguro;
import pe.gob.fovipol.sifo.model.credito.CrdCuotaSeguroPK;
import pe.gob.fovipol.sifo.model.credito.CrdSimulacion;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguro;
import pe.gob.fovipol.sifo.model.maestros.MaeSeguroRango;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;
import pe.gob.fovipol.sifo.service.CreditoService;
import pe.gob.fovipol.sifo.util.Constantes;

@Stateless
public class CreditoServiceImpl implements CreditoService {

    @EJB
    private MaeSeguroRangoFacade ejbSeguroRangoFacade;
    @EJB
    private CrdCreditoCuotaFacade ejbCreditoCuotaFacade;
    @EJB
    private CrdCuotaSeguroFacade ejbCuotaSeguroFacade;
    @EJB
    private CrdCreditoFacade ejbCreditoFacade;
    @EJB
    private CrdCreditoSeguroFacade ejbCreditoSeguroFacade;

    @Override
    public BigDecimal calcularMaximaCuota(BigDecimal remuneracionConsolidada, BigDecimal descuentoMaximo, BigDecimal descuentoOficial, BigDecimal descuentoPersonal, BigDecimal combustible) {
        BigDecimal maximaCuota = BigDecimal.ZERO;
        if(remuneracionConsolidada==null)
            remuneracionConsolidada= BigDecimal.ZERO;
        if(descuentoMaximo==null)
            descuentoMaximo= BigDecimal.ZERO;
        if(descuentoOficial==null)
            descuentoOficial= BigDecimal.ZERO;
        if(descuentoPersonal==null)
            descuentoPersonal= BigDecimal.ZERO;
        if(combustible==null)
            combustible= BigDecimal.ZERO;
        maximaCuota = remuneracionConsolidada.multiply(descuentoMaximo).divide(new BigDecimal(100));
        maximaCuota = maximaCuota.add(descuentoOficial.negate());
        maximaCuota = maximaCuota.add(descuentoPersonal.negate()).add(combustible);
        return maximaCuota;
    }

    @Override
    public BigDecimal calcularMaximoPrestamo(BigDecimal totalAporte, BigInteger maximoPorProducto,
            BigDecimal otrasDeudas, BigDecimal minimaDeuda,BigDecimal otrosIngresos,BigDecimal prestamoAnterior) {
        BigDecimal maximaPrestamo = BigDecimal.ZERO;
        if(totalAporte==null)
            totalAporte= BigDecimal.ZERO;
        if(maximoPorProducto==null)
            maximoPorProducto= BigInteger.ONE;
        if(otrasDeudas==null)
            otrasDeudas= BigDecimal.ZERO;
        if(minimaDeuda==null)
            minimaDeuda= BigDecimal.ZERO;
        if(otrosIngresos==null)
            otrosIngresos= BigDecimal.ZERO;
        if(prestamoAnterior==null)
            prestamoAnterior= BigDecimal.ZERO;
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
            BigDecimal montoPrestamo, BigInteger numeroCuotas, BigDecimal cuotaMensual, Date fechaNacimiento,CrdSimulacion simulacion) {
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
            c.setSimulacion(simulacion);
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
            List<CrdCuotaSeguro> cuotasSeguro=new ArrayList<>();
            int j=1;
            for(List<MaeSeguroRango> listas:rangos){                
                if(!estaIncluido(c.getEdad(), new BigDecimal(listas.get(0).getRagnEdaiSgr()),new BigDecimal(listas.get(0).getRangEdafSgr()))){
                    listas.remove(0);
                }
                CrdCuotaSeguro cuotaSeguro=new CrdCuotaSeguro();
                cuotaSeguro.setCrdCuotaSeguroPK(new CrdCuotaSeguroPK(null, i+1, j));
                cuotaSeguro.setFlagEstaCcs(Constantes.VALOR_ESTADO_ACTIVO);
                cuotaSeguro.setIdenSeguSeg(listas.get(0).getMaeSeguro());
                BigDecimal auxiliarDegravamen = c.getSaldoInicial().multiply(listas.get(0).getTasaSeguSgr());
                auxiliarDegravamen = auxiliarDegravamen.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP);
                seguro=seguro.add(auxiliarDegravamen);
                cuotaSeguro.setImpoSeguCcs(auxiliarDegravamen);
                cuotaSeguro.setTasaSeguCcs(listas.get(0).getTasaSeguSgr());
                cuotasSeguro.add(cuotaSeguro);
                j++;
            }
            c.setCuotasSeguro(cuotasSeguro);
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

    @Override
    public boolean generarCuotas(TrmTramite tramite, CrdCredito credito,List<Cuota> cuotas,List<MaeSeguro> seguros) {
        int i=1;
        try{
            if(cuotas==null || cuotas.isEmpty())
                return false;
            credito.setImpoGadmCrd(credito.getTasaGadmCrd().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).multiply(credito.getImpoSoliCrd()));
            credito.setImpoInteCrd(cuotas.get(0).getTotalInteres());
            int j=1;
            for(MaeSeguro seguro:seguros){
                CrdCreditoSeguro creditoSeguro=new CrdCreditoSeguro();
                creditoSeguro.setCrdCreditoSeguroPK(new CrdCreditoSeguroPK(credito.getIdenCredCrd().toBigInteger(), j));
                j++;
                creditoSeguro.setCrdCredito(credito);
                creditoSeguro.setFlagEstaCrs(Constantes.VALOR_ESTADO_ACTIVO);
                creditoSeguro.setIdenSeguSeg(seguro);
                ejbCreditoSeguroFacade.edit(creditoSeguro);
            }
            for(Cuota cuota:cuotas){
                CrdCreditoCuota creditoCuota=new CrdCreditoCuota();
                creditoCuota.setCrdCreditoCuotaPK(new CrdCreditoCuotaPK(credito.getIdenCredCrd().toBigInteger(), i));
                creditoCuota.setNumeCuotCuo(cuota.getNumero()+"");
                creditoCuota.setSaldInicCuo(cuota.getSaldoInicial());
                creditoCuota.setCapiMontCuo(cuota.getCuota().add(cuota.getInteres().negate()));
                creditoCuota.setImpoSeguCuo(cuota.getDegravamen());
                creditoCuota.setInteMontCuo(cuota.getInteres());
                creditoCuota.setImpoCuotCuo(cuota.getCuota());
                creditoCuota.setTasaPteaCuo(credito.getTasaInteCrd());
                creditoCuota.setCodiSituCuo(1);
                creditoCuota.setFlagEstaCuo(Constantes.VALOR_ESTADO_ACTIVO);
                creditoCuota.setFechPagoCuo(cuota.getFechaPago());
                creditoCuota.setFechUddpCuo(cuota.getFechaPago());
                ejbCreditoCuotaFacade.edit(creditoCuota);
                for(CrdCuotaSeguro cuotaSeguro:cuota.getCuotasSeguro()){
                    cuotaSeguro.getCrdCuotaSeguroPK().setIdenCredCrd(credito.getIdenCredCrd().toBigInteger());
                    System.out.println("CUOTA SEGURO --->>>> "+cuotaSeguro.getCrdCuotaSeguroPK());
                    ejbCuotaSeguroFacade.edit(cuotaSeguro);
                }                
                i++;
            }
            ejbCreditoFacade.edit(credito);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<CrdCredito> obtenerCreditos(Date fechaInicio, Date fechaFinal,
            MaeEntidaddet moneda, Integer estado) {
        List<CrdCredito> lista = null;
        lista = ejbCreditoFacade.findByFiltros1(fechaInicio, fechaFinal, moneda, estado);
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista;
    }

    @Override
    public List<CrdCredito> obtenerCreditosProductos(Date fechaInicio, Date fechaFinal, MaeProducto producto, MaeEntidaddet moneda, Integer estado) {
        List<CrdCredito> lista = null;
        lista = ejbCreditoFacade.findByFiltros2(fechaInicio, fechaFinal, producto, moneda, estado);
        if(lista==null || lista.isEmpty())
            return null;
        else
            return lista;
    }

}
