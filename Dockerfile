FROM gradle:jdk11 as builder
COPY --chown=gradle:gradle . /home/application
WORKDIR /home/application
RUN ./gradlew build --no-daemon
FROM amazonlinux:2.0.20210219.0 as graalvm

ENV LANG=en_US.UTF-8

RUN yum install -y gcc gcc-c++ libc6-dev  zlib1g-dev curl bash zlib zlib-devel zip
RUN     yum -y update && \
    yum -y install wget && \
    yum install -y tar.x86_64 && \
    yum clean all
ENV GRAAL_VERSION 21.0.0.2
ENV JDK_VERSION java11
ENV GRAAL_FILENAME graalvm-ce-java11-darwin-amd64-21.0.0.2.tar.gz

RUN curl -4 -L https://github.com/graalvm/graalvm-ce-builds/releases/${GRAAL_FILENAME} -o /tmp/${GRAAL_FILENAME}

RUN tar -zxvf /tmp/${GRAAL_FILENAME} -C /tmp \
    && mv /tmp/graalvm-ce-${JDK_VERSION}-${GRAAL_VERSION} /usr/lib/graalvm

RUN rm -rf /tmp/*
CMD ["/usr/lib/graalvm/bin/native-image"]

FROM graalvm
COPY --from=builder /home/application/ /home/application/
WORKDIR /home/application
RUN /usr/lib/graalvm/bin/gu install native-image
RUN /usr/lib/graalvm/bin/native-image --no-server -cp build/libs/studentapp-*-all.jar
RUN chmod 777 bootstrap
RUN chmod 777 studentapp
RUN zip -j function.zip bootstrap studentapp
EXPOSE 8080
ENTRYPOINT ["/home/application/studentapp"]