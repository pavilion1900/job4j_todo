package ru.job4j.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

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
    public boolean replace(Task task) {
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
    public Task findById(int id) {
        return taskRepository.findById(id);
    }
}
