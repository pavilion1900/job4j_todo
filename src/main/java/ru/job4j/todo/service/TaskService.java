package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;

public interface TaskService {

    Task add(Task task);

    boolean replace(Task task);

    boolean delete(int id);

    List<Task> findAll();

    Task findById(int id);
}
