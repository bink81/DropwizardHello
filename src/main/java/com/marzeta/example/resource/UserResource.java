package com.marzeta.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.User;
import model.UserDao;

public class UserResource {
	@Context
	private final UriInfo uriInfo;
	@Context
	private final Request request;

	private final String id;

	public UserResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser() {
		return UserDao.instance.getUserByKey(id);
	}
}
