package guru.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebApplication {
	
	/*@Autowired
	private JdbcTemplate  jdbcTemplate;
*/
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    
  /*  @Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		//factory.setServerURIs("tcp://localhost:1883");
		factory.setServerURIs("tcp://broker.hivemq.com:1883");
		//factory.setUserName("guest");
		//factory.setPassword("guest");
		return factory;
	}

	// publisher
    
    
    @Bean
    public MessageSource<Object> jdbcMessageSource() {
       JdbcPollingChannelAdapter a = new JdbcPollingChannelAdapter(jdbcTemplate, "SELECT firstName FROM developer");
       return a;
    }

	@Bean
	public IntegrationFlow mqttOutFlow() {
		
		return IntegrationFlows.from("helloWorldInput")
		.transform("Hello "::concat)
		.handle(mqttOutbound())
		.get();
		//return IntegrationFlows.from(jdbcMessageSource(),
		//return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
		return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
						e -> e.poller(Pollers.fixedDelay(1000)))
				.transform(p -> p + " sent to MQTT")
				.handle(mqttOutbound())
				.get();
	}

	@Bean
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("siSampleTopic");
		return messageHandler;
	}

	// consumer

	@Bean
	public IntegrationFlow mqttInFlow() {
		return IntegrationFlows.from(mqttInbound())
				.transform(p -> p + ", received from MQTT")
				.handle(logger())
				.get();
	}

	private LoggingHandler logger() {
		LoggingHandler loggingHandler = new LoggingHandler("INFO");
		loggingHandler.setLoggerName("siSample");
		return loggingHandler;
	}

	@Bean
	public MessageProducerSupport mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer",
				mqttClientFactory(), "siSampleTopic");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		return adapter;
}*/
    
}
