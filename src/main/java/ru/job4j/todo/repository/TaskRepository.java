package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;

public interface TaskRepository {

    Task add(Task task);

    Task replace(Task task);

    boolean delete(int id);

    List<Task> findAll();

    Task findById(int id);
}
