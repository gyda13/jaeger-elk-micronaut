# jaeger-micronaut


This repository contains two microservices: Service A and Service B. Service A communicates with Service B to handle specific operations. I used Jaeger for distributed tracing to monitor and analyze the interactions between these services.


## Run Jaeger in Docker
To run Jaeger in a Docker container, use the following command:

```
docker run -d --name jaeger \
  -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 14250:14250 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.30
```


<img width="1728" alt="Screenshot 1445-12-25 at 2 32 19 PM" src="https://github.com/gyda13/jaeger-micronaut/assets/90142160/7ac2f47e-746e-4f38-aab3-6e889fc6032d">
