FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD main-project-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
