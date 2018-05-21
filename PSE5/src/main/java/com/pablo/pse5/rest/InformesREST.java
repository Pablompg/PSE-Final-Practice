
package com.pablo.pse5.rest;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Named
@Path("informes")
public class InformesREST {

    @Context
    private UriInfo context;
    
    public InformesREST() {
    }
    
    @GET
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getPDF(@PathParam("path") String path) {
        try {
            String text = "This is the text of my pdf";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph(text));
            document.close();
            
            System.err.println("doc closed");

            return Response.ok().entity(baos.toByteArray())
                    .header("Content-Disposition",
                            "attachment; filename=\"mypdf - " + new Date().toString() + ".pdf\"")
                    .header("Expires", "0")
                    .header("Cache-Control", "must-revalidate, post-check=0, pre-check=0")
                    .header("Pragma", "public")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
