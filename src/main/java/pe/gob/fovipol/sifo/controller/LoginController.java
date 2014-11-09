package pe.gob.fovipol.sifo.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    public String doLogin() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public void mostrarErrores() {
        Exception e = (Exception) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap()
                    .put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
            String mensaje = "";
            try {
                mensaje = "Error al logearse";
            } catch (Exception ex) {
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        }
    }
}
