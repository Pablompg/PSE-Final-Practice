
package com.pablo.pse5.suscribirse;

import com.pablo.pse5.client.OfertaClientBean;
import com.pablo.pse5.client.SuscribirClientBean;
import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.Oferta;
import com.pablo.pse5.valdavia.ValdaviaClientBean;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FlowScoped("suscribirse")
public class Suscribirse implements Serializable{
    private int idOferta;
    private Oferta oferta;
    private String nombreEmpresa;
    private String carta;
    private String estadoPagos;
    @Inject
    SuscribirClientBean suscribirClientBean;
    @Inject
    OfertaClientBean ofertaClientBean;
    @Inject
    UsuarioClientBean usuarioClientBean;
    @Inject
    ValdaviaClientBean valdaviaClientBean;
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
    public Oferta getOferta(){
        return oferta;
    }
    
    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public void setNombreEmpresa(String nombre) {
        this.nombreEmpresa = nombre;
    }
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getCarta() {
        return carta;
    }

    public void setCarta(String carta) {
        this.carta = carta;
    }
    
    public String getEstadoPagos(){
        return estadoPagos;
    }
    
    public void setOferta(String estadoPagos) {
        this.estadoPagos = estadoPagos;
    }
    
    public void almacenarOferta(){
        this.oferta = ofertaClientBean.getOfertaFacesFlow(idOferta);
        this.nombreEmpresa = usuarioClientBean.getNombreEmpresa(oferta.getEmailEmpresa());
    }
    
    public String getDescripcionOferta(){
        return oferta.getDescripcion();
    }
    
    public String getNombreOferta(){
        return oferta.getNombre();
    }
    
    public String getPuestoOferta(){
        return oferta.getPuesto();
    }
    
    public String getRequisitosMinimosOferta(){
        return oferta.getRequisitosMinimos();
    }
    
    public String getFechaOferta(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(oferta.getFecha());
    }
    
    public String getEmailEmpresa(){
        return oferta.getEmailEmpresa();
    }
    
    public boolean getUsuarioSuscrito(){
        //return false if user is not suscribed
        return !(suscribirClientBean.getUsuarioSuscrito(idOferta).length == 0);
    }
    
    public void suscribir(){
        suscribirClientBean.addSuscribir(idOferta,carta);
    }
    
    public void estadoPagos(){
        this.estadoPagos = valdaviaClientBean.getEstado().getPago();
    }
    
}
