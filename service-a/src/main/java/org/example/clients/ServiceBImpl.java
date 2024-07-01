package org.example.clients;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class ServiceBImpl {

    private final ServiceB serviceB;

    public String callServiceB(String correlationId) {
        HttpResponse<String> response;

        try {
            response = serviceB.callB(correlationId);

            if (response.getBody().isEmpty()) {
                log.info("Body is empty");
            }
            return response.getBody().get();

        } catch (Exception exception) {
           throw new RuntimeException(exception);
        }

    }
}
