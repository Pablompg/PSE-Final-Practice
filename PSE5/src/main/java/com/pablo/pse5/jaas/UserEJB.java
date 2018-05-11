package com.pablo.pse5.jaas;

import com.pablo.pse5.bean.LoginBackingBean;
import com.pablo.pse5.bean.RegistroBackingBean;
import com.pablo.pse5.client.GrupoUsuarioClientBean;
import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.Usuario;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserEJB {
    @Inject
    private RegistroBackingBean registroBean;
    @Inject
    private LoginBackingBean loginBean;
    @Inject
    private UsuarioClientBean usuarioClientBean;
    @Inject
    private GrupoUsuarioClientBean grupoClientBean;

    public void createUser() {
        try {
            registroBean.setPassword(AuthenticationUtils.encodeSHA256(registroBean.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (loginBean.getAuthenticatedUser() != null) {
            usuarioClientBean.addEmpresa();
            grupoClientBean.addGrupoUsuario("empresa");
        }
        else{
            usuarioClientBean.addCandidato();
            grupoClientBean.addGrupoUsuario("candidato");
        }
    }

    public Usuario findByEmail(String email) {
        return usuarioClientBean.getUsuario(email);
    }
}
