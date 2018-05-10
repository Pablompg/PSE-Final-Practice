
package com.pablo.pse5.json;

import com.pablo.pse5.valdavia.Pago;
import com.pablo.pse5.entities.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
public class ValdaviaReader implements MessageBodyReader<Pago>{
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Pago.class.isAssignableFrom(type);
    }

    @Override
    public Pago readFrom(Class<Pago> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Pago pagousuario = new Pago();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "email":
                            pagousuario.setEmail(parser.getString());
                            break;
                        case "estadopago":
                            pagousuario.setPago(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return pagousuario;
    }
}
