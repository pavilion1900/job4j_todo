package ru.job4j.todo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.job4j.todo.model.User;

import javax.servlet.http.HttpSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionUtil {

    public static User checkUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = User.builder()
                    .login("Гость")
                    .build();
        }
        return user;
    }
}
