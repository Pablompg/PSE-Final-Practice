
package com.pablo.pse5.json;

import com.pablo.pse5.entities.Oferta;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class OfertaWriter implements MessageBodyWriter<Oferta>{
    
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Oferta.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Oferta t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Oferta o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        gen.writeStartObject()
                .write("idOferta", o.getIdOferta())
                .write("nombre", o.getNombre())
                .write("descripcion", o.getDescripcion())
                .write("fecha", formato.format(o.getFecha()))
                .write("puesto", o.getPuesto())
                .write("requisitosMinimos", o.getRequisitosMinimos())
                .write("emailEmpresa", o.getEmailEmpresa())
                .writeEnd();
        gen.flush();
    }
}

