package com.github.fridujo.micronaut.automocker.sample.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import io.micronaut.http.HttpMethod;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import com.github.fridujo.micronaut.automocker.Automocker;
import com.github.fridujo.micronaut.automocker.http.server.netty.MockMvc;
import com.github.fridujo.micronaut.automocker.http.server.netty.ReadableHttpResponse;

@Automocker
public class WebApplicationIntegrationTest {

    @Inject
    MockMvc mockMvc;

    @Test
    void testHello() {
        ReadableHttpResponse<?> response = mockMvc.exchange(HttpMethod.GET, "/hello/Fred");
        assertEquals("Hello Fred!", response.bodyAsString());
    }
}
