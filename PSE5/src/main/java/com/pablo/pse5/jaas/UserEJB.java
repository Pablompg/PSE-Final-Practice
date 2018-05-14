package com.pablo.pse5.jaas;

import com.pablo.pse5.bean.LoginBackingBean;
import com.pablo.pse5.bean.RegistroBackingBean;
import com.pablo.pse5.client.GrupoUsuarioClientBean;
import com.pablo.pse5.client.SuscribirClientBean;
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
    @Inject
    private SuscribirClientBean suscribirClientBean;

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
        Usuario user = null;
        try{
            user = usuarioClientBean.getUsuario(email);
        }
        catch(Exception e){
            
        }
        return user;
    }
    
    public void eliminarCandidato(String email){
        suscribirClientBean.deleteSuscripcionesPorEmailCandidato(email);
        usuarioClientBean.deleteCandidato(email);
        grupoClientBean.deleteGrupoUsuarioCandidato(email);
    }
}
