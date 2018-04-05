FROM java:8

WORKDIR /app/

#add pom file
ADD pom.xml /app/pom.xml
ADD . /app

# get all the downloads out of the way
RUN ./mvnw verify clean -Dmaven.test.skip=true --fail-never

RUN ./mvnw verify -Dmaven.test.skip=true

EXPOSE 8080

CMD ["java", "-jar", "payments.jar"]
