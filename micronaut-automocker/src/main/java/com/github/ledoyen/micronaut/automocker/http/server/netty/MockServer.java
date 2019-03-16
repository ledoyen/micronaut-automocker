package com.github.ledoyen.micronaut.automocker.http.server.netty;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.ledoyen.micronaut.automocker.Automocker;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.exceptions.ConfigurationException;
import io.micronaut.http.server.netty.NettyHttpServer;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.runtime.server.EmbeddedServer;

/**
 * Stub for {@link NettyHttpServer} to avoid listening on a port and exposing HTTP services.
 *
 * @see MockMvc
 * @see Automocker
 */
@Singleton
@Replaces(NettyHttpServer.class)
public class MockServer implements EmbeddedServer {

    private final String scheme = "http";
    private final String host = "test";
    private final int port = -1;
    private final ApplicationContext applicationContext;

    @Inject
    public MockServer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        System.out.println("Mocking NettyHttpServer");
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getScheme() {
        return scheme;
    }

    @Override
    public URL getURL() {
        try {
            return new URL(getScheme() + "://" + getHost() + ':' + getPort());
        } catch (MalformedURLException e) {
            throw new ConfigurationException("Invalid server URL: " + e.getMessage(), e);
        }
    }

    @Override
    public URI getURI() {
        try {
            return new URI(getScheme() + "://" + getHost() + ':' + getPort());
        } catch (URISyntaxException e) {
            throw new ConfigurationException("Invalid server URL: " + e.getMessage(), e);
        }
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public ApplicationConfiguration getApplicationConfiguration() {
        return null;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
