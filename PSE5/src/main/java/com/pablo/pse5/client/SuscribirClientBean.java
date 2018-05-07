
package com.pablo.pse5.client;

import com.pablo.pse5.entities.Oferta;
import com.pablo.pse5.entities.Suscribir;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

@Named
@RequestScoped
public class SuscribirClientBean{
    
    Client client;
    WebTarget target;
    @Inject
    Suscribirse bean;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/PSE5/webresources/com.pablo.pse5.entities.suscribir");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Suscribir[] getSuscripciones() {
        return target
                .request()
                .get(Suscribir[].class);
    }

    public Oferta getSuscribir() {
        return target
                .register(SuscribirReader.class)
                .path("{id}")
                .resolveTemplate("id", bean.getIdSuscribir())
                .request()
                .get(Suscribir.class);
    }

    public void deleteSuscribir() {
        target.path("{id}")
                .resolveTemplate("id", bean.getIdSuscribir())
                .request()
                .delete();
    }

    public void addSuscribir() {
        Suscribir s = new Suscribir();
        s.setIdSuscribir(1);
        s.setIdOferta (bean.getIdOferta());
        s.setEmailCandidato (bean.getEmailCandidato());
        s.setCartaPresentacion (bean.getCartaPresentacion());
        target.register(SuscribirWriter.class)
                .request()
                .post(Entity.entity(s, MediaType.APPLICATION_JSON));
    }
}

