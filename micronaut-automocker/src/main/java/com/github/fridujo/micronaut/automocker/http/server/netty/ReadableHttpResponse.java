package com.github.fridujo.micronaut.automocker.http.server.netty;

import java.util.Optional;

import io.micronaut.core.convert.value.MutableConvertibleValues;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.reactivex.Single;

public class ReadableHttpResponse<T> implements HttpResponse<T> {

    private final HttpResponse<T> delegate;

    public ReadableHttpResponse(HttpResponse<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public HttpStatus getStatus() {
        return delegate.getStatus();
    }

    @Override
    public HttpHeaders getHeaders() {
        return delegate.getHeaders();
    }

    @Override
    public MutableConvertibleValues<Object> getAttributes() {
        return delegate.getAttributes();
    }

    @Override
    public Optional<T> getBody() {
        return delegate.getBody();
    }

    public String bodyAsString() {
        return getBody().map(this::toBlocking).map(Object::toString).orElse(null);
    }

    private Object toBlocking(Object o) {
        if (o instanceof Single) {
            return ((Single<?>) o).blockingGet();
        } else {
            return o;
        }
    }
}
