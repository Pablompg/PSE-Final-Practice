/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablo.pse5.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author usuario
 */
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
        resources.add(com.pablo.pse5.rest.GrupoUsuarioFacadeREST.class);
        resources.add(com.pablo.pse5.rest.OfertaFacadeREST.class);
        resources.add(com.pablo.pse5.rest.SuscribirFacadeREST.class);
        resources.add(com.pablo.pse5.rest.UsuarioFacadeREST.class);
    }
    
}
