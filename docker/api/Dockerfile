FROM openjdk:17-jdk-slim

WORKDIR /app

# 파일 업로드 디렉토리 생성
RUN mkdir -p /tmp/uploads && \
    chmod 777 /tmp/uploads

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"] 