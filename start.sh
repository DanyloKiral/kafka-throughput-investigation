sbt assembly
docker-compose build
docker-compose up -d --scale processor-service=10
sleep 40s
docker exec kafka-broker kafka-topics --create --topic reddit-messages --zookeeper zookeeper:2181 --partitions 10 --replication-factor 1
echo "FINISHED"