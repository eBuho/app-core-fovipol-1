package pe.gob.fovipol.sifo.util;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean(name = "constantes")
@ApplicationScoped
public class Constantes implements Serializable {
    public static final String ENTIDAD_TIPO_AREA = "TipoAreaAre";
    //Entidad Grado Parentesco
    public static final String ENTIDAD_GRADO_PARENTESCO = "GRADPAREPER";
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

    
}
