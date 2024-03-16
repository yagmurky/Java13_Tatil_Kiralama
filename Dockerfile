FROM amazoncorretto:21.0.2-alpine3.19
COPY build/libs/Java13_Tatil_Kiralama-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]