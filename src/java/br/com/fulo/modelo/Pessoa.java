/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fulo.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Descrição da classe
 *
 * @name Pessoa
 * @author Victor Eduardo Barreto
 * @date Feb 20, 2015
 * @version 1.0
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findBySqPessoa", query = "SELECT p FROM Pessoa p WHERE p.sq_pessoa = :sq_pessoa"),
    @NamedQuery(name = "Pessoa.findByDsNome", query = "SELECT p FROM Pessoa p WHERE p.ds_nome = :ds_nome"),
    @NamedQuery(name = "Pessoa.findByDsEmail", query = "SELECT p FROM Pessoa p WHERE p.ds_email = :ds_email")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sq_pessoa")
    private Integer sq_pessoa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ds_nome")
    private String ds_nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ds_email")
    private String ds_email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Usuario usuario;

    public Pessoa() {
    }

    public Pessoa(Integer sq_pessoa) {
        this.sq_pessoa = sq_pessoa;
    }

    public Pessoa(Integer sq_pessoa, String ds_nome, String ds_email) {
        this.sq_pessoa = sq_pessoa;
        this.ds_nome = ds_nome;
        this.ds_email = ds_email;
    }

    public Integer getSq_pessoa() {
        return sq_pessoa;
    }

    public void setSq_pessoa(Integer sq_pessoa) {
        this.sq_pessoa = sq_pessoa;
    }

    public String getDs_nome() {
        return ds_nome;
    }

    public void setDs_nome(String ds_nome) {
        this.ds_nome = ds_nome;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.sq_pessoa == null && other.sq_pessoa != null) || (this.sq_pessoa != null && !this.sq_pessoa.equals(other.sq_pessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pessoa[ sq_pessoa=" + sq_pessoa + " ]";
    }

}
