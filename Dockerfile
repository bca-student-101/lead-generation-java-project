# Step 1: Tomcat Runner डाउनलोड करना
FROM alpine:latest AS downloader
RUN apk add --no-cache curl
WORKDIR /app
RUN curl -fSL https://maven.org -o webapp-runner.jar

# Step 2: Java एनवायरनमेंट (openjdk की जगह नया eclipse-temurin)
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

# डाउनलोडर से रनर जार को कॉपी करना
COPY --from=downloader /app/webapp-runner.jar .

# अपनी .war फाइल को रूट में कॉपी करना
COPY ./LeadGenAdmin.war ./webapps/ROOT.war

# रेलवे का डायनामिक पोर्ट एक्सपोज करना
EXPOSE 8080

# सर्वर रन करने की कमांड
CMD ["java", "-jar", "webapp-runner.jar", "--port", "8080", "./webapps/ROOT.war"]
