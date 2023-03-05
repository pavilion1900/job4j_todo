package ru.job4j.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    void whenGetString() {
        assertThat(App.getString()).isEqualTo("Hello World!");
    }
}
