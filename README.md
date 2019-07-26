# Kafka REST connector demo

Demo that uses [kafka-connect-rest](https://github.com/llofberg/kafka-connect-rest) to use a REST API as data source and publish the data received to kafka topic.

## Prerequesits

1. build and run the mock API
```$shell

cd silly-transaction-api
mvn clean package
java -jar target/transaction-api-0.0.1-SNAPSHOT.jar

# test API
curl http://localhost:8080/transactions

```

2. Prepare Confluent Kafka
 Download [Confluent community edition 5.2.x](http://packages.confluent.io/archive/5.2/confluent-community-5.2.2-2.11.tar.gz), then untar the installation and startup the platform

```$shell
cd <CONFLUENT_HOME_DIRECTORY>
bin/confluent start connect

# create new topic for testing
bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

# start a console consumer
bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning

# start a console producer
bin/kafka-console-producer --broker-list localhost:9092 --topic test

# enter whatever text and press enter, consumer should recieve the message and show the same output, 
# press ctrl-D and enter and exit console prodcuer

# show connect log messages
bin/confluent log connect

```

### Compile and install kafka-connect-rest 

```$shell
git clone https://github.com/llofberg/kafka-connect-rest
cd kafka-connect-rest
mvn clean package

# copy the connector JAR files into Confluent  Kafka  installation directory
mkdir <CONFLUENT_HOME_DIRECTORY>/etc/java/kafka-connect-rest

find . -name "*shaeded.jar" -exec cp {} <CONFLUENT_HOME_DIRECTORY>/etc/java/kafka-connect-rest/.

```
 
### create a connector via Kafka Connect REST API
```$shell

curl -v -H "Content-Type: application/json" -d @config/source.json http://localhost:8083/connectors

# the API should return status 201 if the command is  successful, the connector should start pulling data from REST API immediately. check connect log output in case something goes wrong.

```




