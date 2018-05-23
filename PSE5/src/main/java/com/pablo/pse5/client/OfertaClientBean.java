package com.pablo.pse5.client;

import com.pablo.pse5.bean.LoginBackingBean;
import com.pablo.pse5.bean.OfertaBackingBean;
import com.pablo.pse5.entities.Oferta;
import com.pablo.pse5.json.OfertaReader;
import com.pablo.pse5.json.OfertaWriter;
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
public class OfertaClientBean{
    
    Client client;
    WebTarget target;
    @Inject
    OfertaBackingBean ofertaBean;
    @Inject
    LoginBackingBean loginBean;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/PSE5/webresources/com.pablo.pse5.entities.oferta");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Oferta[] getOfertas() {
        return target
                .request()
                .get(Oferta[].class);
    }
    
    public Oferta[] getOfertasEmpresa(String email) {
        return target
                .path("empresa/{emailEmpresa}")
                .resolveTemplate("emailEmpresa", email)
                .request()
                .get(Oferta[].class);
    }
    
    public Oferta getOfertaFacesFlow(int id){
        return target
                .register(OfertaReader.class)
                .path("{idOferta}")
                .resolveTemplate("idOferta", id)
                .request()
                .get(Oferta.class);
    }

    public Oferta getOferta() {
        return target
                .register(OfertaReader.class)
                .path("{idOferta}")
                .resolveTemplate("idOferta", ofertaBean.getIdOferta())
                .request()
                .get(Oferta.class);
    }
    
    public Oferta getOferta(int idOferta) {
        return target
                .register(OfertaReader.class)
                .path("{idOferta}")
                .resolveTemplate("idOferta", idOferta)
                .request()
                .get(Oferta.class);
    }

    public void deleteOferta(int idOferta) {
        target.path("{idOferta}")
                .resolveTemplate("idOferta", idOferta)
                .request()
                .delete();
    }

    public void addOferta() {
        Oferta o = new Oferta();
        o.setIdOferta(1);
        o.setNombre (ofertaBean.getOfertaNombre());
        o.setDescripcion (ofertaBean.getOfertaDescripcion());
        o.setFecha (ofertaBean.getOfertaFecha());
        o.setPuesto (ofertaBean.getOfertaPuesto());
        o.setRequisitosMinimos(ofertaBean.getOfertaRequisitosMinimos());
        o.setEmailEmpresa(loginBean.getAuthenticatedUser().getEmail());
        target.register(OfertaWriter.class)
            .request()
            .post(Entity.entity(o, MediaType.APPLICATION_JSON));
    }
    
    public void modificarOferta(){
        Oferta o = new Oferta();
        o.setIdOferta(ofertaBean.getIdOferta());
        o.setNombre(ofertaBean.getOfertaNombre());
        o.setDescripcion(ofertaBean.getOfertaDescripcion());
        o.setEmailEmpresa(ofertaBean.getOfertaEmailEmpresa());
        o.setFecha(ofertaBean.getOfertaFecha());
        o.setPuesto(ofertaBean.getOfertaPuesto());
        o.setRequisitosMinimos(ofertaBean.getOfertaRequisitosMinimos());
        
        target
            .path("{id}")
            .register(OfertaWriter.class)
            .resolveTemplate("id", ofertaBean.getIdOferta())
            .request()
            .put(Entity.entity(o, MediaType.APPLICATION_JSON));
    }
    
}
