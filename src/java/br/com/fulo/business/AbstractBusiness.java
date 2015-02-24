/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fulo.business;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Descrição da classe
 *
 * @name MasterBusiness
 * @author Victor Eduardo Barreto
 * @date Feb 8, 2015
 * @version 1.0
 */
public abstract class AbstractBusiness {

    /**
     * Método responsável por aplicar regras negociais para remover usuários
     *
     * @name converteMD5
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param dado Dado a ser criptografado
     * @return Dado encriptado
     * @date Feb 08, 2015
     * @version 1.0
     */
    public String converteMD5(String dado) throws Exception {

        String md5 = null;

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(dado.getBytes(), 0, dado.length());

            md5 = new BigInteger(1, messageDigest.digest()).toString(16);

        } catch (Exception exception) {

            throw exception;
        }

        return md5;
    }

    /**
     * Método responsável por recuperar a sessão
     *
     * @name sessao
     * @author Victor Eduardo Barreto
     * @date Nov 3, 2014
     * @return Object Dados da sessao do usuário logado
     * @version 1.0
     */
    public Object sessao() {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sessao = (HttpSession) externalContext.getSession(false);

        return sessao.getAttribute("usuario");
    }

}
