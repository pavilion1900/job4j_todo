package ru.job4j.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;
import ru.job4j.todo.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String findAll(Model model,
                          HttpSession session) {
        User user = SessionUtil.checkUser(session);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model,
                            @RequestParam(name = "fail", required = false) Boolean fail,
                            HttpSession session) {
        User user = SessionUtil.checkUser(session);
        model.addAttribute("user", user);
        model.addAttribute("fail", fail != null);
        return "user/login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/loginPage";
    }

    @GetMapping("/formAddUser")
    public String addUser(Model model,
                          @RequestParam(name = "fail", required = false) Boolean fail,
                          HttpSession session) {
        User user = SessionUtil.checkUser(session);
        model.addAttribute("user", user);
        model.addAttribute("fail", fail != null);
        return "user/addUser";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user,
                               HttpServletRequest request) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            return "redirect:/users/formAddUser?fail=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", regUser.get());
        return "redirect:/tasks";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        HttpServletRequest request) {
        Optional<User> userDb = userService.findUserByLoginAndPassword(
                user.getLogin(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/users/loginPage?fail=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/tasks";
    }
}
