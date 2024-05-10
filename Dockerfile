#
# Build stage
#
FROM maven:3.8.1-openjdk-17-slim AS build
ARG MONGODB_URI
ENV MONGODB_URI ${MONGODB_URI}
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
ENV PORT ${PORT}
ENV MONGODB_URI ${MONGODB_URI}
ENV CALENDAR_PRIVATE_KEY_ID ${CALENDAR_PRIVATE_KEY_ID}
ENV CALENDAR_PRIVATE_KEY ${CALENDAR_PRIVATE_KEY}
ENV CALENDAR_ID ${CALENDAR_ID}
COPY --from=build /home/app/target/eventos.jar /usr/local/lib/eventos.jar
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/usr/local/lib/eventos.jar"]