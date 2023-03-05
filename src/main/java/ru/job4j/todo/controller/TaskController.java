package ru.job4j.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public String findAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/finishedTasks")
    public String findAllFinishedTasks(Model model) {
        List<Task> finishedTasks = taskService.findAll().stream()
                .filter(Task::isDone)
                .collect(toList());
        model.addAttribute("finishedTasks", finishedTasks);
        return "finishedTasks";
    }

    @GetMapping("/newTasks")
    public String findAllNewTasks(Model model) {
        List<Task> newTasks = taskService.findAll().stream()
                .filter(task -> !task.isDone())
                .collect(toList());
        model.addAttribute("newTasks", newTasks);
        return "newTasks";
    }

    @GetMapping("/formAddTask")
    public String addTask() {
        return "addTask";
    }

    @GetMapping("/formShowDescription/{taskId}")
    public String formShowDescription(Model model,
                                      @PathVariable("taskId") Integer id) {
        model.addAttribute("task", taskService.findById(id));
        return "showDescription";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateCandidate(Model model,
                                      @PathVariable("taskId") Integer id) {
        model.addAttribute("task", taskService.findById(id));
        return "updateTask";
    }

    @GetMapping("/finishTask/{taskId}")
    public String finishTask(@PathVariable("taskId") Integer id) {
        Task task = taskService.findById(id);
        task.setDone(true);
        taskService.replace(task);
        return "redirect:/tasks";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable("taskId") Integer id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        task.setCreated(LocalDateTime.now());
        taskService.add(task);
        return "redirect:/tasks";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        taskService.replace(task);
        return "redirect:/tasks";
    }
}
