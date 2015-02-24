/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fulo.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Modelo de perfil
 *
 * @name Perfil
 * @author Victor Eduardo Barreto
 * @date Feb 20, 2015
 * @version 1.0
 */
@Entity
@Table(name = "perfil", schema = "dominio")
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer sq_perfil;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String de_perfil;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private Collection<Usuario> usuarioCollection;

    public Perfil() {
    }

    public Perfil(Integer sq_perfil) {
        this.sq_perfil = sq_perfil;
    }

    public Perfil(Integer sq_perfil, String de_perfil) {
        this.sq_perfil = sq_perfil;
        this.de_perfil = de_perfil;
    }

    public Integer getSq_perfil() {
        return sq_perfil;
    }

    public void setSq_perfil(Integer sq_perfil) {
        this.sq_perfil = sq_perfil;
    }

    public String getDe_perfil() {
        return de_perfil;
    }

    public void setDe_perfil(String de_perfil) {
        this.de_perfil = de_perfil;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

}
