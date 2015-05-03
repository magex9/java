FROM magex9/jboss5
MAINTAINER Scott Finlay <scott@magex.ca>

RUN wget http://central.maven.org/maven2/com/icegreen/greenmail-webapp/1.4.1/greenmail-webapp-1.4.1.war
RUN mv greenmail-webapp-1.4.1.war ${WORKDIR}/jboss-5.1.0.GA/server/default/deploy/greenmail.war
