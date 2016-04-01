package services.index;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class IndexResource {

	public IndexResource() {
	}

	@GET
	@Produces(value = MediaType.TEXT_XML)
	public String index() {
		StringBuilder b = new StringBuilder();
		b.append("<html> <title>Hello</title><body>");
		String url = "http://localhost:8080/hello?name=12332";
		b.append("<a href =\"" + url + "\">" + url + "</a>");
		// List<String> list = new ArrayList<String>();
		// list.add(e);
		// list.add("http://localhost:8080/users?name=A");
		// list.add("http://localhost:8080/users");
		// list.add("http://localhost:8081");
		b.append("</body></html>");
		return b.toString();
	}
}