FROM tomcat:9.0-jdk17

# 파일 업로드 디렉토리 생성
RUN mkdir -p /var/www/html/uploads && \
    chmod 777 /var/www/html/uploads

COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]