package services.users;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path(value = "/users")
public class UsersResource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	private final UserDao userDao;

	public UsersResource(UserDao userDao) {
		this.userDao = userDao;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{id}")
	public UserResource getById(@PathParam("id") String id) {
		return new UserResource(uriInfo, request, userDao, id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<User> getByName(@QueryParam("name") String name) {
		return userDao.getUsersByName(name);
	}
}
