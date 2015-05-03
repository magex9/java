FROM magex9/tomcat
MAINTAINER Scott Finlay <scott@magex.ca>

RUN rm -rf tomcat/webapps/*
RUN wget http://central.maven.org/maven2/com/icegreen/greenmail-webapp/1.4.1/greenmail-webapp-1.4.1.war
RUN mv -R greenmail-webapp-1.4.1.war ${WORKDIR}/tomcat/webapps/

CMD ["/start_tomcat.sh"]
