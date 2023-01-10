#Pull base image
#-----------------
FROM azul/zulu-openjdk:17

#Author
#-------
LABEL org.opencontainers.image.authors="Jennifer Reif,jennifer@thehecklers.org,@JMHReif"

#Copy jar and expose entrypoints
#--------------------------------
COPY target/backend-*.jar backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]