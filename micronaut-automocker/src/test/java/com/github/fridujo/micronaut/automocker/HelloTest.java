package com.github.fridujo.micronaut.automocker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloTest {

    @Test
    void obvious() {
        assertThat(new Hello().get()).contains("world");
    }
}
