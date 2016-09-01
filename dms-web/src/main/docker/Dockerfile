


FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD dms-web.jar dms-web.jar
RUN sh -c 'touch /dms-web.jar'
ENTRYPOINT ["java", "-Denv=aws", "-jar", "dms-web.jar"]

