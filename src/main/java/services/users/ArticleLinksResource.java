package services.users;

import java.net.URI;
import java.util.Collection;
import java.util.List;

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

import utils.CollectionToListConverter;

@Path(value = "/article")
public class ArticleLinksResource {
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;

	private final ArticleLinkDao articleLinkDao;

	public ArticleLinksResource(ArticleLinkDao articleLinkDao) {
		this.articleLinkDao = articleLinkDao;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(articleLinkDao.getArticleLinkCount());
	}

	@GET
	@Path("{id}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public ArticleLink getArticleLinkById(@PathParam("id") String id) {
		return articleLinkDao.getArticleLinkById(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<ArticleLink> getArticleLinks() {
		return articleLinkDao.getAllArticleLinks();
	}

	@GET
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ArticleLink> getByName(@QueryParam("name") String name) {
		return new CollectionToListConverter<ArticleLink>(articleLinkDao.getArticleLinksByName(name)).convert();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_HTML })
	public Response updateArticleLink(JAXBElement<ArticleLink> wrapper) {
		ArticleLink articleLink = wrapper.getValue();
		if (articleLink == null) {
			throw new BadRequestException();
		}

		Response res;
		if (articleLinkDao.containsId(articleLink.getId())) {
			articleLinkDao.put(articleLink);
			res = Response.noContent().build();
		} else {
			String newId = articleLinkDao.put(articleLink);
			res = Response.created(createNewUri(newId)).build();
		}
		return res;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_HTML })
	public Response createArticleLink(@Valid ArticleLink articleLink) {
		if (articleLink == null) {
			throw new BadRequestException();
		}
		String newId = articleLinkDao.put(articleLink);
		return Response.created(createNewUri(newId)).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.TEXT_HTML })
	public Response createArticleLink(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("url") String url) {
		ArticleLink articleLink = new ArticleLink(id, name, url);
		String newId = articleLinkDao.put(articleLink);
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
	public ArticleLink deleteArticleLink(@PathParam("id") String id) {
		return articleLinkDao.delete(id);
	}
}
