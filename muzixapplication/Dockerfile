FROM openjdk:11-jre
WORKDIR /usr/src
ADD ./target/muzixapplication-0.0.1-SNAPSHOT.war /usr/src/muzixapplication-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/muzixapplication-0.0.1-SNAPSHOT.war"]



