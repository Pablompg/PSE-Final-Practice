
package com.pablo.pse5.suscribirse;

import com.pablo.pse5.entities.Oferta;
import com.pablo.pse5.entities.Suscribir;
import com.pablo.pse5.entities.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Named
@FlowScoped("suscribirse")
public class Suscribirse implements Serializable{
    private int idOferta;
    @PersistenceContext
    private EntityManager em;
    
    //TEMPORAL
    private String emailCandidato = "grupo5si@uva.es"; //TEMPORAL
    //TEMPORAL
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
    public String getOfertaNombre(){
        try{
            return em.createNamedQuery("Oferta.findByIdOferta", Oferta.class)
                    .setParameter("idOferta",idOferta)
                    .getSingleResult()
                    .getNombre();
        }catch(NoResultException e){
            return "";
        }
    }
    
    public String getOfertaDescripcion(){
        try{
            return em.createNamedQuery("Oferta.findByIdOferta", Oferta.class)
                    .setParameter("idOferta",idOferta)
                    .getSingleResult()
                    .getDescripcion();
        }catch(NoResultException e){
            return "";
        }
    }
    
    public Date getOfertaFecha(){
        try{
            return em.createNamedQuery("Oferta.findByIdOferta", Oferta.class)
                    .setParameter("idOferta",idOferta)
                    .getSingleResult()
                    .getFecha();
        }catch(NoResultException e){
            return new Date();
        }
    }
    
    public String getOfertaPuesto(){
        try{
            return em.createNamedQuery("Oferta.findByIdOferta", Oferta.class)
                    .setParameter("idOferta",idOferta)
                    .getSingleResult()
                    .getPuesto();
        }catch(NoResultException e){
            return "";
        }
    }
    
    public String getOfertaRequisitosMinimos(){
        try{
            return em.createNamedQuery("Oferta.findByIdOferta", Oferta.class)
                    .setParameter("idOferta",idOferta)
                    .getSingleResult()
                    .getRequisitosMinimos();
        }catch(NoResultException e){
            return "";
        }
    }
    
    public String getOfertaEmailEmpresa(){
        try{
            return em.createNamedQuery("Oferta.findByIdOferta", Oferta.class)
                    .setParameter("idOferta",idOferta)
                    .getSingleResult()
                    .getEmailEmpresa();
        }catch(NoResultException e){
            return "";
        }
    }
    
    public String getOfertaNombreEmpresa(){
        try{
            return em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email",getOfertaEmailEmpresa())
                    .getSingleResult()
                    .getNombre();
        }catch(NoResultException e){
            return "";
        }
    }
    
    public boolean getUsuarioSuscrito(){
        try{
            List<Suscribir> lista = em.createNamedQuery("Suscribir.findByOfertaAndCandidato", Suscribir.class)
                    .setParameter("emailCandidato",emailCandidato)
                    .setParameter("idOferta",idOferta)
                    .getResultList();
            return lista.isEmpty();
        }catch(NoResultException e){
            return false;
        }
    }

    public String getEmailCandidato() {
        return emailCandidato;
    }
    
    
}
