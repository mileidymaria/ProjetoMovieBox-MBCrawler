FROM openjdk AS build
RUN mkdir -p /app/mbcrawl/crawler
COPY src /app/mbcrawl/crawler/src
COPY pom.xml /app/mbcrawl/crawler

RUN mvn -f /app/mbcrawl/crawler/pom.xml clean install

ARG war_FILE=/app/mbcrawl/crawler/target/*.war
COPY --from=build ${war_FILE} /app/mbcrawl/crawler.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/mbcrawl/crawler.war"]