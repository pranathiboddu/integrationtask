
FROM openjdk:11-jre
ADD ./target/config-gateway-0.0.1-SNAPSHOT.jar /usr/src/config-gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/config-gateway.jar"]
