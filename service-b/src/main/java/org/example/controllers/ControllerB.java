package org.example.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.SpanTag;
import lombok.extern.slf4j.Slf4j;

import static org.example.filters.ControllerHttpFilter.X_CORRELATION_ID;


@Controller("/service-b")
@Slf4j
public class ControllerB {

    @Get
    @ContinueSpan
    public String hello(HttpRequest request,
                        @SpanTag("serviceB.correlationId") @Header(X_CORRELATION_ID) String correlationId) {
        log.info("jaeger-trace-id: {}", request.getHeaders().get("uber-trace-id"));
        return "Hello from B";
    }

}
