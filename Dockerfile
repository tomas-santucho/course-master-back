FROM amazoncorretto:21

WORKDIR /app

COPY build/libs/CourseMaster-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "CourseMaster-0.0.1-SNAPSHOT.jar"]