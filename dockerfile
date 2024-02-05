FROM maven:3.6.0-jdk-12-alpine
ADD . .
COPY entrypoint.sh /
WORKDIR /
RUN ls -ltr
RUN chmod +x entrypoint.sh
ENTRYPOINT ["/bin/bash", "/entrypoint.sh"]
RUN echo $?