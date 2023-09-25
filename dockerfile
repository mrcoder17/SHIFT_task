FROM openjdk:17.0.1-jdk
COPY target/SHIFT_task-0.0.1-SNAPSHOT.jar /docker.jar
ENTRYPOINT ["java","-jar","/docker.jar"]
