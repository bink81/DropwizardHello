package utils;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class TestRestUtils {
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}

	public static WebTarget createWebTarget() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		return client.target(TestRestUtils.getBaseURI());
	}
}
