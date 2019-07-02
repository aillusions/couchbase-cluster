#!/bin/bash

cd /usr/src/app

echo "======================================================================================"
echo "======================================================================================"

java -Xms650m -Xmx650m -XX:MaxMetaspaceSize=128m \
    -server \
    -XX:+UseParallelGC \
    -Djava.endorsed.dirs=/usr/local/tomcat7/endorsed \
    -Djava.io.tmpdir=/usr/src/app \
    -Djavax.servlet.request.encoding=UTF-8 \
    -XX:+PrintGCDetails \
    -XX:+PrintTenuringDistribution \
    -XX:+PrintGCApplicationStoppedTime \
    -XX:+PrintGCDateStamps \
    -XX:+PrintGCTimeStamps \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/usr/src/app \
    -Xloggc:/usr/src/app/gc.log \
    -Dfile.encoding=UTF-8 \
    -jar couch-backend-0.0.1-SNAPSHOT.jar