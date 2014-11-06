package pe.gob.fovipol.sifo.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Auditoria {

    public static String obtenerIP() {
        HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().
                getExternalContext().getRequest());

        // Obtener los atributos de cabecera. 
        // Los usamos para recuperar los valores reales.
        request.getHeaderNames();

        // Obtener la dirección IP del cliente.    
        String ip = request.getRemoteAddr();
        System.out.println("ip:" + ip);
        return ip;
    }

    public static String obtenerPC() {
        HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().
                getExternalContext().getRequest());

        // Obtener los atributos de cabecera. 
        // Los usamos para recuperar los valores reales.
        request.getHeaderNames();

        // Obtener el nombre de host del cliente.  
        String pc = request.getRemoteHost();
        System.out.println("pc:" + pc);
        return pc;
    }

    public static String obtenerEquipo() {
        StringBuilder sb = new StringBuilder();
        String resultado = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();

                if (mac != null) {
                    System.out.print("Current MAC address : ");

                    for (int i = 0; i < mac.length; i++) {
                        String macAddress = String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                        if (!macAddress.equals("00-00-00-00-00-00-00-E0"))
                            sb.append(macAddress);
                    }
                    resultado = sb.toString();
                    resultado = resultado.replace("00-00-00-00-00-00-00-E0", "");
                    System.out.println(resultado);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace(); return "";
        } catch (SocketException e) {
            e.printStackTrace(); return "";
        }
        return resultado;
    }
}
