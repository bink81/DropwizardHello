package services.links;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.google.common.base.Preconditions;

import utils.CollectionToListConverter;

@Path(value = "/link")
public class InternetLinksResource {
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;

	private final InternetLinkDao internetLinkDao;

	public InternetLinksResource(InternetLinkDao internetLinkDao) {
		this.internetLinkDao = internetLinkDao;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(internetLinkDao.getInternetLinkCount());
	}

	@GET
	@Path("{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public InternetLink getInternetLinkById(@PathParam("id") String id) {
		return internetLinkDao.getInternetLinkById(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<InternetLink> getInternetLinks() {
		return internetLinkDao.getAllInternetLinks();
	}

	@GET
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<InternetLink> getByName(@QueryParam("name") String name) {
		return new CollectionToListConverter<InternetLink>(internetLinkDao.getInternetLinksByName(name))
				.convert();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_HTML })
	public Response updateInternetLink(JAXBElement<InternetLink> wrapper) {
		InternetLink internetLink = wrapper.getValue();
		if (internetLink == null) {
			throw new BadRequestException();
		}

		Response res;
		if (internetLinkDao.containsId(internetLink.getId())) {
			internetLinkDao.put(internetLink);
			res = Response.noContent().build();
		} else {
			String newId = internetLinkDao.put(internetLink);
			res = Response.created(createNewUri(newId)).build();
		}
		return res;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_HTML })
	public Response createInternetLink(@Valid InternetLink internetLink) {
		if (internetLink == null) {
			throw new BadRequestException();
		}
		String newId = internetLinkDao.put(internetLink);
		return Response.created(createNewUri(newId)).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response createInternetLink(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("url") String url,
			@FormParam("type") InternetLinkType type,
			@Context HttpServletResponse servletResponse) {
		Preconditions.checkNotNull(name, "name must not be null");
		Preconditions.checkNotNull(url, "url must not be null");
		Preconditions.checkNotNull(type, "type must not be null");

		InternetLink internetLink = new InternetLink(id, name, url, type);
		String newId = internetLinkDao.put(internetLink);
		return Response.created(createNewUri(newId)).build();
	}

	private URI createNewUri(String newId) {
		UriBuilder ub = uriInfo.getRequestUriBuilder();
		URI uri = ub.path(newId).build();
		return uri;
	}

	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public InternetLink deleteInternetLink(@PathParam("id") String id) {
		return internetLinkDao.delete(id);
	}
}
