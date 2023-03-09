package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task add(Task task);

    Task replace(Task task);

    boolean delete(int id);

    List<Task> findAll();

    List<Task> findAll(boolean done);

    Optional<Task> findById(int id);
}
