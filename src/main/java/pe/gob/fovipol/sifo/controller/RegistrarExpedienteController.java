package pe.gob.fovipol.sifo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.dao.CrdSimulacionFacade;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.dao.MaeRequisitoFacade;
import pe.gob.fovipol.sifo.dao.MaeSocioFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.model.maestros.MaeRequisito;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.simulacion.CrdSimulacion;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumento;
import pe.gob.fovipol.sifo.model.tramite.TrmDocumentoPK;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

@ManagedBean(name = "registrarExpedienteController")
@ViewScoped
public class RegistrarExpedienteController implements Serializable{    
    @EJB
    private MaeSocioFacade ejbSocioFacade;
    @EJB
    private MaeEntidaddetFacade ejbEntidadDetalleFacade;
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    @EJB
    private MaeRequisitoFacade ejbRequisitoFacade;
    @EJB
    private CrdSimulacionFacade ejbSimulacionFacade;
    private String cip;
    private MaeSocio socio;
    private TrmTramite tramite;
    private List<MaeProducto> productos;
    private List<MaeEntidaddet> tiposTramite;
    private List<MaeEntidaddet> tiposPrioridad;
    private List<MaeEntidaddet> modalidadesTramite;
    private List<TrmDocumento> documentos;
    private List<CrdSimulacion> simulaciones;
    private MaeProducto producto;
    private BigDecimal simulacion;
    @PostConstruct
    public void init(){
        tramite=new TrmTramite();
        tramite.setIdenSimuSim(new CrdSimulacion());
        productos=ejbProductoFacade.findAll();
    }
    
    public void mostrarSimulacion(){
        tramite.setIdenSimuSim(ejbSimulacionFacade.find(simulacion));
    }
    public void cargarRequisitos(){
        if(producto!=null){
            List<MaeRequisito> reqs=ejbRequisitoFacade.findByProcesoActivo(producto.getIdenProcPrc().getCodiProcPrc());
            if(socio!=null){
                simulaciones=ejbSimulacionFacade.findBySocioProducto(socio.getCodiPersPer(),producto.getIdenProdPrd());
            }
            documentos=new ArrayList<>();
            int i=1;
            for(MaeRequisito aux:reqs){
                TrmDocumento doc=new TrmDocumento();
                doc.setTrmDocumentoPK(new TrmDocumentoPK());
                doc.getTrmDocumentoPK().setSecuDocuDoc(i);
                doc.setMaeRequisito(aux);
                i++;
                doc.setDescNombDoc(aux.getNombRequReq());
                doc.setFlagEstaDoc(new Short("1"));
                documentos.add(doc);
            }
        }
    }
    public void buscarSocio(){
        List<MaeSocio> socios=ejbSocioFacade.findByCIP(cip);
        if(socios!=null && !socios.isEmpty())
            socio=socios.get(0);
        else
            socio=null;
    }
    public void registrar(){
        
    }
    /**
     * @return the cip
     */
    public String getCip() {
        return cip;
    }

    /**
     * @param cip the cip to set
     */
    public void setCip(String cip) {
        this.cip = cip;
    }

    /**
     * @return the socio
     */
    public MaeSocio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(MaeSocio socio) {
        this.socio = socio;
    }

    /**
     * @return the tramite
     */
    public TrmTramite getTramite() {
        return tramite;
    }

    /**
     * @param tramite the tramite to set
     */
    public void setTramite(TrmTramite tramite) {
        this.tramite = tramite;
    }

    /**
     * @return the tiposTramite
     */
    public List<MaeEntidaddet> getTiposTramite() {
        if(tiposTramite==null)
            tiposTramite=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("TIPOTRAMTRM"));
        return tiposTramite;
    }

    /**
     * @param tiposTramite the tiposTramite to set
     */
    public void setTiposTramite(List<MaeEntidaddet> tiposTramite) {
        this.tiposTramite = tiposTramite;
    }

    /**
     * @return the modalidadesTramite
     */
    public List<MaeEntidaddet> getModalidadesTramite() {
        if(modalidadesTramite==null)
            modalidadesTramite=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("CODIMODATRM"));
        return modalidadesTramite;
    }

    /**
     * @param modalidadesTramite the modalidadesTramite to set
     */
    public void setModalidadesTramite(List<MaeEntidaddet> modalidadesTramite) {
        this.modalidadesTramite = modalidadesTramite;
    }

    /**
     * @return the tiposPrioridad
     */
    public List<MaeEntidaddet> getTiposPrioridad() {
        if(tiposPrioridad==null)
            tiposPrioridad=ejbEntidadDetalleFacade.findDetalleActivo(new MaeEntidad("CODIPRIOTRM"));
        return tiposPrioridad;
    }

    /**
     * @param tiposPrioridad the tiposPrioridad to set
     */
    public void setTiposPrioridad(List<MaeEntidaddet> tiposPrioridad) {
        this.tiposPrioridad = tiposPrioridad;
    }

    

    /**
     * @return the procesos
     */
    public List<MaeProducto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<MaeProducto> productos) {
        this.productos = productos;
    }

    /**
     * @return the producto
     */
    public MaeProducto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(MaeProducto producto) {
        this.producto = producto;
    }

    /**
     * @return the documentos
     */
    public List<TrmDocumento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<TrmDocumento> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the simulaciones
     */
    public List<CrdSimulacion> getSimulaciones() {
        return simulaciones;
    }

    /**
     * @param simulaciones the simulaciones to set
     */
    public void setSimulaciones(List<CrdSimulacion> simulaciones) {
        this.simulaciones = simulaciones;
    }

    /**
     * @return the simulacion
     */
    public BigDecimal getSimulacion() {
        return simulacion;
    }

    /**
     * @param simulacion the simulacion to set
     */
    public void setSimulacion(BigDecimal simulacion) {
        this.simulacion = simulacion;
    }
}
