package com.pablo.pse5.json;

import com.pablo.pse5.entities.Oferta;
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
public class OfertaReader implements MessageBodyReader<Oferta> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Oferta.class.isAssignableFrom(type);
    }

    @Override
    public Oferta readFrom(Class<Oferta> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Oferta oferta = new Oferta();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "idOferta":
                            oferta.setIdOferta(parser.getInt());
                            break;
                        case "nombre":
                            oferta.setNombre(parser.getString());
                            break;
                        case "descripcion":
                            oferta.setDescripcion(parser.getString());
                            break;
                        case "fecha": 
                            try {
                                oferta.setFecha(format.parse(parser.getString()));
                            } catch (ParseException ex) {
                                Logger.getLogger(OfertaReader.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case "puesto":
                            oferta.setPuesto(parser.getString());
                            break;
                        case "requisitosMinimos":
                            oferta.setRequisitosMinimos(parser.getString());
                            break;
                        case "emailEmpresa":
                            oferta.setEmailEmpresa(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return oferta;
    }

}
