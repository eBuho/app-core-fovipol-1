package pe.gob.fovipol.sifo.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.model.general.Moneda;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.service.ComunService;
import pe.gob.fovipol.sifo.util.Constantes;

/**
 *
 * @author ebuho
 */
@Stateless
public class ComunServiceImpl implements ComunService {
    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;
    
    @Override
    public List<Moneda> getMonedas() {
        List<Moneda>  listaMonedas = new ArrayList<>();
        List<MaeEntidaddet> lista = ejbEntidaddetFacade.findDetalleActivo(new MaeEntidad(Constantes.CODI_MONE_CRD));
        for (MaeEntidaddet detalle : lista) {
            Moneda moneda = new Moneda(detalle);
            listaMonedas.add(moneda);
        }
        return listaMonedas;
    }
    
}
