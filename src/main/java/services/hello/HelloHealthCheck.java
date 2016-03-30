package services.hello;

import org.apache.commons.lang3.StringUtils;

import com.codahale.metrics.health.HealthCheck;

public class HelloHealthCheck extends HealthCheck {
	private final String greeting;

	public HelloHealthCheck(String greeting) {
		this.greeting = greeting;
	}

	@Override
	protected Result check() throws Exception {
		if (StringUtils.isEmpty(greeting)) {
			return Result.unhealthy("Configuration doesn't specify a greeting (helloConfiguration -> greeting)");
		}
		return Result.healthy();
	}
}
