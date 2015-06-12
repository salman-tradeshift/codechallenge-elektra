FROM ubuntu:trusty
MAINTAINER Johannes Ulén (jul@tradeshift.com)

# VERSION
ENV JETTY_VERSION 9.2.11.v20150529
ENV JAVA_VERSION java8

# INSTALL
## Java
RUN apt-get install software-properties-common -y
RUN apt-add-repository ppa:webupd8team/java -y
RUN apt-get update
RUN echo oracle-${JAVA_VERSION}-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get update && apt-get install -y oracle-${JAVA_VERSION}-installer ca-certificates

## Jetty
RUN apt-get install wget -y
RUN wget -O /opt/jetty.tar.gz http://download.eclipse.org/jetty/${JETTY_VERSION}/dist/jetty-distribution-${JETTY_VERSION}.tar.gz
RUN tar -xvf /opt/jetty.tar.gz -C /opt/
RUN rm /opt/jetty.tar.gz
RUN mv /opt/jetty-distribution-${JETTY_VERSION} /opt/jetty
RUN useradd jetty -U -s /bin/false
RUN chown -R jetty:jetty /opt/jetty

# COPY
ADD messages.war /opt/jetty/webapps/messages.war
RUN chown -R jetty:jetty /opt/jetty/webapps/messages.war

# PORT
EXPOSE 8080

# LAUNCH
WORKDIR /opt/jetty
CMD ["java", "-jar", "start.jar", "jetty.home=/opt/jetty"]