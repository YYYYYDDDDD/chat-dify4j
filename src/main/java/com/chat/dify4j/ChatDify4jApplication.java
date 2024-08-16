package com.chat.dify4j;

import com.chat.dify4j.properties.ChatUrlProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = ChatUrlProperties.class)
public class ChatDify4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatDify4jApplication.class, args);
	}

}
