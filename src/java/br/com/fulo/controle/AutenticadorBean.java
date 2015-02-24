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
package br.com.fulo.controle;

import br.com.fulo.business.UsuarioBusiness;
import br.com.fulo.config.Mensagens;
import br.com.fulo.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

/**
 * Bean responsável pela autenticação no sistema
 *
 * @name AutenticadorBean
 * @author Victor Eduardo Barreto
 * @date Nov 3, 2014
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class AutenticadorBean {

    @NotNull
    @Email
    private String ds_email;

    @NotNull
    private String ds_senha;

    // instancia a business de usuário.
    UsuarioBusiness business = new UsuarioBusiness();

    /**
     * Método responsável pelo login de usuário
     *
     * @name autentica
     * @author Victor Eduardo Barreto
     * @date Nov 3, 2014
     * @throws java.io.IOException
     * @return String Url de retorno
     * @version 1.0
     */
    public String autentica() throws IOException, Exception {

        // pesquisa com base no email.
        List resultado = business.verificaEmailCadastrado(ds_email);

        if (!resultado.isEmpty()) {

            // recupera dados da pesquisa.
            Usuario usuario = (Usuario) resultado.get(0);

            // verifica se as credenciais estao certas.
            if (usuario.getPessoa().getDs_email().equals(ds_email) && usuario.getDs_senha().equals(business.converteMD5(ds_senha))) {

                // inicia a sessão.
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession sessao = (HttpSession) externalContext.getSession(false);

                // salva dados na sessão.
                sessao.setAttribute("usuario", usuario);

                // apresenta mensagem e retorna para a tela inicial.
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("sucesso", Mensagens.MSG0001);
                return ("/index.xhtml");

            }

        }

        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("erro", Mensagens.MSG0003);
        return ("/index.xhtml");

    }

    /**
     * Método responsável pelo logoff de usuário
     *
     * @name registraSaida
     * @author Victor Eduardo Barreto
     * @date Nov 3, 2014
     * @return String redirecionamento de página
     * @version 1.0
     */
    public String registraSaida() {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sessao = (HttpSession) externalContext.getSession(false);
        sessao.removeAttribute("usuario");

        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("sucesso", Mensagens.MSG0001);

        return "/index.xhtml";

    }

    /**
     * Método responsável por recuperar a sessão
     *
     * @name recuperaSessao
     * @author Victor Eduardo Barreto
     * @date Nov 3, 2014
     * @return Object Dados da sessao do usuário logado
     * @version 1.0
     */
    public Object recuperaSessao() {

        return business.sessao();

    }

    /**
     *
     * @return
     */
    public String getDs_email() {
        return ds_email;
    }

    /**
     *
     * @param ds_email
     */
    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    /**
     *
     * @return
     */
    public String getDs_senha() {
        return ds_senha;
    }

    /**
     *
     * @param ds_senha
     */
    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }

}
