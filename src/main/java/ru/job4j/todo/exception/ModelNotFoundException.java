package ru.job4j.todo.exception;

import lombok.Getter;

@Getter
public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException(String message) {
        super(message);
    }
}
