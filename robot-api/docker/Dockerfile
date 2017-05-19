FROM tomcat:8.5-jre8-alpine

WORKDIR /usr/local/tomcat
# must be only one war under docker/
COPY *api*.war webapps/api.war
RUN ash -c 'pwd; ls webapps;'

EXPOSE 8080
CMD ["catalina.sh", "run"]