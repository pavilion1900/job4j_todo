package ru.job4j.todo.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.CrudRepository;
import ru.job4j.todo.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String DELETE_USER = "DELETE FROM User u WHERE u.id = :id";

    private static final String FIND_ALL_USERS = "FROM User u ORDER BY u.id";

    private static final String FIND_BY_ID_USER = "FROM User u WHERE u.id = :id";

    private static final String FIND_BY_LOGIN_AND_PASSWORD_USER =
            "FROM User u WHERE u.login = :login AND u.password = :password";

    private final CrudRepository crudRepository;

    @Override
    public Optional<User> add(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public User replace(User user) {
        crudRepository.run(session -> session.merge(user));
        return user;
    }

    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    DELETE_USER,
                    Map.of("id", id)
            );
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return crudRepository.query(FIND_ALL_USERS, User.class);
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional(
                FIND_BY_ID_USER, User.class,
                Map.of("id", id)
        );
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                FIND_BY_LOGIN_AND_PASSWORD_USER, User.class,
                Map.of("login", login, "password", password)
        );
    }
}
