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
		createLink(builder, assembleLinksUrl("/"));
		createLink(builder, assembleLinksUrl("/hello?name=A"));
		createLink(builder, assembleLinksUrl("/hello/ping"));
		createLink(builder, assembleLinksUrl("/link"));
		createLink(builder, assembleLinksUrl("/link?name=A"));
		createLink(builder, assembleLinksUrl("/link/1"));
		createLink(builder, assembleLinksUrl("/link/count"));
		createLink(builder, assembleLinksUrl("/quotes"));
		createLink(builder, assembleLinksUrl("/quotes/1"));
		createLink(builder, assembleLinksUrl("/quotes/count"));
		createLink(builder, assembleLinksUrl("/quotes/random"));
		createLink(builder, assembleLinksUrl("/quotes?author=A"));
		builder.append("</body></html>");
		return builder.toString();
	}

	private void createLink(StringBuilder builder, String url) {
		builder.append("<br>");
		builder.append("<a href =\"" + url + "\">" + url + "</a>");
	}

	private String assembleLinksUrl(String path) {
		return WebServiceUtils.getBaseURI() + path;
	}
}