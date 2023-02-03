FROM eclipse-temurin:17-jdk-focal
ADD target/banco-futuro.jar banco-futuro.jar
ENTRYPOINT ["java", "-jar","banco-futuro.jar"]
EXPOSE 8080:8080 

