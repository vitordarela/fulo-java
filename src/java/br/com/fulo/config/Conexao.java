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
package br.com.fulo.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Classe responsável por criar o manager factory
 *
 * @name Conexao
 * @author Victor Eduardo Barreto
 * @date Jan 17, 2015
 * @version 1.0
 */
public class Conexao {

    private static final String PERSISTENCE_UNIT = "fulo-pu";
    private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<>();
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Método responsável por entregar uma conexão.
     *
     * @name closeEntityManager
     * @author Victor Eduardo Barreto
     * @date Feb 10, 2015
     * @return Object Conexão
     * @version 1.0
     */
    public static EntityManager getEntityManager() {

        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

        EntityManager entityManager = threadEntityManager.get();

        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
            Conexao.threadEntityManager.set(entityManager);
        }

        return entityManager;

    }

    /**
     * Método responsável por fechar a transação.
     *
     * @name closeEntityManager
     * @author Victor Eduardo Barreto
     * @date Feb 10, 2015
     * @version 1.0
     */
    public static void closeEntityManager() {

        EntityManager em = threadEntityManager.get();

        if (em != null) {
            EntityTransaction transaction = em.getTransaction();

            if (transaction.isActive()) {
                transaction.commit();
            }

            em.close();
            threadEntityManager.set(null);
        }
    }

    /**
     * Método responsável por fechar a conexão.
     *
     * @name closeEntityManager
     * @author Victor Eduardo Barreto
     * @date Feb 10, 2015
     * @version 1.0
     */
    public static void closeEntityManagerFactory() {

        closeEntityManager();
        entityManagerFactory.close();

    }
}
