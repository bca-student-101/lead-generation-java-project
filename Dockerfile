
# Step 1: Webapp Runner जार फाइल डाउनलोड करना
FROM alpine:latest AS downloader
RUN apk add --no-cache curl
WORKDIR /app
RUN curl -fSL https://maven.org -o webapp-runner.jar

# Step 2: Java एनवायरनमेंट सेट करना
FROM eclipse-temurin:11-jre
WORKDIR /app

# डाउनलोडर से रनर को कॉपी करना
COPY --from=downloader /app/webapp-runner.jar .

# अपनी .war फाइल को सीधे ROOT.war बनाना
COPY ./LeadGenAdmin.war ./ROOT.war

# रेलवे के डायनामिक पोर्ट को बाइंड करने की सही शेल कमांड (बिना ब्रैकेट के)
CMD java -jar webapp-runner.jar --port $PORT ./ROOT.war
