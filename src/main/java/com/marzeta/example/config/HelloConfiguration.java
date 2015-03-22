package com.marzeta.example.config;

import javax.validation.constraints.NotNull;

public class HelloConfiguration {

	@NotNull
	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
}
