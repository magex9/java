FROM magex9/tomcat
MAINTAINER Scott Finlay <scott@magex.ca>

RUN rm -rf tomcat/webapps/*
RUN wget -q https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR_VERSION}/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz
RUN tar xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz
RUN cp -R apache-tomcat-${TOMCAT_VERSION}/webapps/examples ${WORKDIR}/tomcat/webapps/tomcat
RUN rm -rf apache-tomcat-${TOMCAT_VERSION}*

CMD ["/start_tomcat.sh"]
