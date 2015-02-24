/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fulo.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Modelo de usuario
 *
 * @name Usuario
 * @author Victor Eduardo Barreto
 * @date Feb 20, 2015
 * @version 1.0
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer sq_pessoa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    private String ds_senha;

    @NotNull
    @Size(min = 4, max = 10)
    @Transient
    private String confirma_senha;

    @JoinColumn(name = "sq_perfil", referencedColumnName = "sq_perfil")
    @ManyToOne(optional = false)
    private Perfil perfil;

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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getConfirma_senha() {
        return confirma_senha;
    }

    public void setConfirma_senha(String confirma_senha) {
        this.confirma_senha = confirma_senha;
    }

}
