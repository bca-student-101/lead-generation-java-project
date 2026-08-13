# ऑफिशियल टॉमकैट इमेज
FROM tomcat:9.0-jdk11-openjdk-slim

# पुराने डिफॉल्ट ऐप्स को डिलीट करना
RUN rm -rf /usr/local/tomcat/webapps/*

# अपनी .war फाइल को सीधे ROOT.war बनाना
COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war



# सर्वर को रन करना
CMD ["catalina.sh", "run"]
