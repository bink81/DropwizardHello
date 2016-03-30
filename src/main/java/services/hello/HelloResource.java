package services.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(value = "/hello")
public class HelloResource {
	private final HelloConfiguration configuration;

	public HelloResource(final HelloConfiguration configuration) {
		this.configuration = configuration;
	}

	@GET
	public String sayHello(@QueryParam("name") String name) {
		return configuration.getGreeting() + " " + name;
	}
}
