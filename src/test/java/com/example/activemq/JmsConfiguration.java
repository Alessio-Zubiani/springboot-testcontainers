package com.example.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.testcontainers.containers.GenericContainer;

import jakarta.jms.JMSException;

@TestConfiguration
public class JmsConfiguration {
	
	private static final GenericContainer<?> activeMQContainer;

	static {
		activeMQContainer = new GenericContainer<>("rmohr/activemq:latest").withExposedPorts(61616);
		activeMQContainer.start();
	}
	
	@Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("vm://localhost:" + activeMQContainer.getMappedPort(61616));
 
        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
        jmsTemplate.setMessageConverter(converter);
 
        return jmsTemplate;
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