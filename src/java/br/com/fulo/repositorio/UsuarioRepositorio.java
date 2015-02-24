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
import br.com.fulo.modelo.Pessoa;
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

    // variavel para receber a conexão.
    private final EntityManager manager;

    /**
     * Método responsável pela construção da classe
     *
     * @name UsuarioRepositorio
     * @author Victor Eduardo Barreto
     * @date Feb 10, 2015
     * @version 1.0
     */
    public UsuarioRepositorio() {

        // recebe a conexão na variavel manager.
        manager = Conexao.getEntityManager();
    }

    /**
     * Método responsável por adicionar usuário
     *
     * @param usuario
     * @name adiciona
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param pessoa
     * @date Nov 4, 2014
     * @version 1.0
     */
    public void cadastra(Pessoa pessoa, Usuario usuario) throws Exception {

        try {

            // inicia transação.
            manager.getTransaction().begin();

            // insere pessoa.
            manager.persist(pessoa);

            // pega sq_pessoa.
            usuario.setSq_pessoa(pessoa.getSq_pessoa());

            // persiste usuario.
            manager.persist(usuario);

            // commita.
            manager.getTransaction().commit();

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
    public List<Pessoa> pesquisa() throws Exception {

        List retorno;

        // executa a query.
        try {

            // monta a query com entity manager.
            Query query = manager.createQuery("SELECT p FROM Pessoa p");

            // salva o resultado.
            retorno = query.getResultList();

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

        // exexuta a query.
        try {

            // monta a query com entity manager.
            Query query = manager.createQuery("SELECT p FROM Pessoa p WHERE p.ds_email = :ds_email");

            // monta a clausula where.
            query.setParameter("ds_email", ds_email);

            // salva o resultado.
            return query.getResultList();

        } catch (Exception exception) {
            throw exception;
        }

    }

    /**
     * Método responsável por verificar email cadastrado
     *
     * @name pesquisaPorId
     * @author Victor Eduardo Barreto
     * @throws java.lang.Exception
     * @param id Identificador do registro
     * @return object Dados de usuário
     * @date Nov 20, 2014
     * @version 1.0
     */
    public Pessoa pesquisaPorId(Integer sq_pessoa) throws Exception {

        try {

            return manager.find(Pessoa.class, sq_pessoa);
        } catch (Exception exception) {
            throw exception;
        }

    }

    /**
     * Método responsável por editar usuário
     *
     * @name edita
     * @author Victor Eduardo Barreto
     * @param pessoa
     * @throws java.lang.Exception
     * @date Jan 29, 2015
     * @version 1.0
     */
    public void edita(Pessoa pessoa) throws Exception {

        try {

            // inicia transação.
            manager.getTransaction().begin();

            // atualiza usuário.
            manager.merge(pessoa);

            // commita.
            manager.getTransaction().commit();

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
     * Método responsável por deletar usuário
     *
     * @name remove
     * @author Victor Eduardo Barreto
     * @param pessoa Dados de usuário
     * @throws java.lang.Exception
     * @date Jan 29, 2015
     * @version 1.0
     */
    public void remove(Pessoa pessoa) throws Exception {

        try {

            // inicia transação.
            manager.getTransaction().begin();

            // remove usuário.
            manager.remove(pessoa);

            // commita.
            manager.getTransaction().commit();

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
