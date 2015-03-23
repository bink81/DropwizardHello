package com.marzeta.example.resource;

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

import model.User;
import model.UserDao;

@Path(value = "/users")
public class UsersResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	public UsersResource() {
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{id}")
	public UserResource getById(@PathParam("id") String id) {
		return new UserResource(uriInfo, request, id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<User> getUserByName(@QueryParam("name") String name) {
		return UserDao.instance.getUserByName(name);
	}
}
