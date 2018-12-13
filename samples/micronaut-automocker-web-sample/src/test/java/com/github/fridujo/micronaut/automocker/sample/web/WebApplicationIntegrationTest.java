package com.github.fridujo.micronaut.automocker.sample.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

@MicronautTest
public class WebApplicationIntegrationTest {

    @Inject
    HelloClient helloClient;

    @Test
    void testHello() {
        assertEquals(
            "Hello Fred!",
            helloClient.hello("Fred").blockingGet());
    }
}
