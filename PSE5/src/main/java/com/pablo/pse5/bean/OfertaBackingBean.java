package com.pablo.pse5.bean;

import com.pablo.pse5.client.OfertaClientBean;
import com.pablo.pse5.client.SuscribirClientBean;
import com.pablo.pse5.client.UsuarioClientBean;
import com.pablo.pse5.entities.Oferta;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class OfertaBackingBean implements Serializable {

    private int idOferta;
    private String ofertaNombre;
    private String ofertaDescripcion;
    private Date ofertaFecha;
    private String ofertaPuesto;
    private String ofertaRequisitosMinimos;
    private String ofertaEmailEmpresa="";

    @Inject
    OfertaClientBean ofertaClientBean;

    @Inject
    UsuarioClientBean usuarioClientBean;
    
    @Inject
    SuscribirClientBean suscribirClientBean;

    @Inject
    LoginBackingBean loginBean;

    public Date getCurrentDate(){
        return new Date();
    }
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getOfertaNombre() {
        return ofertaNombre;
    }

    public void setOfertaNombre(String ofertaNombre) {
        this.ofertaNombre = ofertaNombre;
    }

    public String getOfertaDescripcion() {
        return ofertaDescripcion;
    }

    public void setOfertaDescripcion(String ofertaDescripcion) {
        this.ofertaDescripcion = ofertaDescripcion;
    }

    public Date getOfertaFecha() {
        return ofertaFecha;
    }

    public void setOfertaFecha(Date ofertaFecha) {
        this.ofertaFecha = ofertaFecha;
    }

    public String getOfertaPuesto() {
        return ofertaPuesto;
    }

    public void setOfertaPuesto(String ofertaPuesto) {
        this.ofertaPuesto = ofertaPuesto;
    }

    public String getOfertaRequisitosMinimos() {
        return ofertaRequisitosMinimos;
    }

    public void setOfertaRequisitosMinimos(String ofertaRequisitosMinimos) {
        this.ofertaRequisitosMinimos = ofertaRequisitosMinimos;
    }

    public String getOfertaEmailEmpresa() {
        return ofertaEmailEmpresa;
    }

    public void setOfertaEmailEmpresa(String ofertaEmailEmpresa) {
        this.ofertaEmailEmpresa = ofertaEmailEmpresa;
    }

    public void eliminarOferta() {
        ofertaClientBean.deleteOferta(idOferta);
        suscribirClientBean.deleteSuscripcionesPorIdOferta(idOferta);
    }

    public Oferta[] ofertasEmpresa() {
        return ofertaClientBean.getOfertasEmpresa(loginBean.getEmail());
    }

    public void volcarDatosOferta() {
        Oferta o = ofertaClientBean.getOferta();
        ofertaNombre = o.getNombre();
        ofertaDescripcion = o.getDescripcion();
        ofertaFecha = o.getFecha();
        ofertaPuesto = o.getPuesto();
        ofertaRequisitosMinimos = o.getRequisitosMinimos();
        ofertaEmailEmpresa = o.getEmailEmpresa();
    }

    public void modificarOferta() {
        ofertaClientBean.modificarOferta();
    }
    
    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return formato.format(ofertaFecha);
    }
    
    public String getNombreById(int idOferta){
        return ofertaClientBean.getOferta(idOferta).getNombre();
    }
    
    public String getNombreEmpresaByEmail(){
        return usuarioClientBean.getNombreEmpresa(ofertaEmailEmpresa);
    }
    
    
    public void vaciarOferta(){
        ofertaNombre = "";
        ofertaDescripcion = "";
        ofertaFecha = new Date();
        ofertaPuesto = "";
        ofertaRequisitosMinimos = "";
        ofertaEmailEmpresa = "";
    }
}
