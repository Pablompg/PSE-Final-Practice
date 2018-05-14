
package com.pablo.pse5.bean;

import com.pablo.pse5.client.OfertaClientBean;
import com.pablo.pse5.client.SuscribirClientBean;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class OfertaBackingBean implements Serializable{
    
    private int idOferta;
    private String ofertaNombre;
    private String ofertaDescripcion; 
    private Date ofertaFecha; 
    private String ofertaPuesto; 
    private String ofertaRequisitosMinimos;
    private String ofertaEmailEmpresa;
    
    @Inject
    OfertaClientBean ofertaClientBean;

    @Inject
    SuscribirClientBean suscribirClientBean;
    
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
    
    public void eliminarOferta(){
        ofertaClientBean.deleteOferta();
        suscribirClientBean.deleteSuscripcionesPorIdOferta(idOferta);
    }
}
