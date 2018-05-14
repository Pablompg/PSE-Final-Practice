package com.pablo.pse5.bean;

import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.Usuario;
import com.pablo.pse5.jaas.UserEJB;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class LoginBackingBean implements Serializable {

    private String email;
    private String password;
    private Usuario user = null;
    private static Logger log = Logger.getLogger(LoginBackingBean.class.getName());
    @Inject
    private UserEJB userEJB;
    @Inject
    private UsuarioClientBean usuarioClientBean;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getAuthenticatedUser() {
        return user;
    }

    /*public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login incorrecto!", null));
            return "login";
        }
        
        this.user = userEJB.findByEmail(request.getUserPrincipal().getName());
        
        if (request.isUserInRole("administrador")) {
            return "index?faces-redirect=true";
        } else if (request.isUserInRole("candidato")) {
            return "index?faces-redirect=true";
        } else if (request.isUserInRole("empresa")) {
            return "index?faces-redirect=true";
        } else {
            return "login?faces-redirect=true";
        }
    }*/
    
    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Boolean loggedIn;
        try {
            request.login(email, password);
            this.user = userEJB.findByEmail(request.getUserPrincipal().getName());
            loggedIn = true;
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login incorrecto!", null));
            loggedIn = false;
        }
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }
    
    public String rol(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String rol = "";
        if (request.isUserInRole("administrador")) {
            rol = "Administrador";
        } else if (request.isUserInRole("candidato")) {
            rol = "Candidato";
        } else if (request.isUserInRole("empresa")) {
            rol = "Empresa";
        }
        return rol;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Fallo durante el proceso de logout!", e);
        }
        return "/index?faces-redirect=true";
    }
    
    public void eliminarCandidato(){
        userEJB.eliminarCandidato(email);
        logout();
    }
    
    public void modificarCandidato(){
        usuarioClientBean.modificarCandidato();
    }
}
