package org.example.clients;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static org.example.filters.ControllerHttpFilter.X_CORRELATION_ID;

@Client(value = "http://localhost:8081")
public interface ServiceB {

    @Get(value = "/service-b", produces = APPLICATION_JSON)
    HttpResponse<String> callB(@Header(X_CORRELATION_ID) String correlationId);

}
