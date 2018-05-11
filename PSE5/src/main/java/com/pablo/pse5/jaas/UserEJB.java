package com.pablo.pse5.jaas;

import com.pablo.pse5.bean.LoginBackingBean;
import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.GrupoUsuario;
import com.pablo.pse5.entities.Usuario;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Request;
import org.primefaces.context.RequestContext;

@Stateless
public class UserEJB {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private LoginBackingBean usuarioInfo;
    @Inject
    private UsuarioClientBean usuarioBean;

    public Usuario createUser(Usuario user) {
        
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GrupoUsuario group = new GrupoUsuario();
        group.setEmailUsuario(user.getEmail());
        String nombreGrupo = (usuarioInfo.getAuthenticatedUser() != null)? "empresa" : "candidato";
        group.setNombreGrupo(nombreGrupo);
//        UsuarioClientBean usuarioBean = new UsuarioClientBean();
        usuarioBean.addUsuario();
//        GrupoUsuarioClientBean grupoBean = new GrupoUsuarioClientBean();
//        grupoBean.addGrupoUsuario();
        //em.persist(user);
        em.persist(group);

        return user;
    }

    public Usuario findByEmail(String email) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByEmail", Usuario.class);
        query.setParameter("email", email);
        Usuario user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }
}
