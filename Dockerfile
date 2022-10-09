FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

COPY run.sh .

RUN chmod +x run.sh

CMD ["run.sh"]