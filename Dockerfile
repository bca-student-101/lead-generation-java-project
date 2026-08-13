# ऑफिशियल टॉमकैट इमेज
FROM tomcat:9.0-jdk11-openjdk-slim

# पुराने डिफॉल्ट ऐप्स को डिलीट करना
RUN rm -rf /usr/local/tomcat/webapps/*

# अपनी .war फाइल को सीधे ROOT.war बनाना
COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war

# सबसे महत्वपूर्ण लाइन: टॉमकैट की अंदरूनी फाइल में पोर्ट को रेलवे के डायनामिक $PORT से बदलना
RUN sed -i 's/port="8080"/port="${PORT}"/g' /usr/local/tomcat/conf/server.xml

# सर्वर को रन करना
CMD ["catalina.sh", "run"]
