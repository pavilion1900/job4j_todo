package ru.job4j.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.exception.ModelNotFoundException;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;
import ru.job4j.todo.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> add(User user) {
        return userRepository.add(user);
    }

    @Override
    public User replace(User user) {
        return userRepository.replace(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
