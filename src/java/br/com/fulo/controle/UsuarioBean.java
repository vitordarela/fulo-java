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
import br.com.fulo.modelo.Perfil;
import br.com.fulo.modelo.Pessoa;
import br.com.fulo.modelo.Usuario;
import com.sun.faces.context.flash.ELFlash;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Classe responsável pelas funções de usuário
 *
 * @name UsuarioBean
 * @author Victor Eduardo Barreto
 * @date Nov 4, 2014
 * @version 1.0
 */
@ManagedBean
@ApplicationScoped
public class UsuarioBean {

    // instancia a classe usuario e pessoa.
    private Usuario usuario = new Usuario();

    public UsuarioBean() {
        usuario.setPerfil(new Perfil());
        usuario.setPessoa(new Pessoa());
    }

    // instancia a business de usuário.
    private UsuarioBusiness business = new UsuarioBusiness();

    /**
     * Método responsável por cadastrar usuário
     *
     * @name cadastrar
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @return String Menságens de operação
     * @date Jan 14, 2015
     * @version 1.0
     */
    public String cadastrar() throws Exception {

        try {

            // verifica se a senha confere.
            if (!usuario.getDs_senha().trim().equals(usuario.getConfirma_senha())) {

                // apresenta mensagem de erro.
                ELFlash.getFlash().put("erro", Mensagens.MSG0004);

                // retorna para o formulário.
                return "/usuario/cadastrar.xhtml";
            }

            // pesquisa email informado.
            List pesquisa = business.verificaEmailCadastrado(usuario.pessoa.getDs_email());

            // verifica resultado da comparação.
            if (!pesquisa.isEmpty()) {

                // apresenta mensagem de erro.
                ELFlash.getFlash().put("erro", Mensagens.MSG0005);

                // retorna para o formulário.
                return "/usuario/cadastrar.xhtml";

            }

            // manda dados para a business
            business.cadastrarUsuario(usuario);

            // instancia um novo usuario para não popular o formulário com dados antigos.
            usuario = new Usuario();

            // apresenta mensagem de sucesso.
            ELFlash.getFlash().put("sucesso", Mensagens.MSG0001);

            // retorna para o formulário.
            return "/usuario/cadastrar.xhtml";

        } catch (Exception exception) {

            throw exception;

        }

    }

    /**
     * Método responsável por listar usuarios.
     *
     * @name listarUsuarios
     * @author Victor Eduardo Barreto
     * @date Nov 4, 2014
     * @throws java.lang.Exception
     * @return List Usuários cadastrados
     * @version 1.0
     */
    public List<Usuario> pesquisar() throws Exception {

        List resultado = business.pesquisarUsuarios();

        // verifica se tem resultado.
        if (resultado.isEmpty()) {

            // apresenta mensagem de erro.
            ELFlash.getFlash().put("info", Mensagens.MSG0006);
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/fulo/usuario/pesquisar.xhtml");

        // retorna para o formulário.
        return resultado;

    }

    /**
     * Método responsável por listar usuarios.
     *
     * @name preparaAlteracao
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @return
     * @date Nov 4, 2014
     * @version 1.0
     */
    public String preparaEdicao() throws Exception {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer sq_pessoa = Integer.parseInt(params.get("sq_pessoa"));

        try {

            this.usuario = business.buscarDadosId(sq_pessoa);

            return "/usuario/editar.xhtml";
//            return "/usuario/editar?faces-redirect=true";

        } catch (Exception exception) {

            throw exception;

        }

    }

    /**
     * Método responsável por editar usuário
     *
     * @name editar
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @return String Menságens de operação
     * @date Jan 29, 2015
     * @version 1.0
     */
    public String editar() throws Exception {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        // verifica se tem parametro.
        if (params.get("sq_pessoa") != null) {

            Integer sq_pessoa = Integer.parseInt(params.get("sq_pessoa"));
            this.usuario = business.buscarDadosId(sq_pessoa);

            // redireciona para o form de edição.
            return "/usuario/editar?faces-redirect=true";
        }

        try {

            System.err.println(usuario.pessoa.getDs_email());
            // pesquisa se o email informado existe no banco.
            List pesquisa = business.verificaEmailCadastrado(usuario.pessoa.getDs_email());

            // se nao tiver resultado deixa passar
            if (!pesquisa.isEmpty()) {

                // pega email do banco.
                Usuario email = (Usuario) pesquisa.get(0);

                System.err.println(email);

                // pesquisa dados antigos.
                Usuario antigos = business.buscarDadosId(usuario.pessoa.getSq_pessoa());

                // compara emails.
                if (usuario.pessoa.getDs_email().equals(email.pessoa.getDs_email()) && !usuario.pessoa.getDs_email().equals(antigos.pessoa.getDs_email())) {

                    // apresenta mensagem de erro.
                    ELFlash.getFlash().put("erro", Mensagens.MSG0005);

                    // retorna para o formulário.
                    return "/usuario/editar.xhtml";

                }
            }

            // manda dados para a business
            business.editarUsuario(this.usuario);

            // apresenta mensagem de sucesso.
            ELFlash.getFlash().put("sucesso", Mensagens.MSG0001);

            // recupera a sessão;
            Usuario sessao = (Usuario) business.sessao();

            // verifica se o usuário está alterando ele mesmo e muda o redirect.
            if (!usuario.getSq_pessoa().equals(sessao.getSq_pessoa())) {

                // retorna para pesquisa
                return "/usuario/pesquisar?faces-redirect=true";

            }

            // retorna para index.
            return "/index?faces-redirect=true";

        } catch (Exception exception) {

            throw exception;

        }
    }

    /**
     * Método responsável por remover usuários
     *
     * @name removerUsuario
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @return String Redireciona página
     * @date Jan 30, 2015
     * @version 1.0
     */
    public String remover() throws Exception {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer sq_usuario = Integer.parseInt(params.get("sq_usuario"));

        try {

            business.removerUsuario(sq_usuario);

            ELFlash.getFlash().put("sucesso", Mensagens.MSG0001);

            return "/usuario/pesquisar?faces-redirect=true";

        } catch (Exception exception) {

            throw exception;

        }

    }

    public UsuarioBusiness getBusiness() {
        return business;
    }

    public void setBusiness(UsuarioBusiness business) {
        this.business = business;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
