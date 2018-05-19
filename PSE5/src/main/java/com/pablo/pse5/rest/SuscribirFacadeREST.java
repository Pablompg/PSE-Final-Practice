/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablo.pse5.rest;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pablo.pse5.batch.SuscripcionesOfertaBatch;
import com.pablo.pse5.entities.Suscribir;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
                .setParameter("emailCandidato", emailCandidato)
                .setParameter("idOferta", idOferta)
                .getResultList();
    }

    @GET
    @Path("suscripciones/{idOferta}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscribir> findSuscripcionesByIdOferta(@PathParam("idOferta") int idOferta) {
        return em.createNamedQuery("Suscribir.findByIdOferta", Suscribir.class)
                .setParameter("idOferta", idOferta)
                .getResultList();
    }

    @GET
    @Path("{emailCandidato}/suscripciones")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Suscribir> findSuscripcionesByEmail(@PathParam("emailCandidato") String emailCandidato) {
        return em.createNamedQuery("Suscribir.findByEmailCandidato", Suscribir.class)
                .setParameter("emailCandidato", emailCandidato)
                .getResultList();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("/generatePDF")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response generatePDF() {
        try {

            String text = "This is the text of my pdf";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph(text));
            document.close();
            
            System.err.println("doc closed");

            return Response.ok().entity(baos.toByteArray()).
                    header("Content-Disposition",
                            "attachment; filename=\"mypdf - " + new Date().toString() + ".pdf\"")
                    .header("Expires", "0")
                    .header("Cache-Control", "must-revalidate, post-check=0, pre-check=0")
                    .header("Pragma", "public")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
