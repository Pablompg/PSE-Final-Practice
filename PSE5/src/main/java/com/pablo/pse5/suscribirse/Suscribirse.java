
package com.pablo.pse5.suscribirse;

import com.pablo.pse5.client.OfertaClientBean;
import com.pablo.pse5.client.SuscribirClientBean;
import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.Oferta;
import com.pablo.pse5.entities.Suscribir;
import java.io.Serializable;
import java.util.List;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Named
@FlowScoped("suscribirse")
public class Suscribirse implements Serializable{
    private int idOferta;
    private Oferta oferta;
    private String nombreEmpresa;
    private String carta;
    
    @Inject
    SuscribirClientBean suscribirClientBean;
    
    @Inject
    OfertaClientBean ofertaClientBean;
    
    @Inject
    UsuarioClientBean usuarioClientBean;
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
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
    
    public void almacenarOferta(){
        this.oferta = ofertaClientBean.getOfertaFacesFlow(idOferta);
        this.nombreEmpresa = usuarioClientBean.getNombreEmpresa(oferta.getEmailEmpresa());
    }
    
    public boolean getUsuarioSuscrito(){
        return !(suscribirClientBean.getUsuarioSuscrito(idOferta).length == 0);
    }
    
    public Oferta getOferta(){
        return oferta;
    }
    
    public String getDescripcionOferta(){
        return oferta.getDescripcion();
    }
    
    public void suscribir(){
        suscribirClientBean.addSuscribir(idOferta,carta);
    }

    public String getCarta() {
        return carta;
    }

    public void setCarta(String carta) {
        this.carta = carta;
    }
    
}
