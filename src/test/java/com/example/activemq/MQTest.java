package com.example.activemq;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

@Testcontainers
class MQTest {

    @Container
    public GenericContainer<?> activeMQContainer = new GenericContainer<>(DockerImageName.parse("rmohr/activemq"))
                    .withExposedPorts(61616);
    
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;

    @BeforeEach
    void setup() throws JMSException {
        String brokerUrl = "tcp://localhost:" + this.activeMQContainer.getMappedPort(61616);
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        this.connection = connectionFactory.createConnection();
        this.connection.start();

        // Creating session for sending messages
        this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Getting the queue
        Queue queue = this.session.createQueue("test.queue");
        
        // Creating the producer & consumer
        this.producer = this.session.createProducer(queue);
        this.consumer = this.session.createConsumer(queue);
    }

    @AfterEach
    public void tearDown() throws JMSException {
        // Cleaning up resources
        if (this.producer != null) this.producer.close();
        if (this.consumer != null) this.consumer.close();
        if (this.session != null) this.session.close();
        if (this.connection != null) this.connection.close();
    }

    @Test
    public void testSendMessage() throws JMSException {
        String dummyPayload = "Dummy payload";

        // Sending a text message to the queue
        TextMessage message = this.session.createTextMessage(dummyPayload);
        this.producer.send(message);

        // Receiving the message from the queue
        TextMessage receivedMessage = (TextMessage) this.consumer.receive(5000);

        assertThat(dummyPayload).isEqualTo(receivedMessage.getText());
    }
}

/*@SpringBootTest
@Testcontainers
public class JmsTest {

	@Container
    static GenericContainer<?> artemis = new GenericContainer<>(DockerImageName.parse("apache/activemq-artemis:latest-alpine"))
            .withEnv("ANONYMOUS_LOGIN", "true")
            .withExposedPorts(61616);

    @DynamicPropertySource
    static void artemisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.artemis.broker-url", () -> "tcp://%s:%d".formatted(artemis.getHost(), artemis.getMappedPort(61616)));
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    void sendMessage() throws JMSException {
        this.jmsTemplate.convertAndSend("test.queue", "Hello, JMS!");

        Message message = this.jmsTemplate.receive("testQueue");
        assertThat(message).isInstanceOf(TextMessage.class);
        
        TextMessage textMessage = (TextMessage) message;
        assertThat(textMessage.getText()).isEqualTo("Hello, JMS!");
    }

}
*/