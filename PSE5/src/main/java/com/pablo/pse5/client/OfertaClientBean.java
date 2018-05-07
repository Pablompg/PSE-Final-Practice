package com.pablo.pse5.client;

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

    public Oferta[] getOfertas() {
        return target
                .request()
                .get(Oferta[].class);
    }

    public Oferta getOferta() {
        return target
                .register(OfertaReader.class)
                .path("{ofertaId}")
                .resolveTemplate("ofertaId", bean.getOfertaId())
                .request()
                .get(Oferta.class);
    }

    public void deleteOferta() {
        target.path("{ofertaId}")
                .resolveTemplate("ofertaId", bean.getOfertaId())
                .request()
                .delete();
    }

    public void addOferta() {
        Oferta o = new Oferta();
        o.setIdOferta(1);
        o.setNombre (bean.getOfertaNombre());
        o.setDescripcion (bean.getOfertaDescripcion());
        o.setFecha (bean.getOfertaFecha());
        o.setPuesto (bean.getOfertaPuesto());
        o.setRequisitosMinimos(bean.getOfertaRequisitosMinimos());
        o.setEmailEmpresa(bean.getOfertaEmailEmpresa());
        target.register(OfertaWriter.class)
                .request()
                .post(Entity.entity(o, MediaType.APPLICATION_JSON));
    }
    
}
