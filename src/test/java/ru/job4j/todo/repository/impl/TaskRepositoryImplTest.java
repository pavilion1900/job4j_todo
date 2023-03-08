package ru.job4j.todo.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TaskRepositoryImplTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void clearTableBeforeTest() {
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> taskRepository.delete(task.getId()));
    }

    @AfterEach
    void clearTableAfterTest() {
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> taskRepository.delete(task.getId()));
    }

    @Test
    void whenAddTask() {
        Task task = Task.builder()
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .build();
        Task actual = taskRepository.add(task);
        int size = taskRepository.findAll().size();
        assertAll(
                () -> assertThat(size).isEqualTo(1),
                () -> assertThat(actual.getDescription()).isEqualTo("PRES-1148: Подключить Spring Security")
        );
    }

    @Test
    void whenReplaceTask() {
        Task task = Task.builder()
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .build();
        Task savedTask = taskRepository.add(task);
        Task newTask = Task.builder()
                .id(savedTask.getId())
                .description("PRES-1149: Подключить Spring AOP")
                .created(LocalDateTime.now())
                .build();
        taskRepository.replace(newTask);
        Optional<Task> optional = taskRepository.findById(savedTask.getId());
        int size = taskRepository.findAll().size();
        assertThat(size).isEqualTo(1);
        optional.ifPresent(replacedTask -> assertThat(replacedTask.getDescription()).isEqualTo("PRES-1149: Подключить Spring AOP"));
    }

    @Test
    void whenDeleteTask() {
        Task task = Task.builder()
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .build();
        Task savedTask = taskRepository.add(task);
        boolean result = taskRepository.delete(savedTask.getId());
        assertTrue(result);
    }

    @Test
    void whenFindAllTasks() {
        Task firstTask = Task.builder()
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .build();
        Task secondTask = Task.builder()
                .description("PRES-1149: Подключить Spring AOP")
                .created(LocalDateTime.now())
                .build();
        taskRepository.add(firstTask);
        taskRepository.add(secondTask);
        int size = taskRepository.findAll().size();
        assertThat(size).isEqualTo(2);
    }

    @Test
    void whenFindByIdTask() {
        Task task = Task.builder()
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .build();
        Task savedTask = taskRepository.add(task);
        Optional<Task> optional = taskRepository.findById(savedTask.getId());
        optional.ifPresent(findByIdTask -> assertThat(savedTask.getDescription()).isEqualTo(findByIdTask.getDescription()));
    }
}
