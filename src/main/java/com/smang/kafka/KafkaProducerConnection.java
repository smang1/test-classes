package com.smang.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * @author smang
 * @Date 26/06/2017
 */
public class KafkaProducerConnection {

    final static Logger logger = Logger.getLogger(KafkaProducerConnection.class.getName());
    private static Producer kafkaProducer = null;

    private static void open(){
        logger.info("Ouverture de la connexion Kafka Producer");

        // create instance for properties to access producer configs
        Properties props = new Properties();
        props.put("bootstrap.servers", "smangcnn:9092,smangdn2:9092,smangdn3:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("max.request.size", 15728640);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
    }

    public static void close(){
        logger.info("Fermeture de la connexion Kafka Producer");
        kafkaProducer.close();

    }

    public static Producer getKafkaProducer() {
        if (kafkaProducer == null) {
            open();
        }
        return kafkaProducer;
    }

    public static void send(String key, String message, String topic) {
        logger.debug("Envoi d'un message sur le topic " + topic + " (" + message + ")");
        getKafkaProducer().send(new ProducerRecord<>(topic, key, message));
    }


}
