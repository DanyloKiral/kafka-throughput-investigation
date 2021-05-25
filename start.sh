docker-compose up -d
sleep 25s
docker exec kafka-broker kafka-topics --create --topic reddit-messages --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1
echo "FINISHED"