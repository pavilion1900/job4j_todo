package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;

public interface TaskService {

    Task add(Task task);

    Task replace(Task task);

    boolean delete(int id);

    List<Task> findAll();

    List<Task> findAllFinishedTasks();

    List<Task> findAllNewTasks();

    Task findById(int id);
}
