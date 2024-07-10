package org.example.filters;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.slf4j.MDC;

import java.util.UUID;

@Filter("/**")
@Slf4j
public class ControllerHttpFilter implements HttpServerFilter {

    public static final String X_CORRELATION_ID = "X-Correlation-ID";

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        String correlationId = request.getHeaders().contains(X_CORRELATION_ID) ? request.getHeaders()
                .get(X_CORRELATION_ID) : UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);

        MutableHttpRequest<?> mutableRequest = request.mutate();


        if (request.getHeaders().get(X_CORRELATION_ID) == null) {
            log.info("Adding X-Correlation-ID header to request {}", correlationId);
            mutableRequest.getHeaders().add(X_CORRELATION_ID, correlationId);
        } else {
            log.info("X-Correlation-ID already present in request header: {}", correlationId);
            mutableRequest.getHeaders().add(X_CORRELATION_ID, correlationId);
        }

        return chain.proceed(mutableRequest);
    }

}