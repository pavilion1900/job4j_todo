package ru.job4j.todo.service;

import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> add(User user);

    User replace(User user);

    boolean delete(int id);

    List<User> findAll();

    User findById(int id);

    Optional<User> findUserByLoginAndPassword(String login, String password);
}
