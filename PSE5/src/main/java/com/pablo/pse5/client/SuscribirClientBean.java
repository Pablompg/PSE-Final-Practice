
package com.pablo.pse5.client;

import com.pablo.pse5.bean.LoginBackingBean;
import com.pablo.pse5.entities.Suscribir;
import com.pablo.pse5.json.SuscribirReader;
import com.pablo.pse5.json.SuscribirWriter;
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
    LoginBackingBean loginBean;
    
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

//    public Suscribir getSuscribir() {
//        return target
//                .register(SuscribirReader.class)
//                .path("{id}")
//                .resolveTemplate("id", bean.getIdSuscribir())
//                .request()
//                .get(Suscribir.class);
//    }
    
    public Suscribir[] getUsuarioSuscrito(int idOferta) {
        return target
                .register(SuscribirReader.class)
                .path("{emailCandidato}/{idOferta}")
                .resolveTemplate("emailCandidato", loginBean.getEmail())
                .resolveTemplate("idOferta", idOferta)
                .request()
                .get(Suscribir[].class);
    }

//    public void deleteSuscribir() {
//        target.path("{id}")
//                .resolveTemplate("id", bean.getIdSuscribir())
//                .request()
//                .delete();
//    }
//
    public void addSuscribir(int idOferta, String cartaPresentacion) {
        Suscribir s = new Suscribir();
        s.setIdSuscribir(1);
        s.setIdOferta(idOferta);
        s.setEmailCandidato(loginBean.getEmail());
        s.setCartaPresentacion(cartaPresentacion);
        
        target.register(SuscribirWriter.class)
            .request()
            .post(Entity.entity(s, MediaType.APPLICATION_JSON));
    }
    
    public Suscribir[] obtenerSuscripcionesPorIdOferta(int idOferta){
        return target
                .path("suscripciones/{idOferta}")
                .resolveTemplate("idOferta", idOferta)
                .request()
                .get(Suscribir[].class);
    }
    
    public void deleteSuscripcionesPorIdOferta(int idOferta){
        for(Suscribir s:obtenerSuscripcionesPorIdOferta(idOferta)){
            target
                .path("{idSuscripciones}")
                .resolveTemplate("idSuscripciones", s.getIdSuscribir())
                .request()
                .delete();
        }
    }
    
    public Suscribir[] obtenerSuscripcionesPorEmailCandidato(String emailCandidato){
        return target
                .path("{emailCandidato}/suscripciones")
                .resolveTemplate("emailCandidato", emailCandidato)
                .request()
                .get(Suscribir[].class);
    }
    
    public void deleteSuscripcionesPorEmailCandidato(String emailCandidato){
        for(Suscribir s:obtenerSuscripcionesPorEmailCandidato(emailCandidato)){
            target
                .path("{idSuscripciones}")
                .resolveTemplate("idSuscripciones", s.getIdSuscribir())
                .request()
                .delete();
        }
    }
    
}

