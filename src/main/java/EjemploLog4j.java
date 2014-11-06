import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class EjemploLog4j {

    private static Logger log = Logger.getLogger(EjemploLog4j.class);

    public static void main(String[] args) throws IOException {
        /*
        String ruta = System.getProperty("user.dir");
        System.out.println("ruta:" + ruta);
        */
        BasicConfigurator.configure();
        //PropertyConfigurator.configure(ruta + "\\log4j.properties");
        //log.addAppender(new FileAppender(new PatternLayout(),"prueba.log", true));
        
        if (log.isTraceEnabled()) {
            log.trace("mensaje de trace");
        }

        if (log.isDebugEnabled()) {
            log.debug("mensaje de debug");
        }

        if (log.isInfoEnabled()) {
            log.info("mensaje de info");
        }

        log.warn("mensaje de warn");
        log.error("mensaje de error");
        log.fatal("mensaje de fatal");
    }

}
