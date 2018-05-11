package com.pablo.pse5.jaas;

import com.pablo.pse5.client.GrupoUsuarioClientBean;
import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.Usuario;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserEJB {
    @Inject
    private UsuarioClientBean usuarioClientBean;
    @Inject
    private GrupoUsuarioClientBean grupoClientBean;

    public void createUser() {
        usuarioClientBean.addUsuario();
        grupoClientBean.addGrupoUsuario();
    }

    public Usuario findByEmail(String email) {
        return usuarioClientBean.getUsuario(email);
    }
}
