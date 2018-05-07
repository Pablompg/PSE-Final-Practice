
package com.pablo.pse5.rest;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.pablo.pse5.json.OfertaReader.class);
        resources.add(com.pablo.pse5.json.OfertaWriter.class);
        resources.add(com.pablo.pse5.json.SuscribirReader.class);
        resources.add(com.pablo.pse5.json.SuscribirWriter.class);
        resources.add(com.pablo.pse5.json.UsuarioReader.class);
        resources.add(com.pablo.pse5.json.UsuarioWriter.class);
        resources.add(com.pablo.pse5.rest.GrupoUsuarioFacadeREST.class);
        resources.add(com.pablo.pse5.rest.OfertaFacadeREST.class);
        resources.add(com.pablo.pse5.rest.SuscribirFacadeREST.class);
        resources.add(com.pablo.pse5.rest.UsuarioFacadeREST.class);
    }
    
}
