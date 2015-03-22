package health;

import com.codahale.metrics.health.HealthCheck;

public class HelloHealthCheck extends HealthCheck {
	private final String helloMessage;

	public HelloHealthCheck(String helloMessage) {
		this.helloMessage = helloMessage;
	}

	@Override
	protected Result check() throws Exception {
		final String saying = String.format(helloMessage, "Hello");
		if (!saying.contains("Hello")) {
			return Result.unhealthy("template doesn't include a greeting");
		}
		return Result.healthy();
	}
}
