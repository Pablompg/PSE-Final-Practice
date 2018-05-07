package com.pablo.pse5.json;

import com.pablo.pse5.entities.Suscribir;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
public class SuscribirReader implements MessageBodyReader<Suscribir> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Suscribir.class.isAssignableFrom(type);
    }

    @Override
    public Suscribir readFrom(Class<Suscribir> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Suscribir suscribir = new Suscribir();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "IdSuscribir":
                            suscribir.setIdSuscribir(parser.getInt());
                            break;
                        case "IdOferta":
                            suscribir.setIdOferta(parser.getInt());
                            break;
                        case "EmailCandidato":
                            suscribir.setEmailCandidato(parser.getString());
                            break;
                        case "CartaPresentacion": 
                            suscribir.setCartaPresentacion(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return suscribir;
    }

}
