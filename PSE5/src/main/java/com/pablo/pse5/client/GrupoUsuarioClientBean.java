
package com.pablo.pse5.client;

import com.pablo.pse5.bean.EmpresaBackingBean;
import com.pablo.pse5.bean.RegistroBackingBean;
import com.pablo.pse5.entities.GrupoUsuario;
import com.pablo.pse5.json.GrupoUsuarioWriter;
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
public class GrupoUsuarioClientBean {
    Client client;
    WebTarget target;
    @Inject
    private RegistroBackingBean registroBean;
    @Inject
    private EmpresaBackingBean empresaBean;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/PSE5/webresources/com.pablo.pse5.entities.grupousuario");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public void addGrupoUsuario(String nombreGrupo) {
        GrupoUsuario grupo = new GrupoUsuario();
        grupo.setEmailUsuario(registroBean.getEmail());
        grupo.setNombreGrupo(nombreGrupo);
        target.register(GrupoUsuarioWriter.class)
            .request()
            .post(Entity.entity(grupo, MediaType.APPLICATION_JSON));
    }
    
    public void deleteGrupoUsuarioEmpresa() {
        target.path("{email}")
                .resolveTemplate("email", empresaBean.getEmail())
                .request()
                .delete();
    }
    
    public void deleteGrupoUsuarioCandidato(String email) {
        target.path("{email}")
                .resolveTemplate("email", email)
                .request()
                .delete();
    }
}