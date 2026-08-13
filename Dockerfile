# Official Tomcat image with Java 17
FROM tomcat:9.0-jdk17-temurin

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file as ROOT.war
COPY ./LeadGenAdmin.war /usr/local/tomcat/webapps/ROOT.war

# Use Railway's dynamic PORT
RUN sed -i 's/port="8080"/port="${PORT}"/g' /usr/local/tomcat/conf/server.xml

# Start Tomcat
CMD ["catalina.sh", "run"]
