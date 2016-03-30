package services.users;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.codahale.metrics.annotation.Timed;

public class UserResource {
	@Context
	private final UriInfo uriInfo;
	@Context
	private final Request request;

	private final UserDao userDao;

	private final String id;

	public UserResource(UriInfo uriInfo, Request request, UserDao userDao, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.userDao = userDao;
		this.id = id;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser() {
		return userDao.getUserById(id);
	}

	@POST
	@Timed
	public Response createUser(@Valid User user) {
		userDao.insert(user);
		return Response.noContent().build();
	}
}
