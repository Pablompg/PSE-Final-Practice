
package com.pablo.pse5.json;

import com.pablo.pse5.entities.Usuario;
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
public class UsuarioWriter implements MessageBodyWriter<Usuario>{
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Usuario t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Usuario u, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        if (u.getNacimiento() != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            gen.writeStartObject()
                .write("email", u.getEmail())
                .write("password", u.getPassword())
                .write("nombre", u.getNombre())
                .write("nacimiento", formato.format(u.getNacimiento()))
                .write("movil", u.getMovil())
                .write("tarjeta", u.getTarjeta())
                .writeEnd();
        }
        else{
            gen.writeStartObject()
                .write("email", u.getEmail())
                .write("password", u.getPassword())
                .write("nombre", u.getNombre())
                .writeEnd();
        }
        gen.flush();
    }
}