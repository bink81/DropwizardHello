package services.index;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import utils.WebServiceUtils;

@Path("/")
public class IndexResource {

	@GET
	@Produces(value = MediaType.TEXT_HTML)
	public String index() {
		StringBuilder builder = new StringBuilder();
		builder.append("<html><title>Index</title><body>");
		builder.append("Available links:");
		createLink(builder, assembleIndexUrl());
		createLink(builder, assembleHelloUrl());
		createLink(builder, assembleHelloPingUrl());
		createLink(builder, assembleLinksUrl());
		createLink(builder, assembleLinksUrlForName());
		createLink(builder, assembleLinksUrlForId());
		createLink(builder, assembleLinksUrlForCount());
		builder.append("</body></html>");
		return builder.toString();
	}

	private void createLink(StringBuilder builder, String url) {
		builder.append("<br>");
		builder.append("<a href =\"" + url + "\">" + url + "</a>");
	}

	private String assembleIndexUrl() {
		return WebServiceUtils.getBaseURI() + "/";
	}

	private String assembleHelloUrl() {
		return WebServiceUtils.getBaseURI() + "/hello?name=exampleName";
	}

	private String assembleHelloPingUrl() {
		return WebServiceUtils.getBaseURI() + "/hello/ping";
	}

	private String assembleLinksUrl() {
		return WebServiceUtils.getBaseURI() + "/link";
	}

	private String assembleLinksUrlForName() {
		return WebServiceUtils.getBaseURI() + "/link?name=A";
	}

	private String assembleLinksUrlForId() {
		return WebServiceUtils.getBaseURI() + "/link/1";
	}

	private String assembleLinksUrlForCount() {
		return WebServiceUtils.getBaseURI() + "/link/count";
	}
}