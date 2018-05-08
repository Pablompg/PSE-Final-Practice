
package com.pablo.pse5.client;

import com.pablo.pse5.suscribirse.Suscribirse;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@RequestScoped
public class ValdaviaClientBean {
    
    Client client;
    WebTarget target;
    Suscribirse bean;
    
    private String estado;
    private String email;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://valdavia.infor.uva.es:8080/pagos/webresources/usuarios");
    }
    
    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public String getSuscribir() {
        try{
        return target
                .register(ValdaviaReader.class)
                .path("{email}")
                .resolveTemplate("email", bean.getEmailCandidato())
                .request()
                .get(Suscribirse.class);
    
        }catch(NotFoundException e){
            return "Not Found";
        }
    }    

}
