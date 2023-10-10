#Stage 1 : Run Maven, build the fat JAR, and unpack it
FROM huangzp88/maven-openjdk17 as build 
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

#Additional step to cache the dependencies unless
# the POM file changes
RUN mvn dependency:go-offline -B

# copy the project source
COPY src src

#Package the app
RUN mvn install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#Stage 2: Running the app
FROM eclipse-temurin:17-jre-alpine

# create user to run app
RUN addgroup -S kombe && adduser -S kombe -G kombe

# Use user
USER kombe

# Copy dependencies from the build stage
# Ref :https://spring.io/guides/topicals/spring-boot-docker/
# Ref : https://www.youtube.com/watch?v=4ugChIR9sS8&t=616s

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.kbf.Api.ApiApplication"]
