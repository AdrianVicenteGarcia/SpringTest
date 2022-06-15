FROM openjdk:11 AS builder
WORKDIR /springtest
COPY . ./
RUN ./gradlew test assemble

FROM openjdk:11-jre-slim-buster AS runtime
WORKDIR /opt/springboot
COPY --from=builder /springtest/build/libs/rest-service-0.0.1-SNAPSHOT.jar ./
CMD java -jar rest-service-0.0.1-SNAPSHOT.jar