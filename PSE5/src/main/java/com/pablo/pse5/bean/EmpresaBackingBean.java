
package com.pablo.pse5.bean;

import com.pablo.pse5.client.GrupoUsuarioClientBean;
import com.pablo.pse5.client.UsuarioClientBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class EmpresaBackingBean implements Serializable{
   
    private String email;
    private String nombre;
    @Inject
    UsuarioClientBean usuarioClientBean;
    @Inject
    GrupoUsuarioClientBean grupoUsuarioClientBean;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void eliminarEmpresa(){
        usuarioClientBean.deleteUsuario();
        grupoUsuarioClientBean.deleteGrupoUsuario();
    }
    
}
