FROM openjdk:8-jre-slim
COPY ./build/libs/*.jar birthfit_socket.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "-Duser.timezone=Asia/Seoul", "/birthfit_socket.jar"]
EXPOSE 3000
