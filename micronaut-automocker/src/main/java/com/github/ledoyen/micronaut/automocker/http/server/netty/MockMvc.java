package com.github.ledoyen.micronaut.automocker.http.server.netty;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.ledoyen.micronaut.automocker.Automocker;
import io.micronaut.core.annotation.AnnotationMetadata;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.netty.NettyMutableHttpResponse;
import io.micronaut.http.server.exceptions.InternalServerException;
import io.micronaut.web.router.RouteMatch;
import io.micronaut.web.router.Router;
import io.micronaut.web.router.UriRouteMatch;

/**
 * API from where to call HTTP services as if they were exposed.
 *
 * @see MockServer
 * @see Automocker
 */
@Singleton
public class MockMvc {

    private final Router router;

    @Inject
    public MockMvc(Router router) {
        this.router = router;
    }

    public ReadableHttpResponse<?> exchange(HttpMethod method, String uri) {
        Optional<UriRouteMatch<Object, Object>> route = router.route(method, uri);
        final MutableHttpResponse<?> response;
        if (route.isPresent() && route.get().isExecutable()) {
            RouteMatch<Object> routeMatch = route.get();
            Object result = routeMatch.execute();
            response = messageToResponse(routeMatch, result);
        } else {
            response = newNotFoundError();
        }
        return new ReadableHttpResponse<>(response);
    }

    private MutableHttpResponse<?> messageToResponse(RouteMatch<Object> routeMatch, Object message) {
        final MutableHttpResponse<?> response;
        if (message instanceof HttpResponse) {
            response = ConversionService.SHARED.convert(message, NettyMutableHttpResponse.class)
                .orElseThrow(() -> new InternalServerException("Emitted response is not mutable"));
        } else {
            if (message instanceof HttpStatus) {
                response = HttpResponse.status((HttpStatus) message);
            } else {
                response = forStatus(routeMatch.getAnnotationMetadata()).body(message);
            }
        }
        return response;
    }

    private MutableHttpResponse<Object> forStatus(AnnotationMetadata annotationMetadata) {
        HttpStatus status = HttpStatus.OK;

        if (annotationMetadata.hasAnnotation(Status.class)) {
            status = annotationMetadata.getValue(Status.class, HttpStatus.class).orElse(status);
        }

        return HttpResponse.status(status);
    }

    private MutableHttpResponse<?> newNotFoundError() {
        JsonError jsonError = new JsonError("Page Not Found");
        return HttpResponse.notFound()
            .body(jsonError);
    }
}
