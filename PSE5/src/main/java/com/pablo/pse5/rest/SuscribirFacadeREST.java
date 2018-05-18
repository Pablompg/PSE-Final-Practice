/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablo.pse5.rest;

import com.pablo.pse5.batch.SuscripcionesOfertaBatch;
import com.pablo.pse5.entities.Suscribir;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author usuario
 */
@Stateless
@Named
@Path("com.pablo.pse5.entities.suscribir")
public class SuscribirFacadeREST extends AbstractFacade<Suscribir> {

    @PersistenceContext(unitName = "com.Pablo_PSE5_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SuscribirFacadeREST() {
        super(Suscribir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Suscribir entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Suscribir entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Suscribir find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscribir> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("{emailCandidato}/{idOferta}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscribir> findUsuarioSuscrito(@PathParam("emailCandidato") String emailCandidato, @PathParam("idOferta") Integer idOferta) {
        return em.createNamedQuery("Suscribir.findByOfertaAndCandidato", Suscribir.class)
            .setParameter("emailCandidato",emailCandidato)
            .setParameter("idOferta",idOferta)
            .getResultList();
    }
    
    @GET
    @Path("suscripciones/{idOferta}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscribir> findSuscripcionesByIdOferta(@PathParam("idOferta") int idOferta) {
        return em.createNamedQuery("Suscribir.findByIdOferta", Suscribir.class)
            .setParameter("idOferta",idOferta)
            .getResultList();
    }
    
    @GET
    @Path("{emailCandidato}/suscripciones")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscribir> findSuscripcionesByEmail(@PathParam("emailCandidato") String emailCandidato) {
        return em.createNamedQuery("Suscribir.findByEmailCandidato", Suscribir.class)
            .setParameter("emailCandidato",emailCandidato)
            .getResultList();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
    
    
}
