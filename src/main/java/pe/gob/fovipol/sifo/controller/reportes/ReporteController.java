package pe.gob.fovipol.sifo.controller.reportes;

import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
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
import pe.gob.fovipol.sifo.dao.MaeEntidaddetFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidad;
import pe.gob.fovipol.sifo.model.maestros.MaeEntidaddet;
import pe.gob.fovipol.sifo.util.Constantes;
import pe.gob.fovipol.sifo.util.Funciones;

/**
 * @author ebuho 09/11/2014 12:18pm
 */
@ManagedBean(name = "reporteController")
@ViewScoped
public class ReporteController implements Serializable {

    @EJB
    private MaeEntidaddetFacade ejbEntidaddetFacade;

    private Date fechaInicial;
    private Date fechaFinal;
    private Integer codiMoneCrd;

    private List<MaeEntidaddet> monedas;

    public ReporteController() {
        fechaInicial = Funciones.fechaInicioMes(new Date());
        fechaFinal = new Date();
    }

    @PostConstruct
    public void inicializa() {
        setMonedas(getEjbEntidaddetFacade().findDetalleActivo(new MaeEntidad(Constantes.CODI_MONE_CRD)));
    }

    public void imprimirExpedientes() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré al printReport");
        Connection connection;
        Map parameterMap = new HashMap();

        FacesContext ctx = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) ctx
                .getExternalContext().getResponse();

        InputStream reportStream = getClass().getResourceAsStream("/jasper/expediente.jasper");//ctx.getExternalContext().getResourceAsStream("jasper/expediente.jasper");

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

    public void imprimirExpedientesProductos() throws ClassNotFoundException, IOException, JRException, SQLException {
        System.out.println("Entré al printReport");
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
     * @return the codiMoneCrd
     */
    public Integer getCodiMoneCrd() {
        return codiMoneCrd;
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
     * @param codiMoneCrd the codiMoneCrd to set
     */
    public void setCodiMoneCrd(Integer codiMoneCrd) {
        this.codiMoneCrd = codiMoneCrd;
    }

}
