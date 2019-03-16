package com.github.ledoyen.micronaut.automocker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.MicronautTest;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

@MicronautTest
@Property(name = "automocker.enabled", value = "true")
public @interface Automocker {
}
