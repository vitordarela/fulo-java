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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe responsável pelas funções de usuário
 *
 * @name UsuarioBean
 * @author Victor Eduardo Barreto
 * @date Nov 4, 2014
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    // variável para usar na hora de comparar a senha.
    @NotNull
    @Size(min = 4, max = 10)
    private String senha;

    // instancia a classe pessoa.
    private Usuario usuario = new Usuario();
    private Pessoa pessoa = new Pessoa();

    public UsuarioBean() {
        usuario.setSq_perfil(new Perfil());
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
            if (!usuario.getDs_senha().trim().equals(this.senha)) {

                // apresenta mensagem de erro.
                ELFlash.getFlash().put("erro", Mensagens.MSG0004);

                // retorna para o formulário.
                return "/usuario/cadastrar.xhtml";
            }

            // pesquisa email informado.
            List pesquisa = business.verificaEmailCadastrado(pessoa.getDs_email());

            // verifica resultado da comparação.
            if (!pesquisa.isEmpty()) {

                // apresenta mensagem de erro.
                ELFlash.getFlash().put("erro", Mensagens.MSG0005);

                // retorna para o formulário.
                return "/usuario/cadastrar.xhtml";

            }

            // manda dados para a business
            business.cadastrarUsuario(pessoa, usuario);

            // instancia um novo usuario para não popular o formulário com dados antigos.
            pessoa = new Pessoa();
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
    public List<Pessoa> pesquisar() throws Exception {

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

            Pessoa resultado = business.buscarDadosId(sq_pessoa);

            setPessoa(resultado);

            return "/usuario/editar.xhtml";

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

        try {

            // pesquisa se o email informado existe no banco.
            List pesquisa = business.verificaEmailCadastrado(pessoa.getDs_email());

            // se nao tiver resultado deixa passar.
            if (!pesquisa.isEmpty()) {

                // pega o id de pessoa.
                Integer sq_pessoa = this.pessoa.getSq_pessoa();

                // pesquisa dados antigos.
                Pessoa antigos = business.buscarDadosId(sq_pessoa);

                // pega email do banco.
                Pessoa email = (Pessoa) pesquisa.get(0);

                // compara emails.
                if (pessoa.getDs_email().equals(email.getDs_email()) && !pessoa.getDs_email().equals(antigos.getDs_email())) {

                    // apresenta mensagem de erro.
                    ELFlash.getFlash().put("erro", Mensagens.MSG0005);

                    // retorna para o formulário.
                    return "/usuario/editar.xhtml";

                }
            }

            // manda dados para a business
            business.editarUsuario(pessoa);

            // apresenta mensagem de sucesso.
            ELFlash.getFlash().put("sucesso", Mensagens.MSG0001);

            // recupera a sessão;
            Pessoa sessao = (Pessoa) business.sessao();

            // verifica se o usuário está alterando ele mesmo e muda o redirect.
            if (!pessoa.getSq_pessoa().equals(sessao.getSq_pessoa())) {

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

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioBusiness getBusiness() {
        return business;
    }

    public void setBusiness(UsuarioBusiness business) {
        this.business = business;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
