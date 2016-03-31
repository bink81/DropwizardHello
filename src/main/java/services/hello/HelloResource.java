package services.hello;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(value = "/hello")
public class HelloResource {
	private final HelloConfiguration configuration;

	public HelloResource(final HelloConfiguration configuration) {
		this.configuration = configuration;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(@QueryParam("name") String name) {
		String response = configuration.getGreeting() + " " + name;
		return response;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello(@QueryParam("name") String name) {
		String response = configuration.getGreeting() + " " + name;
		return "<?xml version=\"1.0\"?>" + "<hello> " + response + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@QueryParam("name") String name) {
		String response = configuration.getGreeting() + " " + name;
		return "<html> " + "<title>" + "Hello" + "</title>"
				+ "<body>Html content:<br><h3>" + response + "</h3><br></body>" + "</html> ";
	}

	@GET
	@Path("ping")
	public String getPong() {
		return "...pong : " + new Date();
	}
}
