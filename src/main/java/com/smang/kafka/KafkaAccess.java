package com.smang.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * @author smang
  */
public class KafkaAccess {

 final static Logger logger = Logger.getLogger(KafkaAccess.class.getName());

    /**
     * This method Sends a String message  to the a kafka topic
     * @param topicName : Name of the topic to which the message has to be sent
     * @param idMsg: The ID of the current message (Kafka message key)
     * @param msg: The message to be sent
     */
    public static void sendMsgToKafka(String topicName, String idMsg, String msg)  {
        logger.info("Envoi des messsages vers Kafka topic "+ topicName);
            /*Send the full list to Kafka topic*/
        try {
            KafkaProducerConnection.getKafkaProducer().send(new ProducerRecord<String, String>(topicName,idMsg,msg)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void getMessagesFromKafka(String topicName){
        logger.info("Lecture des messages depuis le topic Kafka  "+ topicName);
        KafkaConsumerConnection.getKafkaConsumer().subscribe(Arrays.asList(topicName));

        while(true){
            ConsumerRecords<String,String> records = KafkaConsumerConnection.getKafkaConsumer().poll(1000);
            for(ConsumerRecord<String, String> record : records){

                System.out.println(String.format("Offset: %s Key: %s Value: %s", record.offset(),  record.key(), record.value()));

            }

        }

    }

}
