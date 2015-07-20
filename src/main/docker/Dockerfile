FROM ubuntu:trusty
FROM java:7

MAINTAINER sal@tradeshift.com

RUN apt-get update
RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*

# Install Jetty
RUN wget -O /opt/jetty.tar.gz "http://eclipse.org/downloads/download.php?file=/jetty/9.2.12.v20150709/dist/jetty-distribution-9.2.12.v20150709.tar.gz&r=1"
RUN tar -xvf /opt/jetty.tar.gz -C /opt/
RUN rm /opt/jetty.tar.gz
RUN mv /opt/jetty-distribution-9.2.12.v20150709 /opt/jetty
RUN rm -rf /opt/jetty/webapps.demo
RUN useradd jetty -U -s /bin/false
RUN chown -R jetty:jetty /opt/jetty

ADD tradeshift-api.war /opt/jetty/webapps/tradeshift-api.war

ENV JETTY_BASE /opt/jetty
WORKDIR $JETTY_BASE
EXPOSE 8080

CMD ["java", "-jar", "start.jar"]