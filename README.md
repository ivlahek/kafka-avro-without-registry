# kafka-avro-without-registry
Spring framework has great support for testing your Spring application with Apache Kafka. 
You add spring-kafka-dependency in your maven pom.xml file and you annotate your test class with @EmbbededKafka and Spring will do the rest.
Spring will start embedded Kafka on some random port and you are ready to test your application. 
It is not a real production-ready Kafka, but for testing, it is enough. 
You can test if your application is sending data to the correct topic. 
You can test whether your application has received data from the topic.

But if your application uses Apache Avro for serialization, and you do not use Confluent Schema Registry 
(which is the case when you want to use spring-kafka-test for testing) then you have a problem. 
There is no Serializer/Deserializer provided by the Spring framework for such a use case. 
Even if you just want to test your application, your application needs to have access to Confluent Schema Registry to work.

This is a test project in which is shown how to test such an application without the need of Confluent Schema Registry.
