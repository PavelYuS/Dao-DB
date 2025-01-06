package jm.task.core.jdbc.dao;

import com.mysql.cj.Query;
//import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoHibernateImpl.class);
    private final SessionFactory factory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        String createTableSql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age TINYINT)";

        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {

            transaction = session.beginTransaction();

            session.createSQLQuery(createTableSql).addEntity(User.class).executeUpdate();

            transaction.commit();
            logger.info("Таблица 'users' успешно создана.");
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.warn("Транзакция откатилась из-за ошибки.");
                } catch (HibernateException rollbackEx) {
                    logger.error("Ошибка при откате транзакции.", rollbackEx);
                }
            }
            logger.error("Ошибка при создании таблицы 'users'.", e);
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTableSql = "DROP TABLE IF EXISTS users";

        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {

            transaction = session.beginTransaction();

            session.createSQLQuery(dropTableSql).addEntity(User.class).executeUpdate();

            transaction.commit();
            logger.info("Таблица 'users' успешно удалена.");
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.warn("Транзакция откатилась из-за ошибки.");
                } catch (HibernateException rollbackEx) {
                    logger.error("Ошибка при откате транзакции.", rollbackEx);
                }
            }
            logger.error("Ошибка при удалении таблицы 'users'.", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {

            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
            logger.info("Ползователь успешно добавлен в базу данных.");
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.warn("Транзакция откатилась из-за ошибки.");
                } catch (HibernateException rollbackEx) {
                    logger.error("Ошибка при откате транзакции.", rollbackEx);
                }
            }
            logger.error("Ошибка при добавлении пользователя в базу данных", e);
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {

            transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));

            transaction.commit();
            logger.info("Ползователь успешно удален из базы данных.");
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.warn("Транзакция откатилась из-за ошибки.");
                } catch (HibernateException rollbackEx) {
                    logger.error("Ошибка при откате транзакции.", rollbackEx);
                }
            }
            logger.error("Ошибка при удалении пользователя из базы данных", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).getResultList();
            transaction.commit();
            logger.info("Получен список пользователей.");
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.warn("Транзакция откатилась из-за ошибки.");
                } catch (HibernateException rollbackEx) {
                    logger.error("Ошибка при откате транзакции.", rollbackEx);
                }
            }
            logger.error("Ошибка при получении списка пользователей", e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {

            transaction = session.beginTransaction();

            session.createQuery("DELETE User").executeUpdate();

            transaction.commit();
            logger.info("Все данные из таблицы были удалены.");
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.warn("Транзакция откатилась из-за ошибки.");
                } catch (HibernateException rollbackEx) {
                    logger.error("Ошибка при откате транзакции.", rollbackEx);
                }
            }
            logger.error("Ошибка при очистке таблицы пользователей", e);
        }
    }
}
