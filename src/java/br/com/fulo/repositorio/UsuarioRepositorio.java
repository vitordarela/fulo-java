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
package br.com.fulo.repositorio;

import br.com.fulo.config.Conexao;
import br.com.fulo.modelo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe responsável por persistir os dados de usuário
 *
 * @name UsuarioRepository
 * @author Victor Eduardo Barreto
 * @date Nov 4, 2014
 * @version 1.0
 */
public class UsuarioRepositorio {

    /**
     * Método responsável pela construção da classe
     *
     * @name UsuarioRepositorio
     * @author Victor Eduardo Barreto
     * @date Feb 10, 2015
     * @version 1.0
     */
    public UsuarioRepositorio() {

    }

    /**
     * Método responsável por adicionar usuário
     *
     * @name adiciona
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param usuario Dados de usuario
     * @date Nov 4, 2014
     * @version 1.0
     */
    public void cadastra(Usuario usuario) throws Exception {

        EntityManager manager = Conexao.getEntityManager();

        try {

            // inicia transação.
            manager.getTransaction().begin();

            // insere usuario.
            manager.persist(usuario);

            manager.getTransaction().commit();
            manager.close();

        } catch (Exception exception) {

            // se der erro, faz rollback.
            manager.getTransaction().rollback();

            // lança exceção.
            throw exception;

        } finally {

            // se a conexão estiver aberta, fecha.
            if (manager.isOpen()) {
                manager.close();
            }
        }

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
    public List<Usuario> pesquisa() throws Exception {

        EntityManager manager = Conexao.getEntityManager();

        List retorno;

        try {

            manager.getTransaction().begin();

            // monta a query com entity manager.
            Query query = manager.createQuery("SELECT u FROM Usuario u");

            // salva o resultado.
            retorno = query.getResultList();
            manager.close();

        } catch (Exception exception) {
            throw exception;
        }

        return retorno;

    }

    /**
     * Método responsável por verificar email cadastrado
     *
     * @name verificaEmail
     * @author Victor Eduardo Barreto
     * @param ds_email Email que será verificado
     * @throws java.lang.Exception
     * @return List
     * @date Nov 20, 2014
     * @version 1.0
     */
    public List verificaEmail(String ds_email) throws Exception {

        List retorno;

        EntityManager manager = Conexao.getEntityManager();

        try {

            manager.getTransaction().begin();

            // monta a query com entity manager.
            Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.pessoa.ds_email = :ds_email");

            // monta a clausula where.
            query.setParameter("ds_email", ds_email);

            // salva o resultado.
            retorno = query.getResultList();
            manager.close();

        } catch (Exception exception) {
            throw exception;
        }

        return retorno;
    }

    /**
     * Método responsável por verificar email cadastrado
     *
     * @name pesquisaPorId
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param sq_pessoa Identificador do registro
     * @return object Dados de usuário
     * @date Nov 20, 2014
     * @version 1.0
     */
    public Usuario pesquisaPorId(Integer sq_pessoa) throws Exception {

        Usuario retorno;

        EntityManager manager = Conexao.getEntityManager();

        try {

            manager.getTransaction().begin();

            retorno = manager.find(Usuario.class, sq_pessoa);
            manager.close();

        } catch (Exception exception) {
            throw exception;
        }

        return retorno;

    }

    /**
     * Método responsável por editar usuário
     *
     * @name edita
     * @author Victor Eduardo Barreto
     * @param usuario
     * @throws java.lang.Exception
     * @date Jan 29, 2015
     * @version 1.0
     */
    public void edita(Usuario usuario) throws Exception {

        EntityManager manager = Conexao.getEntityManager();

        try {

            manager.getTransaction().begin();

            System.err.println(usuario.pessoa.getDs_nome());

            manager.merge(usuario);
            manager.flush();

            manager.getTransaction().commit();

        } catch (Exception exception) {
            manager.getTransaction().rollback();

            // lança exceção.
            throw exception;

        } finally {

            // se a conexão estiver aberta, fecha.
            if (manager.isOpen()) {
                manager.close();
            }
        }

    }

    /**
     * Método responsável por deletar usuário
     *
     * @name remove
     * @author Victor Eduardo Barreto
     * @param usuario Dados de usuário
     * @throws java.lang.Exception
     * @date Jan 29, 2015
     * @version 1.0
     */
    public void remove(Usuario usuario) throws Exception {

        EntityManager manager = Conexao.getEntityManager();

        try {

            // inicia transação.
            manager.getTransaction().begin();

            // remove usuário.
            manager.remove(usuario);

            // commita.
            manager.getTransaction().commit();
            manager.close();

        } catch (Exception exception) {

            // se der erro, faz rollback.
            manager.getTransaction().rollback();

            // lança exceção.
            throw exception;

        } finally {

            // se a conexão estiver aberta, fecha.
            if (manager.isOpen()) {
                manager.close();
            }
        }

    }

}
