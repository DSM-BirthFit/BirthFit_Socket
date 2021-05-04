FROM openjdk:11
COPY ./build/libs/*.jar birthfit_socket.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "-Duser.timezone=Asia/Seoul", "/birthfit_socket.jar"]
EXPOSE 3000
