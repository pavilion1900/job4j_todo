package ru.job4j.todo.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void whenAddTask() {
        Task task = Task.builder()
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .build();
        Task expected = Task.builder()
                .id(1)
                .description("PRES-1148: Подключить Spring Security")
                .created(LocalDateTime.now())
                .done(false)
                .build();
        doReturn(expected)
                .when(taskRepository).add(task);
        Task actual = taskService.add(task);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenReplaceTask() {
        Task task = Task.builder()
                .id(1)
                .description("PRES-1148: Подключить Spring AOP")
                .created(LocalDateTime.now())
                .done(false)
                .build();
        Task expected = Task.builder()
                .id(1)
                .description("PRES-1148: Подключить Spring AOP")
                .created(LocalDateTime.now())
                .done(false)
                .build();
        doReturn(expected)
                .when(taskRepository).replace(task);
        Task actual = taskService.replace(task);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenDeleteTask() {
        Task task = Task.builder()
                .id(1)
                .description("PRES-1148: Подключить Spring AOP")
                .created(LocalDateTime.now())
                .done(false)
                .build();
        doReturn(true)
                .when(taskRepository).delete(task.getId());
        boolean result = taskService.delete(task.getId());
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
        List<Task> tasks = List.of(firstTask, secondTask);
        doReturn(tasks)
                .when(taskRepository).findAll();
        int size = taskRepository.findAll().size();
        assertThat(size).isEqualTo(2);
    }

    @Test
    void whenFindByIdTask() {
        Task task = Task.builder()
                .id(1)
                .description("PRES-1148: Подключить Spring AOP")
                .created(LocalDateTime.now())
                .done(false)
                .build();
        doReturn(task)
                .when(taskRepository).findById(task.getId());
        Task actual = taskRepository.findById(task.getId());
        assertThat(actual).isEqualTo(task);
    }
}
