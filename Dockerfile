FROM tomcat:latest
ADD target/eventos.war /usr/local/tomcat/webapps/
EXPOSE 8081
CMD ["catalina.sh", "run"]