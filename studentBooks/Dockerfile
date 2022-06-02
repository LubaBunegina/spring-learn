FROM openjdk:11-jre-slim
COPY /target/books-0.0.1-SNAPSHOT.jar /app/app.jar
RUN ln -sf /dev/stdout /var/log/access.log \
	&& ln -sf /dev/stderr /var/log/error.log
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
