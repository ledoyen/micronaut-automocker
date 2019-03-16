package com.github.ledoyen.micronaut.automocker.http.server.netty;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.web.router.Router;
import org.junit.jupiter.api.Test;

class MockMvcTest {

    private final Router router = mock(Router.class);
    private final MockMvc mockMvc = new MockMvc(router);

    @Test
    void no_route_returns_404() {
        when(router.route(any(), anyString())).thenReturn(empty());

        ReadableHttpResponse<?> response = mockMvc.exchange(HttpMethod.GET, "/");

        assertThat((Object) response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
