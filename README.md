# springTemplate
Spring Template
# prerequisite -> install gradle
to build run gradle build

# to build and test run
gradle clean test

# to build the project and run Spring Boot with Jetty
gradle build && java -jar build\libs\help-jar-1.0-SNAPSHOT.jar

# before improting project to Intelij Idea run
gradle idea

# build client side libs and then server side code
gradle clientRefresh
gradle clean build

# dev execution
#open two terminals:
#At the first terminal, start Gradle build as a continuous task:
gradle build --continuous
#At the second terminal, start the Gradle bootRun task:
gradle bootRun


# inspect last started docker container
docker ps -l -q
docker inspect 55056672b05b
