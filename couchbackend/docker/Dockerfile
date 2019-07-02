
FROM maven:3.5-jdk-8

WORKDIR /usr/src/app

COPY ./pom.xml /usr/src/app/pom.xml
RUN  mvn dependency:go-offline

COPY ./src /usr/src/app/src

RUN  mkdir /usr/src/app/logs \
   && mvn clean package -DskipTests=true \
   && ls -la /usr/src/app/target \
   && cp /usr/src/app/target/couch-backend-0.0.1-SNAPSHOT.jar /usr/src/app/couch-backend-0.0.1-SNAPSHOT.jar

COPY ./docker/run.sh /usr/src/app/run.sh
RUN chmod +x /usr/src/app/run.sh

EXPOSE 8096

CMD ["bash", "/usr/src/app/run.sh"]