package services.hello;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;

public class HelloServiceConfiguration extends Configuration {

	@NotNull
	@Valid
	private HelloConfiguration helloConfiguration;

	public HelloConfiguration getHelloConfiguration() {
		return helloConfiguration;
	}

	public void setHelloConfiguration(HelloConfiguration helloConfiguration) {
		this.helloConfiguration = helloConfiguration;
	}
}
