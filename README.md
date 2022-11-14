# Sample of event-driven distributed application using Spring boot and Kafka

two spring boot applications `kafka-producer` and `kafka-consumer`

docker-compose spins up a zookeeper instance, a kafka-broker and a kafka-producer.

Separately spin up as many kafka-consumers as wanted, defining a custom topic, curl one of the HTTP endpoints on the kafka-producer, a message will be sent and depending on how the topic is configured one or many consumers will receive the message.
