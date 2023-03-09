package ru.job4j.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.exception.ModelNotFoundException;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;
import ru.job4j.todo.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task add(Task task) {
        return taskRepository.add(task);
    }

    @Override
    public Task replace(Task task) {
        return taskRepository.replace(task);
    }

    @Override
    public boolean delete(int id) {
        return taskRepository.delete(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllFinishedTasks() {
        return taskRepository.findAll(Boolean.TRUE);
    }

    @Override
    public List<Task> findAllNewTasks() {
        return taskRepository.findAll(Boolean.FALSE);
    }

    @Override
    public Task findById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(String.format("Task with id %s not found", id)));
    }
}
