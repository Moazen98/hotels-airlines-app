//package com.zeon.UserPortable.MessageQueue;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQDirectConfig {
//
//	@Bean
//	Queue userRequestDelete() {
//		return new Queue("userRequestDelete", false);
//	}
//
//	@Bean
//	Queue userHotelQueue() {
//		return new Queue("userHotelQueue", false);
//	}
//
//	@Bean
//	Queue userHotelPostQueue() {
//		return new Queue("userHotelPostQueue", false);
//	}
//	
//	@Bean
//	Queue userRegisterQueue() {
//		return new Queue("userRegisterQueue", false);
//	}
//	
//	@Bean
//	Queue userFlightRegisterQueueGo() {
//		return new Queue("userFlightRegisterQueueGo", false);
//	}
//	
//	@Bean
//	Queue userFlightRegisterQueueGoAndReturn() {
//		return new Queue("userFlightRegisterQueueGoAndReturn", false);
//	}
//	
//	@Bean
//	Queue UserSetEditInfoQueue() {
//		return new Queue("UserSetEditInfoQueue", false);
//	}
//
//
//	@Bean
//	DirectExchange exchange() {
//		return new DirectExchange("direct-exchange");
//	}
//
//	@Bean
//	Binding userRequestDeleteBinding(Queue userRequestDelete, DirectExchange exchange) {
//		return BindingBuilder.bind(userRequestDelete).to(exchange).with("userDelete");
//	}
//
//	@Bean
//	Binding userHotelBinding(Queue userHotelQueue, DirectExchange exchange) {
//		return BindingBuilder.bind(userHotelQueue).to(exchange).with("userHotel");
//	}
//
//	@Bean
//	Binding userHotelPostBinding(Queue userHotelPostQueue, DirectExchange exchange) {
//		return BindingBuilder.bind(userHotelPostQueue).to(exchange).with("userHotelPost");
//	}
//	
//	@Bean
//	Binding userRegisterBinding(Queue userRegisterQueue, DirectExchange exchange) {
//		return BindingBuilder.bind(userRegisterQueue).to(exchange).with("userRegister");
//	}
//	
//	@Bean
//	Binding userFlightRegisterBindingGo(Queue userFlightRegisterQueueGo, DirectExchange exchange) {
//		return BindingBuilder.bind(userFlightRegisterQueueGo).to(exchange).with("userFlightRegisterGo");
//	}
//	
//	@Bean
//	Binding userFlightRegisterBindingGoAndReturn(Queue userFlightRegisterQueueGoAndReturn, DirectExchange exchange) {
//		return BindingBuilder.bind(userFlightRegisterQueueGoAndReturn).to(exchange).with("userFlightRegisterGoAndReturn");
//	}
//	
//	@Bean
//	Binding UserSetEditInfoBinding(Queue UserSetEditInfoQueue, DirectExchange exchange) {
//		return BindingBuilder.bind(UserSetEditInfoQueue).to(exchange).with("UserSetEditInfo");
//	}
//	
//	@Bean
//	public MessageConverter jsonMessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}
//	
//	@Bean
//	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(jsonMessageConverter());
//		return rabbitTemplate;
//	}
//
//}