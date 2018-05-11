
package com.pablo.pse5.suscribirse;

import com.pablo.pse5.client.SuscribirClientBean;
import com.pablo.pse5.entities.Oferta;
import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@FlowScoped("suscribirse")
public class Suscribirse implements Serializable{
    private int idOferta;
    private Oferta oferta;
    private String nombreEmpresa;
    private String cartaPresentacion;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    SuscribirClientBean suscribirBean;
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getCartaPresentacion() {
        return cartaPresentacion;
    }

    public void setCartaPresentacion(String cartaPresentacion) {
        this.cartaPresentacion = cartaPresentacion;
    }

    public void setNombreEmpresa(String nombre) {
        this.nombreEmpresa = nombre;
    }
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    
    public String getEmailEmpresa(){
        return oferta.getEmailEmpresa();
    }
    
    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
    
    public Oferta getOferta(){
        return oferta;
    }
    
    public String getDescripcionOferta(){
        return oferta.getDescripcion();
    }
    
    public void suscribir(){
        suscribirBean.addSuscribir(idOferta,cartaPresentacion);
    }
}
