FROM gradle:7.6-jdk17

WORKDIR /hospital-management-platform
#COPY . .
COPY build/libs/hospital-management-platform-*.jar app.jar
RUN ls
#RUN gradle bootRun

EXPOSE 8080
ENTRYPOINT ["java","-jar","/hospital-management-platform-0.0.1-SNAPSHOT.jar"]