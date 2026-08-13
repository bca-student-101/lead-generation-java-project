FROM alpine:latest AS downloader
RUN apk add --no-cache curl
WORKDIR /app
RUN curl -fSL https://maven.org -o webapp-runner.jar

FROM openjdk:11-slim
WORKDIR /app
COPY --from=downloader /app/webapp-runner.jar .
COPY ./LeadGenAdmin.war ./webapps/ROOT.war
EXPOSE 8080
CMD ["java", "-jar", "webapp-runner.jar", "--port", "8080", "./webapps/ROOT.war"]
