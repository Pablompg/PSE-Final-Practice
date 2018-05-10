package com.pablo.pse5.jaas;

import com.pablo.pse5.entities.GrupoUsuario;
import com.pablo.pse5.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserEJB {

    @PersistenceContext
    private EntityManager em;

    public Usuario createUser(Usuario user) {
        
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GrupoUsuario group = new GrupoUsuario();
        group.setEmailUsuario(user.getEmail());
        //String nombreGrupo = (request.isUserInRole('administrador'))? "empresa" : "candidato";
        group.setNombreGrupo("nombreGrupo");
        em.persist(user);
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
