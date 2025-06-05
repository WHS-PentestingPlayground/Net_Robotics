FROM oracleinanutshell/oracle-xe-11g

ENV ORACLE_ALLOW_REMOTE=true

COPY int.sql /docker-entrypoint-initdb.d/

EXPOSE 1521
EXPOSE 8080

CMD ["/bin/bash", "-c", "/etc/init.d/oracle-xe start && tail -f /dev/null"] 