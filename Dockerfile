FROM java:8-jre
ENV MYSQL_DATABASE=mydb
ENV MYSQL_USER = root_app
ENV MYSQL_PASSWORD=root123
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/mydb
ADD target/muzixapptask.jar muzixapptask.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","muzixapptask.jar"]
 

