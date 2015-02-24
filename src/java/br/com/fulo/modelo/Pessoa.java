/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fulo.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 * Modelo de pessoa
 *
 * @name Pessoa
 * @author Victor Eduardo Barreto
 * @date Feb 20, 2015
 * @version 1.0
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer sq_pessoa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String ds_nome;

    @Basic(optional = false)
    @NotNull
    @Email
    @Size(min = 1, max = 45)
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

}
