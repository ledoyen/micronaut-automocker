package com.github.ledoyen.micronaut.automocker.sample.web;

import io.micronaut.runtime.Micronaut;

public class WebApplicationBootstrap {
    public static void main(String[] args) {
        Micronaut.run(WebApplicationBootstrap.class);
    }
}
