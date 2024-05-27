FROM gradle:8.7.0-jdk17-alpine as cache
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY ./build.gradle /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle clean build -i --no-configuration-cache

FROM gradle:8.7.0-jdk17-alpine as runner
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY . /usr/src/java-code/
WORKDIR /usr/src/java-code
ENTRYPOINT ["gradle", "test", "-i"]