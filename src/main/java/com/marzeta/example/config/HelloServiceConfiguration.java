package com.marzeta.example.config;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class HelloServiceConfiguration extends Configuration {

	@NotNull
	@Valid
	private HelloConfiguration messages;

	public HelloConfiguration getMessages() {
		return messages;
	}

	public void setMessages(HelloConfiguration messages) {
		this.messages = messages;
	}
}
