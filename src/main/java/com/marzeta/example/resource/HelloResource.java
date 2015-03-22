package com.marzeta.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.marzeta.example.config.HelloConfiguration;

@Path(value = "/hello")
public class HelloResource {
	private final HelloConfiguration conf;

	public HelloResource(final HelloConfiguration conf) {
		this.conf = conf;
	}

	@GET
	public String sayHello(@QueryParam("name") String name) {
		return conf.getHello() + " " + name;
	}
}
