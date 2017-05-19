FROM tomcat:8.5-jre8-alpine

WORKDIR /usr/local/tomcat
COPY api.war webapps/
#RUN ash -c 'pwd; ls webapps;'

EXPOSE 8080
CMD ["catalina.sh", "run"]