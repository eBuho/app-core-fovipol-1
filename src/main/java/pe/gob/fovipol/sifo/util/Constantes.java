package pe.gob.fovipol.sifo.util;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean(name = "constantes")
@ApplicationScoped
public class Constantes implements Serializable {
    public static final String ENTIDAD_TIPO_AREA = "TIPOAREAARE";
    //Entidad Grado Parentesco
    public static final String ENTIDAD_GRADO_PARENTESCO = "GRADPAREPER";
    //Entidad Tipo Moneda
    public static final String ENTIDAD_TIPO_MONEDA = "CODIMONECRD";
    //Entidad Grado Parentesco
    public static final String ENTIDAD_PAGOS_SOCIO = "ENTIPAGOSOC";
    //Entidad Prioridad Tramite
    public static final String ENTIDAD_PRIORIDAD_TRAMITE = "CODIPRIOTRM";
    //Entidad Modalidad Tramite
    public static final String ENTIDAD_MODALIDAD_TRAMITE = "CODIMODATRM";
    //Entidad Tipo Tramite
    public static final String ENTIDAD_TIPO_TRAMITE = "TIPOTRAMTRM";
    //Entidad Canal Cobranza
    public static final String ENTIDAD_CANAL_COBRANZA = "CODICANACOB";
    //Estado Activo
    public static final String ESTADO_ACTIVO = "ACTIVO";
    //Estado Inactivo
    public static final String ESTADO_INACTIVO = "INACTIVO";
    //Valor Estado Inactivo
    public static final Short VALOR_ESTADO_INACTIVO = 0;
    //Valor Estado Activo
    public static final Short VALOR_ESTADO_ACTIVO = 1;    
    //Valores Codigos de Moneda
    public static final String CODI_MONE_CRD = "CODIMONECRD";
    //Valores Codigos de Lineas de Productos
    public static final String CODI_LINE_PRD = "CODILINEPRD";
    //Valor TIPO PROCESO='PROCESO'
    public static final String PROCESO_PROCESO="Proceso";
    public static final Short VALOR_PROCESO_PROCESO=1;
    //Valor TIPO PROCESO='SUBPROCESO'
    public static final String PROCESO_SUBPROCESO="Subproceso";
    public static final Short VALOR_PROCESO_SUBPROCESO=2;
    //Valor TIPO PROCESO='ACTIVIDAD'
    public static final String PROCESO_ACTIVIDAD="Actividad"; 
    public static final Short VALOR_PROCESO_ACTIVIDAD=3;
    //Valor Nivel UBIGEO='DEPARTAMENTO'
    public static final String NIVEL_UBIGEO_DEPARTAMENTO="Departamento"; 
    public static final Short VALOR_NIVEL_UBIGEO_DEPARTAMENTO=1;
    //Valor Nivel UBIGEO='PROVINCIA'
    public static final String NIVEL_UBIGEO_PROVINCIA="Provincia"; 
    public static final Short VALOR_NIVEL_UBIGEO_PROVINCIA=2;
    //Valor Nivel UBIGEO='DISTRITO'
    public static final String NIVEL_UBIGEO_DISTRITO="Distrito"; 
    public static final Short VALOR_NIVEL_UBIGEO_DISTRITO=3;

    /**
     * @return the ENTIDAD_PAGOS_SOCIO
     */
    public String getENTIDAD_PAGOS_SOCIO() {
        return ENTIDAD_PAGOS_SOCIO;
    }

    /**
     * @return the ENTIDAD_TIPO_MONEDA
     */
    public String getENTIDAD_TIPO_MONEDA() {
        return ENTIDAD_TIPO_MONEDA;
    }

    /**
     * @return the NIVEL_UBIGEO_DEPARTAMENTO
     */
    public String getNIVEL_UBIGEO_DEPARTAMENTO() {
        return NIVEL_UBIGEO_DEPARTAMENTO;
    }

    /**
     * @return the VALOR_NIVEL_UBIGEO_DEPARTAMENTO
     */
    public Short getVALOR_NIVEL_UBIGEO_DEPARTAMENTO() {
        return VALOR_NIVEL_UBIGEO_DEPARTAMENTO;
    }

    /**
     * @return the NIVEL_UBIGEO_PROVINCIA
     */
    public String getNIVEL_UBIGEO_PROVINCIA() {
        return NIVEL_UBIGEO_PROVINCIA;
    }

    /**
     * @return the VALOR_NIVEL_UBIGEO_PROVINCIA
     */
    public Short getVALOR_NIVEL_UBIGEO_PROVINCIA() {
        return VALOR_NIVEL_UBIGEO_PROVINCIA;
    }

    /**
     * @return the NIVEL_UBIGEO_DISTRITO
     */
    public String getNIVEL_UBIGEO_DISTRITO() {
        return NIVEL_UBIGEO_DISTRITO;
    }

    /**
     * @return the VALOR_NIVEL_UBIGEO_DISTRITO
     */
    public Short getVALOR_NIVEL_UBIGEO_DISTRITO() {
        return VALOR_NIVEL_UBIGEO_DISTRITO;
    }
    /**
     * @return the PROCESO_PROCESO
     */
    public String getPROCESO_PROCESO() {
        return PROCESO_PROCESO;
    }

    /**
     * @return the VALOR_PROCESO_PROCESO
     */
    public Short getVALOR_PROCESO_PROCESO() {
        return VALOR_PROCESO_PROCESO;
    }

    /**
     * @return the PROCESO_SUBPROCESO
     */
    public String getPROCESO_SUBPROCESO() {
        return PROCESO_SUBPROCESO;
    }

    /**
     * @return the VALOR_PROCESO_SUBPROCESO
     */
    public Short getVALOR_PROCESO_SUBPROCESO() {
        return VALOR_PROCESO_SUBPROCESO;
    }

    /**
     * @return the PROCESO_ACTIVIDAD
     */
    public String getPROCESO_ACTIVIDAD() {
        return PROCESO_ACTIVIDAD;
    }

    /**
     * @return the VALOR_PROCESO_ACTIVIDAD
     */
    public Short getVALOR_PROCESO_ACTIVIDAD() {
        return VALOR_PROCESO_ACTIVIDAD;
    }
    /**
     * @return the ENTIDAD_TIPO_AREA
     */
    public String getENTIDAD_TIPO_AREA() {
        return ENTIDAD_TIPO_AREA;
    }

    /**
     * @return the ENTIDAD_GRADO_PARENTESCO
     */
    public String getENTIDAD_GRADO_PARENTESCO() {
        return ENTIDAD_GRADO_PARENTESCO;
    }

    /**
     * @return the ENTIDAD_PRIORIDAD_TRAMITE
     */
    public String getENTIDAD_PRIORIDAD_TRAMITE() {
        return ENTIDAD_PRIORIDAD_TRAMITE;
    }

    /**
     * @return the ENTIDAD_MODALIDAD_TRAMITE
     */
    public String getENTIDAD_MODALIDAD_TRAMITE() {
        return ENTIDAD_MODALIDAD_TRAMITE;
    }

    /**
     * @return the ENTIDAD_TIPO_TRAMITE
     */
    public String getENTIDAD_TIPO_TRAMITE() {
        return ENTIDAD_TIPO_TRAMITE;
    }

    /**
     * @return the ENTIDAD_CANAL_COBRANZA
     */
    public String getENTIDAD_CANAL_COBRANZA() {
        return ENTIDAD_CANAL_COBRANZA;
    }

    /**
     * @return the ESTADO_ACTIVO
     */
    public String getESTADO_ACTIVO() {
        return ESTADO_ACTIVO;
    }

    /**
     * @return the ESTADO_INACTIVO
     */
    public String getESTADO_INACTIVO() {
        return ESTADO_INACTIVO;
    }

    /**
     * @return the VALOR_ESTADO_INACTIVO
     */
    public Short getVALOR_ESTADO_INACTIVO() {
        return VALOR_ESTADO_INACTIVO;
    }

    /**
     * @return the VALOR_ESTADO_ACTIVO
     */
    public Short getVALOR_ESTADO_ACTIVO() {
        return VALOR_ESTADO_ACTIVO;
    }

    

    public static final Short ESTADO_ACTIVO_SHORT = 1;
            
}
