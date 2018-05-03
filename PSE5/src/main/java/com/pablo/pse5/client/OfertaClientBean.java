
package com.pablo.pse5.client;

import com.pablo.pse5.entities.Oferta;
import com.pablo.pse5.json.OfertaReader;
import com.pablo.pse5.json.OfertaWriter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
public class OfertaClientBean implements Serializable{
    
    Client client;
    WebTarget target;
    @Inject
    OfertaBackingBean bean;
    
        @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/PSE5/webresources/com.pablo.pse5.entities.oferta");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Oferta[] getMovies() {
        return target
                .request()
                .get(Oferta[].class);
    }

    public Oferta getMovie() {
        Oferta m = target
                .register(OfertaReader.class)
                .path("{ofertaId}")
                .resolveTemplate("ofertaId", bean.getOfertaId())
                .request()
                .get(Oferta.class);
        return m;
    }

    public void deleteMovie() {
        target.path("{ofertaId}")
                .resolveTemplate("ofertaId", bean.getOfertaId())
                .request()
                .delete();
    }

    public void addMovie() {
        Oferta o = new Oferta();
        o.setIdOferta(1);
        o.setNombre (bean.getOfertaNombre ());
        o.setDescripcion (bean.getOfertaDescripcion ());
        o.setFecha (bean.getOfertaFecha ());
        o.setPuesto (bean.getOfertaPuesto ());
        o.setRequisitosMinimos(bean.getOfertaRequisitosMinimos());
        o.setEmailEmpresa(bean.getOfertaEmailEmpresa());
        target.register(OfertaWriter.class)
                .request()
                .post(Entity.entity(o, MediaType.APPLICATION_JSON));
    }
    
}
