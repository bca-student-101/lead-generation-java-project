# ऑफिशियल टॉमकैट इमेज जो रेलवे पर 100% काम करती है
FROM tomcat:9.0-jdk11-openjdk-slim

# पुरानी डिफॉल्ट एप्लिकेशन्स को डिलीट करना
RUN rm -rf /usr/local/tomcat/webapps/*

# अपनी .war फाइल को सीधे ROOT.war बनाकर सही जगह पर कॉपी करना
COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war

# टॉमकैट का डिफॉल्ट पोर्ट एक्सपोज करना
EXPOSE 8080

CMD ["catalina.sh", "run"]
