package com.smang.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.UUID;

/**
 * @author smang
 * @Date 01/08/2017
 */
public class KafkaConsumerConnection {
    final static Logger logger = Logger.getLogger(KafkaConsumerConnection.class.getName());
    private static Consumer kafkaConsumer = null;

    private static void open() {
        logger.info("Ouverture de la connexion Kafka Consumer");

        // create instance for properties to access producer configs
        Properties props = new Properties();
        props.put("bootstrap.servers", "smangnn:9092,smangdn2:9092,smangdn3:9092");
        props.put("group.id", UUID.randomUUID().toString());
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");

        kafkaConsumer = new KafkaConsumer<String, String>(props);
    }

    public static void close() {
        logger.info("Fermeture de la connexion Kafka Consumer");
        kafkaConsumer.close();

    }

    public static Consumer getKafkaConsumer() {
        if (kafkaConsumer == null) {
            open();
        }
        return kafkaConsumer;
    }

}
