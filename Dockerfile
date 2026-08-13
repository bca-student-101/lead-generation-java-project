FROM tomcat:9.0-jdk17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war

CMD ["sh", "-c", "sed -i 's/port=\"8080\"/port=\"'$PORT'\"/g' /usr/local/tomcat/conf/server.xml && catalina.sh run"]
