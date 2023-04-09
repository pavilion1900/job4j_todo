package ru.job4j.todo.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.CrudRepository;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private static final String DELETE_TASK = "DELETE FROM Task t WHERE t.id = :id";

    private static final String FIND_ALL_TASKS = "FROM Task t ORDER BY t.id";

    private static final String FIND_ALL_BY_CONDITION_TASKS = "FROM Task t WHERE t.done = :done ORDER BY t.id";

    private static final String FIND_BY_ID_TASK = "FROM Task t WHERE t.id = :id";

    private final CrudRepository crudRepository;

    @Override
    public Task add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public Task replace(Task task) {
        crudRepository.run(session -> session.merge(task));
        return task;
    }

    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    DELETE_TASK,
                    Map.of("id", id)
            );
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Task> findAll() {
        return crudRepository.query(FIND_ALL_TASKS, Task.class);
    }

    @Override
    public List<Task> findAll(boolean done) {
        return crudRepository.query(
                FIND_ALL_BY_CONDITION_TASKS, Task.class,
                Map.of("done", done)
        );
    }

    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional(
                FIND_BY_ID_TASK, Task.class,
                Map.of("id", id)
        );
    }
}
