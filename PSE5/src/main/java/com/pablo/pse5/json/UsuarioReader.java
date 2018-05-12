
package com.pablo.pse5.json;

import com.pablo.pse5.entities.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;


@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioReader implements MessageBodyReader<Usuario>{
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public Usuario readFrom(Class<Usuario> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Usuario usuario = new Usuario();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "email":
                            usuario.setEmail(parser.getString());
                            break;
                        case "password":
                            usuario.setPassword(parser.getString());
                            break;
                        case "nombre":
                            usuario.setNombre(parser.getString());
                            break;
                        case "nacimiento": 
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            try {
                                usuario.setNacimiento(format.parse(parser.getString()));
                            } catch (ParseException ex) {
                                Logger.getLogger(UsuarioReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case "movil":
                            usuario.setMovil(parser.getInt());
                            break;
                        case "tarjeta":
                            usuario.setTarjeta(BigInteger.valueOf(parser.getInt()));
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return usuario;
    }
    
}