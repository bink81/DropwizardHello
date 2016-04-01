package services.hello;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import utils.WebServiceUtils;

public class HelloResourceTest {

	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		target = WebServiceUtils.createWebTarget();
	}

	@Test
	public void testSayHelloByResponse() throws Exception {
		String actual = target.path("hello").request().accept(MediaType.TEXT_PLAIN).get(Response.class)
				.toString();

		String expected = "InboundJaxrsResponse{ClientResponse{method=GET, uri=http://localhost:8080/hello, status=200, reason=OK}}";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSayHelloByString() throws Exception {
		String actual = target.path("hello").request().accept(MediaType.TEXT_PLAIN).get(String.class);

		String expected = "Hello null";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSayHelloByXml() throws Exception {
		String actual = target.path("hello").request().accept(MediaType.TEXT_XML).get(String.class);

		String expected = "<?xml version=\"1.0\"?><hello> Hello null</hello>";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSayHelloByHtml() throws Exception {
		String actual = target.path("hello").request().accept(MediaType.TEXT_HTML).get(String.class);

		String expected = "<html> <title>Hello</title><body><br><h3>Hello null</h3><br></body></html> ";
		Assert.assertEquals(expected, actual);
	}
}
