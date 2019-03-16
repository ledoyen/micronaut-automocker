@Configuration
@Requires(property = "automocker.enabled")
@Requires(classes = NettyHttpServer.class)
package com.github.fridujo.micronaut.automocker.http.server.netty;

import io.micronaut.context.annotation.Configuration;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.server.netty.NettyHttpServer;
