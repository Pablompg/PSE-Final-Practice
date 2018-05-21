
package com.pablo.pse5.rest;

import com.pablo.pse5.client.InformesClientBean;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.batch.api.listener.AbstractJobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@Dependent
public class BatchListener extends AbstractJobListener {
    
    Client client;
    WebTarget target;
    
    SimpleDateFormat formatoDia = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatoHorasyMinutos = new SimpleDateFormat("HH-mm");
    
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

    @Override
    public void afterJob() throws Exception {
        System.out.println("Empezando a mostrar pdf");
//        Date fecha = new Date();
//        String path = System.getProperty("user.dir") + File.separator + "InformeDia"
//                + formatoDia.format(fecha) + "Hora" + formatoHorasyMinutos.format(fecha) + ".pdf";
//        target.path("{ruta}")
//              .resolveTemplate("ruta", path)
//              .request();
//        System.out.println("Después de la llamada al REST");
    }
}
