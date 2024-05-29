FROM eclipse-temurin:17-jdk
LABEL authors="yanyu."
ENV Version=0.0.1
ENV AppName=stock-schedules

ENV TZ=Asia/Taipei
WORKDIR app
ENV DB_URL=""
ENV DB_USER=""
ENV DB_PASSWORD=""
ENV STOCK_REQUEST_URL=""
ENV DOMAIN_NAME=""
ENV LANG C.UTF-8

COPY build/libs/stock-0.0.1-SNAPSHOT.jar stock-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java"]

CMD ["-jar", "stock-0.0.1-SNAPSHOT.jar"]