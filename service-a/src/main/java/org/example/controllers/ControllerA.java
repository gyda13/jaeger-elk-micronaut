package org.example.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.SpanTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.ServiceBImpl;

import static org.example.filters.ControllerHttpFilter.X_CORRELATION_ID;


@Controller("/service-a")
@Slf4j
@RequiredArgsConstructor
@ExecuteOn(TaskExecutors.BLOCKING)
public class ControllerA {

    private final ServiceBImpl serviceB;

    @Get
    @ContinueSpan
    public String helloWithB(HttpRequest<?> request,
                             @SpanTag("serviceA.correlationId") @Header(X_CORRELATION_ID) String correlationId) {
        String b = serviceB.callServiceB(correlationId);
        return "Hello from A + " + b;
    }
}
