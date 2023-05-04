FROM openjdk:11-jdk

WORKDIR /app

ADD build/libs/sure-market-0.0.1-SNAPSHOT.jar /app/app.jar


ENTRYPOINT ["java","-jar","/app/app.jar"]

# https://do-study.tistory.com/m/126java