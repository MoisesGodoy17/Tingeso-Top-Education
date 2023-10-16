FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} top_education-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/top_education-0.0.1-SNAPSHOT.jar"]