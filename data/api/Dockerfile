FROM maven:3.8.4-openjdk-17-slim

WORKDIR /app

# 소스 코드 복사
COPY pom.xml .
COPY src ./src

# Maven으로 빌드
RUN mvn clean package -DskipTests

# 업로드 디렉토리 생성
RUN mkdir -p /tmp/uploads && \
    chmod 777 /tmp/uploads

# JAR 파일 실행
ENTRYPOINT ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"] 