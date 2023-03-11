package ru.job4j.todo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String DELETE_USER = "DELETE FROM User u WHERE u.id = :id";

    private static final String FIND_ALL_USERS = "FROM User u ORDER BY u.id";

    private static final String FIND_BY_ID_USER = "FROM User u WHERE u.id = :id";

    private static final String FIND_BY_LOGIN_AND_PASSWORD_USER =
            "FROM User u WHERE u.login = :login AND u.password = :password";

    private final SessionFactory sf;

    @Override
    public Optional<User> add(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            if (user != null) {
                return Optional.of(user);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public User replace(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        int result = 0;
        try {
            session.beginTransaction();
            result = session.createQuery(DELETE_USER)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result > 0;
    }

    @Override
    public List<User> findAll() {
        Session session = sf.openSession();
        List<User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(FIND_ALL_USERS, User.class);
            users = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(FIND_BY_ID_USER, User.class);
            query.setParameter("id", id);
            User result = query.uniqueResult();
            session.getTransaction().commit();
            if (result != null) {
                return Optional.of(result);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(FIND_BY_LOGIN_AND_PASSWORD_USER, User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            User result = query.uniqueResult();
            session.getTransaction().commit();
            if (result != null) {
                return Optional.of(result);
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
