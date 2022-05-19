# Docker compose kafka streams app

## Useful commands

sbt clean docker  -build image

docker compose up  -run image with docker compose

docker compose ps  -list processes

docker compose exec kafka kafka-topics --create --topic in --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1

docker compose exec kafka kafka-console-producer --topic in --broker-list kafka:9092

docker compose exec kafka kafka-console-consumer --topic out --bootstrap-server kafka:9092

docker compose rm   -kill processes