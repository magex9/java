FROM magex9/jdk7-oracle
MAINTAINER Scott Finlay <scott@magex.ca>

# Because you have to login to download this file, it is just added in rather then done
# via coammand, this should be updated to just do wget.
# 
ADD jboss-eap-6.4.0.zip ${WORKDIR}/jboss-eap-6.4.0.zip
RUN unzip jboss-eap-6.4.0.zip
RUN rm jboss-eap-6.4.0.zip

EXPOSE 8080
CMD ["/work/jboss-eap-6.4/bin/standalone.sh", "-b", "0.0.0.0"]
