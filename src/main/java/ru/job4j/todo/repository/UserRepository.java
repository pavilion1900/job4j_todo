package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> add(User user);

    User replace(User user);

    boolean delete(int id);

    List<User> findAll();

    Optional<User> findById(int id);

    Optional<User> findByLoginAndPassword(String login, String password);
}
