FROM	ubuntu:trusty
RUN	apt-get update
RUN	apt-get install -y wget
RUN	apt-get install -y openjdk-7-jdk
RUN	wget -O jetty.tar.gz http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-9.2.11.v20150529.tar.gz
RUN 	tar -xvzf jetty.tar.gz
EXPOSE	8080
WORKDIR	jetty-distribution-9.2.11.v20150529
COPY	target/root.war webapps/
CMD	java -jar start.jar
