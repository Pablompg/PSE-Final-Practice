
package com.pablo.pse5.client;

import com.pablo.pse5.bean.LoginBackingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@RequestScoped
public class InformesClientBean{
    
    Client client;
    WebTarget target;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/PSE5/webresources/informes");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public void getInforme(String ruta) {
        target.path("{ruta}")
              .resolveTemplate("ruta", ruta)
              .request();
    }
}
