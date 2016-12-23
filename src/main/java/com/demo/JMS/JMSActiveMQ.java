package com.demo.JMS;

import com.demo.data.Spittle;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by kingwu on 22/12/2016.
 */
@Component
public class JMSActiveMQ {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private JmsOperations jmsOperations;

    public void sendActivemqMessqge(){
        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue("spitter.queue");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText("hello world");
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            closeAll(session, connection);

        }
    }

    public void receiveActivemqMessage() {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue("spitter.queue");
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive();
            System.out.println("Got a message: " + ((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {

            closeAll(session, connection);
        }
    }

    private void closeAll(Session session, Connection connection){
        try {
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public void sendJmsTmpMessage(Spittle spittle){
//        jmsOperations.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage();
//            }
//        });
        jmsOperations.convertAndSend(spittle);

    }

    public void receiveJmsTmpMessage(){
       // Spittle spittle = (Spittle) jmsOperations.receiveAndConvert();
       // System.out.println("receive jmsTemplate message: " + spittle.getMessage());
        try {
            ObjectMessage receiveMsg = (org.apache.activemq.command.ActiveMQObjectMessage)jmsOperations.receive();
            Spittle spittle = (Spittle) receiveMsg.getObject();
            System.out.println("receive jmsTemplate message: " + spittle.getMessage());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
