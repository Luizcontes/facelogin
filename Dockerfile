FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x /run.sh

CMD ["/run.sh"]