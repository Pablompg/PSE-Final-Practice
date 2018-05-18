/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablo.pse5.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "suscribir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suscribir.findAll", query = "SELECT s FROM Suscribir s")
    , @NamedQuery(name = "Suscribir.findByIdSuscribir", query = "SELECT s FROM Suscribir s WHERE s.idSuscribir = :idSuscribir")
    , @NamedQuery(name = "Suscribir.findByIdOferta", query = "SELECT s FROM Suscribir s WHERE s.idOferta = :idOferta")
    , @NamedQuery(name = "Suscribir.findByEmailCandidato", query = "SELECT s FROM Suscribir s WHERE s.emailCandidato = :emailCandidato")
    , @NamedQuery(name = "Suscribir.findByOfertaAndCandidato", query = "SELECT s FROM Suscribir s WHERE s.emailCandidato = :emailCandidato AND s.idOferta = :idOferta")
    , @NamedQuery(name = "Suscribir.findByCartaPresentacion", query = "SELECT s FROM Suscribir s WHERE s.cartaPresentacion = :cartaPresentacion")
    , @NamedQuery(name = "Suscribir.CountSuscripcionesPorEmpresa", query = "SELECT new com.pablo.pse5.batch.SuscripcionesOfertaBatch(u.nombre, o.nombre, u.email, COUNT(s.idSuscribir),  o.idOferta)"
                                                                                + "FROM Suscribir AS s "
                                                                                + "LEFT JOIN Oferta AS o ON o.idOferta=s.idOferta "
                                                                                + "LEFT JOIN Usuario AS u ON u.email=o.emailEmpresa "
                                                                                + "GROUP BY o.idOferta")
})
public class Suscribir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_suscribir")
    private Integer idSuscribir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_oferta")
    private int idOferta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email_candidato")
    private String emailCandidato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "carta_presentacion")
    private String cartaPresentacion;

    public Suscribir() {
    }

    public Suscribir(Integer idSuscribir) {
        this.idSuscribir = idSuscribir;
    }

    public Suscribir(Integer idSuscribir, int idOferta, String emailCandidato, String cartaPresentacion) {
        this.idSuscribir = idSuscribir;
        this.idOferta = idOferta;
        this.emailCandidato = emailCandidato;
        this.cartaPresentacion = cartaPresentacion;
    }

    public Integer getIdSuscribir() {
        return idSuscribir;
    }

    public void setIdSuscribir(Integer idSuscribir) {
        this.idSuscribir = idSuscribir;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getEmailCandidato() {
        return emailCandidato;
    }

    public void setEmailCandidato(String emailCandidato) {
        this.emailCandidato = emailCandidato;
    }

    public String getCartaPresentacion() {
        return cartaPresentacion;
    }

    public void setCartaPresentacion(String cartaPresentacion) {
        this.cartaPresentacion = cartaPresentacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSuscribir != null ? idSuscribir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suscribir)) {
            return false;
        }
        Suscribir other = (Suscribir) object;
        if ((this.idSuscribir == null && other.idSuscribir != null) || (this.idSuscribir != null && !this.idSuscribir.equals(other.idSuscribir))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pablo.pse5.entities.Suscribir[ idSuscribir=" + idSuscribir + " ]";
    }

}
