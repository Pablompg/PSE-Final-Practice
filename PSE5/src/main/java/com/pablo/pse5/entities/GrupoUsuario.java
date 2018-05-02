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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "grupo_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoUsuario.findAll", query = "SELECT g FROM GrupoUsuario g")
    , @NamedQuery(name = "GrupoUsuario.findByEmailUsuario", query = "SELECT g FROM GrupoUsuario g WHERE g.emailUsuario = :emailUsuario")
    , @NamedQuery(name = "GrupoUsuario.findByNombreGrupo", query = "SELECT g FROM GrupoUsuario g WHERE g.nombreGrupo = :nombreGrupo")})
public class GrupoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email_usuario")
    private String emailUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    public GrupoUsuario() {
    }

    public GrupoUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public GrupoUsuario(String emailUsuario, String nombreGrupo) {
        this.emailUsuario = emailUsuario;
        this.nombreGrupo = nombreGrupo;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailUsuario != null ? emailUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        if ((this.emailUsuario == null && other.emailUsuario != null) || (this.emailUsuario != null && !this.emailUsuario.equals(other.emailUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pablo.pse5.entities.GrupoUsuario[ emailUsuario=" + emailUsuario + " ]";
    }
    
}
