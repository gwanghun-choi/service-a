#이미지를 어떻게 만들지”에 대한 레시피
#JAR 넣고, 포트 열고, java -jar로 실행하는 방법까지 정의

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
