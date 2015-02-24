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
package br.com.fulo.business;

import br.com.fulo.modelo.Pessoa;
import br.com.fulo.modelo.Usuario;
import br.com.fulo.repositorio.UsuarioRepositorio;
import java.util.List;

/**
 * Classe responsável pelas regras de negócio de usuário
 *
 * @name UsuarioBusiness
 * @author Victor Eduardo Barreto
 * @date Jan 10, 2015
 * @version 1.0
 */
public class UsuarioBusiness extends AbstractBusiness {

    private final UsuarioRepositorio repositorio;

    /**
     * Método responsável pela construção da classe
     *
     * @name UsuarioBusiness
     * @author Victor Eduardo Barreto
     * @date Feb 10, 2015
     * @version 1.0
     */
    public UsuarioBusiness() {
        repositorio = new UsuarioRepositorio();
    }

    /**
     * Método responsável por aplicar regras de negócio para cadastro de usuário
     *
     * @param pessoa
     * @name cadastrarUsuario
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param usuario
     * @date Jan 14, 2015
     * @version 1.0
     */
    public void cadastrarUsuario(Pessoa pessoa, Usuario usuario) throws Exception {

        // critografa a senha com md5.
        usuario.setDs_senha(converteMD5(usuario.getDs_senha()));

        //envia para o repositorio.
        repositorio.cadastra(pessoa, usuario);
    }

    /**
     * Método responsável por verificar se existe email cadastrado
     *
     * @param ds_email
     * @name verificaEmailCadastrado
     * @author Victor Eduardo Barreto
     * @date Jan 11, 2015
     * @throws java.lang.Exception
     * @return List
     * @version 1.0
     */
    public List verificaEmailCadastrado(String ds_email) throws Exception {

        // manda dados para repositorio.
        return repositorio.verificaEmail(ds_email);

    }

    /**
     * Método responsável por recuperar dados de todos usuários
     *
     * @name listaUsuarios
     * @author Victor Eduardo Barreto
     * @date Jan 26, 2015
     * @throws java.lang.Exception
     * @return List Dados de todos os usuários
     * @version 1.0
     */
    public List<Pessoa> pesquisarUsuarios() throws Exception {

        return repositorio.pesquisa();
    }

    /**
     * Método responsável por recuperar dados de todos usuários usando Id
     *
     * @name buscarDadosId
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param id Identificador do registro
     * @return object Dados de usuário
     * @date Jan 26, 2015
     * @version 1.0
     */
    public Pessoa buscarDadosId(Integer id) throws Exception {

        return repositorio.pesquisaPorId(id);
    }

    /**
     * Método responsável por aplicar regras negociais para edição de usuários
     *
     * @name editarUsuario
     * @author Victor Eduardo Barreto
     * @param usuario Dados de usuário
     * @throws java.lang.Exception
     * @date Jan 29, 2015
     * @version 1.0
     */
    public void editarUsuario(Pessoa pessoa) throws Exception {

        // envia para o repositorio.
        repositorio.edita(pessoa);
    }

    /**
     * Método responsável por aplicar regras negociais para remover usuários
     *
     * @name removerUsuario
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param sq_usuario
     * @date Jan 30, 2015
     * @version 1.0
     */
    public void removerUsuario(Integer sq_pessoa) throws Exception {

        // recupera dados do usuário.
        Pessoa pessoa = buscarDadosId(sq_pessoa);

        // manda para a camada repositório.
        repositorio.remove(pessoa);

    }

}
