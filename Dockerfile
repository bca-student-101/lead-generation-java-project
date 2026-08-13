# ऑफिशियल और रेडीमेड टॉमकैट इमेज का इस्तेमाल करें
FROM tomcat:9.0-jre11-alpine

# टॉमकैट के पुराने डिफॉल्ट ऐप्स को डिलीट करें
RUN rm -rf /usr/local/tomcat/webapps/*

# अपनी .war फाइल को सीधे ROOT.war बनाकर सही जगह पर कॉपी करें
COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war

# रेलवे के डायनामिक पोर्ट को बाइंड करने के लिए सबसे ज़रूरी सेटिंग
ENV PORT=8080
EXPOSE 8080

# टॉमकैट को बिना किसी झंझट के डायरेक्ट रन करें
CMD ["catalina.sh", "run"]
