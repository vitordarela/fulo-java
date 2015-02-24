/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fulo.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Descrição da classe
 *
 * @name Usuario
 * @author Victor Eduardo Barreto
 * @date Feb 20, 2015
 * @version 1.0
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findBySqUsuario", query = "SELECT u FROM Usuario u WHERE u.sq_pessoa = :sq_pessoa"),
    @NamedQuery(name = "Usuario.findByDsSenha", query = "SELECT u FROM Usuario u WHERE u.ds_senha = :ds_senha")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "sq_pessoa")
    private Integer sq_pessoa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ds_senha")
    private String ds_senha;

    @JoinColumn(name = "sq_perfil", referencedColumnName = "sq_perfil")
    @ManyToOne(optional = false)
    private Perfil sq_perfil;

    @JoinColumn(name = "sq_pessoa", referencedColumnName = "sq_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    public Pessoa pessoa;

    public Usuario() {
    }

    public Usuario(Integer sq_pessoa) {
        this.sq_pessoa = sq_pessoa;
    }

    public Usuario(Integer sq_pessoa, String ds_senha) {
        this.sq_pessoa = sq_pessoa;
        this.ds_senha = ds_senha;
    }

    public Integer getSq_pessoa() {
        return sq_pessoa;
    }

    public void setSq_pessoa(Integer sq_pessoa) {
        this.sq_pessoa = sq_pessoa;
    }

    public String getDs_senha() {
        return ds_senha;
    }

    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }

    public Perfil getSq_perfil() {
        return sq_perfil;
    }

    public void setSq_perfil(Perfil sq_perfil) {
        this.sq_perfil = sq_perfil;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sq_pessoa != null ? sq_pessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.sq_pessoa == null && other.sq_pessoa != null) || (this.sq_pessoa != null && !this.sq_pessoa.equals(other.sq_pessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuario[ sq_pessoa=" + sq_pessoa + " ]";
    }

}
