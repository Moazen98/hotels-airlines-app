//package com.zeon.UserPortable.MessageQueue;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.QueueBuilder;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//	@Value("${javainuse.rabbitmq.queue}")
//	String queueName;
//
//	@Value("${javainuse.rabbitmq.exchange}")
//	String exchange;
//
//	@Value("${javainuse.rabbitmq.routingkey}")
//	private String routingkey;
//
////	@Bean
////	Queue queue() {
////		return new Queue(queueName, false);
////	}
//	
//	
//	// this queue is used when the any queue is down and can't get the Object so this queue is like a Rabbish queue that have the dead message
//	@Bean
//	DirectExchange deadLetterExchange() {
//		return new DirectExchange("deadLetterExchange");
//	}
//	
//	@Bean
//	Queue dlq() {
//		return QueueBuilder.durable("deadLetter.queue").build(); 
//	}
//	
//	
//	@Bean
//	Queue queue() {
//		return QueueBuilder.durable("javainuse.queue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
//				.withArgument("x-dead-letter-routing-key", "deadLetter").build();
//	}
//	
//	@Bean
//	Binding DLQbinding() {
//		return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("deadLetter");
//	}
//
//
//	@Bean
//	DirectExchange exchange() {
//		return new DirectExchange(exchange);
//	}
//
//	@Bean
//	Binding binding(Queue queue, DirectExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//	}
//
//	@Bean
//	public MessageConverter jsonMessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}
//
//	
//	@Bean
//	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}
//}
