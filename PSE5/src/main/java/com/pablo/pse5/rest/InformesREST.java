package com.pablo.pse5.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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
    public Response getPDF() {
        try {
            SimpleDateFormat formatoDia = new SimpleDateFormat("dd-MM-yyyy");
            String path = System.getProperty("user.dir") + File.separator + "Informe"
                + formatoDia.format(new Date()) + ".pdf";
            File f = new File(path);
            OutputStream oos = new FileOutputStream("test.pdf");
            byte[] buf = new byte[(int) f.length()];
            InputStream is = new FileInputStream(f);
            int c = 0;
            while ((c = is.read(buf, 0, buf.length)) > 0) {
                oos.write(buf, 0, c);
                oos.flush();
            }
            oos.close();
            is.close();
            return Response.ok().entity(buf)
                    .header("Content-Disposition",
                            "attachment; filename=\"Informe.pdf")
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
