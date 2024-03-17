FROM eclipse-temurin:17-jdk as base

WORKDIR /workspace/app
VOLUME /tmp

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw dependency:resolve

FROM base as dev
CMD ["./mvnw", "spring-boot:run"]

# To add at the end of the presentation
FROM base as test
CMD ["./mvnw", "test"]

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
# Or use the final name from the build tag in the pom.xml --from=build /workspace/app/target/backend_final.jar
# See line 46 in the pom.xml
# Here the wildcard let us find the jar name easily
COPY --from=build /workspace/app/target/*.jar backend.jar
CMD ["java", "-jar", "/backend.jar"]