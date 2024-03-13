FROM openjdk:17

WORKDIR /usrapp/bin

ENV PORT 46001

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","org.eci.arep.aws.loadbalancer.Weblogroundrobin"]