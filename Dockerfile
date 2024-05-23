FROM gradle:8.7.0-jdk17-alpine as cache
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY app/build.gradle /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle build -i --no-configuration-cache || return 0

FROM gradle:8.7.0-jdk17-alpine as runner
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY ./app /usr/src/java-code/
WORKDIR /usr/src/java-code
EXPOSE 8081
ENTRYPOINT ["gradle", "bootRun", "-i"]