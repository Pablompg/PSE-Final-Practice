
package com.pablo.pse5.client;

import com.pablo.pse5.entities.Usuario;
import com.pablo.pse5.json.UsuarioReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@RequestScoped
public class UsuarioClientBean {
    Client client;
    WebTarget target;
    
    
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
        Usuario u= target
                .register(UsuarioReader.class)
                .path("{id}")
                .resolveTemplate("id", emailEmpresa)
                .request()
                .get(Usuario.class);
        return u.getNombre();
    }
    
}
