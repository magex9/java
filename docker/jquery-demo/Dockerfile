FROM magex9/tomcat
MAINTAINER Scott Finlay <scott@magex.ca>

RUN rm -rf tomcat/webapps/*
RUN wget https://jqueryui.com/resources/download/jquery-ui-1.11.4.zip
RUN unzip jquery-ui-1.11.4.zip
RUN cp -R jquery-ui-1.11.4 ${WORKDIR}/tomcat/webapps/jquery
RUN rm -rf jquery-ui-1.11.4*

CMD ["/start_tomcat.sh"]
