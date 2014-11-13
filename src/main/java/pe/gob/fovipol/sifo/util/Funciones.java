package pe.gob.fovipol.sifo.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ebuho
 */
public class Funciones {
    public static Date fechaInicioMes(Date fecha){
        int mes = fecha.getMonth();
        int anio = fecha.getYear();
        Calendar calendario = Calendar.getInstance();
        calendario.set(anio, mes, 1);
        return calendario.getTime();
    }
}
