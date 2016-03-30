package services.hello;

import javax.validation.constraints.NotNull;

public class HelloConfiguration {

	@NotNull
	private String greeting;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
