/*
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Classe responsável pelos scripts da tela de login
 *
 * @class Login
 * @author Victor Eduardo Barreto
 * @date Feb 4, 2015
 * @version 1.0
 */
var Login = {
    /**
     * Construtor
     *
     * @constructor
     * @author Victor Eduardo Barreto
     * @date Feb 4, 2015
     * @version 1.0
     */
    login: function () {
        Login.validaInputs();
    },
    /**
     * Classe responsável pelos scripts da tela de login
     *
     * @name validaInputs
     * @author Victor Eduardo Barreto
     * @date Feb 4, 2015
     * @version 1.0
     */
    validaInputs: function () {

        // variaveis para os campos.
        var entrar = document.getElementById('login:entrar');
        var email = document.getElementById('login:campo-email').value;
        var senha = document.getElementById('login:campo-senha').value;

        // espera o botão entrar ser acionado.
        $(entrar).click(function () {

            // verifica se o e-mail está vazio.
            if (email === '') {

                $('#div-email').addClass('has-error');
                $(document).find('#helpblock-email').removeClass('hidden');

            } else {

                $('#div-email').removeClass('has-error');
                $(document).find('#helpblock-email').addClass('hidden');
            }

            // verifica se o campo senha está vazio.
            if (senha === '') {

                $('#div-senha').addClass('has-error');
                $(document).find('#helpblock-senha').removeClass('hidden');

            } else {

                $('#div-senha').removeClass('has-error');
                $(document).find('#helpblock-senha').addClass('hidden');
            }

            // verifica formato de email.
            if (email !== '' && !filtro.test(email)) {

                $('#div-email').addClass('has-error');
                $(document).find('#helpblock-email').text('O formato do e-mail é inválido');
                $(document).find('#helpblock-email').removeClass('hidden');

            } else {

                $(document).find('#helpblock-email').text('O campo deve ser preenchido');
            }

        });

        // filtro para email.
        var filtro = Login.filtroEmail();

        // testa todas as condições e submete.
        if (email !== '' && senha !== '' && filtro.test(email)) {

            // submete o formulário.
            return true;
        } else {

            return false;
        }

    },
    /**
     * Método responsável por configurar filtro de email.
     *
     * @name filtroEmail
     * @author Victor Eduardo Barreto
     * @date Feb 4, 2015
     * @version 1.0
     */
    filtroEmail: function () {

        var filtro = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

        return filtro;
    }

};

$(document).ready(function () {
    Login.login();
});


