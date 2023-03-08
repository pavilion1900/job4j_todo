package ru.job4j.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/tasks";
    }

    @GetMapping("/finished")
    public String findAllFinishedTasks(Model model) {
        model.addAttribute("finishedTasks", taskService.findAllFinishedTasks());
        return "task/finished";
    }

    @GetMapping("/new")
    public String findAllNewTasks(Model model) {
        model.addAttribute("newTasks", taskService.findAllNewTasks());
        return "task/new";
    }

    @GetMapping("/formAdd")
    public String addTask() {
        return "task/add";
    }

    @GetMapping("/formShowDescription/{id}")
    public String formShowDescription(Model model,
                                      @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id));
        return "task/showDescription";
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdateTask(Model model,
                                 @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id));
        return "task/update";
    }

    @GetMapping("/finish/{id}")
    public String finishTask(@PathVariable int id) {
        Task task = taskService.findById(id);
        task.setDone(true);
        taskService.replace(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        task.setCreated(LocalDateTime.now());
        taskService.add(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.replace(task);
        return "redirect:/tasks";
    }
}
