
package com.pablo.pse5.client;

import com.pablo.pse5.bean.RegistroBackingBean;
import com.pablo.pse5.entities.Usuario;
import com.pablo.pse5.json.CandidatoReader;
import com.pablo.pse5.json.CandidatoWriter;
import com.pablo.pse5.json.EmpresaReader;
import com.pablo.pse5.json.EmpresaWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
public class UsuarioClientBean {
    Client client;
    WebTarget target;
    @Inject
    private RegistroBackingBean registroBean;
    
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/PSE5/webresources/com.pablo.pse5.entities.usuario");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public String getNombreEmpresa(String emailEmpresa){
        Usuario u = target
                .register(EmpresaReader.class)
                .path("{id}")
                .resolveTemplate("id", emailEmpresa)
                .request()
                .get(Usuario.class);
        return u.getNombre();
    }
    
    public Usuario[] getEmpresas(){
        return target
                .request()
                .get(Usuario[].class);
    }
    
    public Usuario getUsuario(String email) {
        return target
                .register(CandidatoReader.class)
                .path("{email}")
                .resolveTemplate("email", email)
                .request()
                .get(Usuario.class);
    }
    
    public void addCandidato() {
        Usuario u = new Usuario();
        u.setEmail(registroBean.getEmail());
        u.setPassword(registroBean.getPassword());
        u.setNombre(registroBean.getName());
        u.setNacimiento(registroBean.getFecha());
        u.setMovil(registroBean.getTelefono());
        u.setTarjeta(registroBean.getTarjeta());
        
        target.register(CandidatoWriter.class)
            .request()
            .post(Entity.entity(u, MediaType.APPLICATION_JSON));
    }
    
    public void addEmpresa() {
        Usuario u = new Usuario();
        u.setEmail(registroBean.getEmail());
        u.setPassword(registroBean.getPassword());
        u.setNombre(registroBean.getName());
        
        target.register(EmpresaWriter.class)
            .request()
            .post(Entity.entity(u, MediaType.APPLICATION_JSON));
    }
}
