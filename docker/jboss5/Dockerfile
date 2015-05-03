FROM magex9/jdk7-oracle
MAINTAINER Scott Finlay <scott@magex.ca>

# Because you have to login to download this file, it is just added in rather then done
# via coammand, this should be updated to just do wget.
# 
ADD jboss-5.1.0.GA.zip ${WORKDIR}/jboss-5.1.0.GA.zip
RUN unzip jboss-5.1.0.GA.zip
RUN rm jboss-5.1.0.GA.zip

EXPOSE 8080
CMD ["/work/jboss-5.1.0.GA/bin/run.sh", "-b", "0.0.0.0", "default"]
