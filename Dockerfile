FROM tomcat:9.0-jdk11-openjdk-slim

# पुरानी डिफॉल्ट एप्लिकेशन्स को हटाना ताकि कोई कॉन्फ्लिक्ट न हो
RUN rm -rf /usr/local/tomcat/webapps/*

# अपनी लीड जेन .war फाइल को सीधे ROOT.war की तरह डालना
COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war

# रेलवे के डायनामिक पोर्ट को सुनना (यह सबसे महत्वपूर्ण है)
ENV PORT=8080
EXPOSE 8080

# टॉमकैट को चालू करने की सही कमांड
CMD ["catalina.sh", "run"]
