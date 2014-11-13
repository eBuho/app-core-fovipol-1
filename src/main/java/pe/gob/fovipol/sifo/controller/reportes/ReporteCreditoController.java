package pe.gob.fovipol.sifo.controller.reportes;

import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.model.credito.CrdCredito;
import pe.gob.fovipol.sifo.model.general.Moneda;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;
import pe.gob.fovipol.sifo.service.ComunService;
import pe.gob.fovipol.sifo.service.CreditoService;
import pe.gob.fovipol.sifo.util.Constantes;
import pe.gob.fovipol.sifo.util.Funciones;

/**
 * @author ebuho 09/11/2014 12:18pm
 */
@ManagedBean(name = "reporteCreditoController")
@ViewScoped
public class ReporteCreditoController implements Serializable {

    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;
    @EJB
    private MaeProductoFacade ejbProductoFacade;
    
    @EJB
    private CreditoService serviceCreditos;

    private Date fechaInicial;
    private Date fechaFinal;
    private MaeEntidaddet moneda;
    private MaeProducto producto;
    private MaeEntidaddet linea;
    private MaeEntidaddet todas;

    private List<MaeEntidaddet> monedas;
    private List<MaeProducto> productos;
    private List<MaeEntidaddet> lineas;
    private List<CrdCredito> listaCreditos;

    public ReporteCreditoController() {
        fechaInicial = Funciones.fechaInicioMes(new Date());
        fechaFinal = new Date();
    }

    @PostConstruct
    public void inicializa() {
        monedas = ejbEntidaddetFacade.findDetalleActivo(new MaeEntidad(Constantes.CODI_MONE_CRD));
        todas = new MaeEntidaddet(BigDecimal.ZERO, 0);
        todas.setCodiEntiEnt(new MaeEntidad(Constantes.CODI_MONE_CRD));
        todas.setValoCaduDet("(Todas)");
        monedas.add(0, todas);
        productos = ejbProductoFacade.findAll();
        producto = new MaeProducto(BigDecimal.ZERO);
        producto.setNombProdPrd("(Todos)");
        productos.add(0, producto);
        lineas = ejbEntidaddetFacade.findDetalleActivo(new MaeEntidad(Constantes.CODI_LINE_PRD));
        linea = new MaeEntidaddet(BigDecimal.ZERO, 0);
        linea.setCodiEntiEnt(new MaeEntidad(Constantes.CODI_LINE_PRD));
        linea.setValoCaduDet("(Todas)");
        lineas.add(0, linea);
    }
         
    public void filtrarExpedientes(){
        System.out.println("fechaInicial: "+fechaInicial);
        System.out.println("fechaFinal: "+fechaFinal);
        System.out.println("moneda: "+moneda);
        listaCreditos = serviceCreditos.obtenerCreditos(fechaInicial, fechaFinal, moneda, 1);
        if (moneda == null) moneda = getTodas();
        if (listaCreditos == null) return;
        for (CrdCredito credito : listaCreditos) {
            MaeEntidaddet moneda = buscarMoneda(credito.getCodiMoneCrd());
            credito.setMoneda(moneda);
        }
    }
    
    public void filtrarExpedientesProductos(){
        System.out.println("fechaInicial: "+fechaInicial);
        System.out.println("fechaFinal: "+fechaFinal);
        System.out.println("producto: "+getProducto());
        System.out.println("moneda: "+moneda);
        listaCreditos = serviceCreditos.obtenerCreditosProductos(fechaInicial, fechaFinal, producto, moneda, 1);
        if (moneda == null) moneda = getTodas();
        if (listaCreditos == null) return;
        for (CrdCredito credito : listaCreditos) {
            MaeEntidaddet moneda = buscarMoneda(credito.getCodiMoneCrd());
            credito.setMoneda(moneda);
        }
    }

    public JRBeanCollectionDataSource getExpedientes() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré a Expedientes");
        //listaCreditos = serviceCreditos.obtenerCreditos(fechaInicial, fechaFinal, codiMoneCrd, 1);
        return new JRBeanCollectionDataSource(listaCreditos);
    }
    
    public JRBeanCollectionDataSource getExpedientesProductos() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré a ExpedientesProductos");
        //listaCreditos = serviceCreditos.obtenerCreditos(fechaInicial, fechaFinal, codiMoneCrd, 1);
        return new JRBeanCollectionDataSource(listaCreditos);
    }
    
    public void imprimirExpedientesProductos() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré al printReport2");
        Connection connection;
        Map parameterMap = new HashMap();

        FacesContext ctx = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) ctx
                .getExternalContext().getResponse();

        InputStream reportStream = getClass().getResourceAsStream("/jasper/expedienteProducto.jasper");//ctx.getExternalContext().getResourceAsStream("jasper/expediente.jasper");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.104:1521:bdfovidesa", "sifo", "12345");

        response.setContentType("application/pdf");

        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameterMap, connection);

        connection.close();
        servletOutputStream.flush();
        servletOutputStream.close();
        ctx.responseComplete();
    }

    public void imprimirConsolidadoLinea() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré al printReport");
        Connection connection;
        Map parameterMap = new HashMap();

        FacesContext ctx = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) ctx
                .getExternalContext().getResponse();

        InputStream reportStream = getClass().getResourceAsStream("/jasper/consolidadoLinea.jasper");//ctx.getExternalContext().getResourceAsStream("jasper/expediente.jasper");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.104:1521:bdfovidesa", "sifo", "12345");

        response.setContentType("application/pdf");

        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameterMap, connection);

        connection.close();
        servletOutputStream.flush();
        servletOutputStream.close();
        ctx.responseComplete();
    }

    public void imprimirConsolidadoProducto() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré al printReport");
        Connection connection;
        Map parameterMap = new HashMap();

        FacesContext ctx = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) ctx
                .getExternalContext().getResponse();

        InputStream reportStream = getClass().getResourceAsStream("/jasper/consolidadoProducto.jasper");//ctx.getExternalContext().getResourceAsStream("jasper/expediente.jasper");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.104:1521:bdfovidesa", "sifo", "12345");

        response.setContentType("application/pdf");

        JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameterMap, connection);

        connection.close();
        servletOutputStream.flush();
        servletOutputStream.close();
        ctx.responseComplete();
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the monedas
     */
    public List<MaeEntidaddet> getMonedas() {
        return monedas;
    }

    /**
     * @param monedas the monedas to set
     */
    public void setMonedas(List<MaeEntidaddet> monedas) {
        this.monedas = monedas;
    }

    /**
     * @return the ejbEntidaddetFacade
     */
    public MaeEntidaddetFacade getEjbEntidaddetFacade() {
        return ejbEntidaddetFacade;
    }

    /**
     * @param ejbEntidaddetFacade the ejbEntidaddetFacade to set
     */
    public void setEjbEntidaddetFacade(MaeEntidaddetFacade ejbEntidaddetFacade) {
        this.ejbEntidaddetFacade = ejbEntidaddetFacade;
    }

    /**
     * @return the listaCreditos
     */
    public List<CrdCredito> getListaCreditos() {
        return listaCreditos;
    }

    /**
     * @param listaCreditos the listaCreditos to set
     */
    public void setListaCreditos(List<CrdCredito> listaCreditos) {
        this.listaCreditos = listaCreditos;
    }

    /**
     * @return the moneda
     */
    public MaeEntidaddet getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(MaeEntidaddet moneda) {
        this.moneda = moneda;
    }
    
    private MaeEntidaddet buscarMoneda(Integer key) {
        MaeEntidaddet objeto = new MaeEntidaddet();
        
        for (MaeEntidaddet bean : monedas) {
            if (bean.getSecuEntiDet()!= key){
            } else {
                objeto = bean;
                break;
            }
        }
        return objeto;
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
     * @return the todas
     */
    public MaeEntidaddet getTodas() {
        return todas;
    }

    /**
     * @param todas the todas to set
     */
    public void setTodas(MaeEntidaddet todas) {
        this.todas = todas;
    }

    /**
     * @return the productos
     */
    public List<MaeProducto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<MaeProducto> productos) {
        this.productos = productos;
    }
   
}
