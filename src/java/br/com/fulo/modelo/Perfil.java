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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Descrição da classe
 *
 * @name Perfil
 * @author Victor Eduardo Barreto
 * @date Feb 20, 2015
 * @version 1.0
 */
@Entity
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findBySqPerfil", query = "SELECT p FROM Perfil p WHERE p.sq_perfil = :sq_perfil"),
    @NamedQuery(name = "Perfil.findByDePerfil", query = "SELECT p FROM Perfil p WHERE p.de_perfil = :de_perfil")})
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sq_perfil")
    private Integer sq_perfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "de_perfil")
    private String de_perfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sq_perfil")
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sq_perfil != null ? sq_perfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.sq_perfil == null && other.sq_perfil != null) || (this.sq_perfil != null && !this.sq_perfil.equals(other.sq_perfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Perfil[ sq_perfil=" + sq_perfil + " ]";
    }

}
