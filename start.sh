zstd -d reddit_subreddits.ndjson.zst -o data/reddit_subreddits.ndjson
echo "DATA DECOMPRESSED"
docker-compose up -d
sleep 25s
docker exec kafka-broker kafka-topics --create --topic reddit-message --zookeeper zookeeper:2181 --partitions 1 --replication-factor 1
echo "FINISHED"