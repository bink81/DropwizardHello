package com.marzeta.example;

import health.HelloHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.marzeta.example.config.HelloConfiguration;
import com.marzeta.example.config.HelloServiceConfiguration;
import com.marzeta.example.resource.HelloResource;
import com.marzeta.example.resource.UsersResource;

public class HelloService extends Application<HelloServiceConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloService().run(args);
	}

	@Override
	public String getName() {
		return "hello";
	}

	@Override
	public void initialize(Bootstrap<HelloServiceConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(final HelloServiceConfiguration conf, final Environment env)
			throws Exception {
		final HelloHealthCheck healthCheck = new HelloHealthCheck(conf
				.getMessages().getHello());
		env.healthChecks().register("helloMessage", healthCheck);
		HelloConfiguration messages = conf.getMessages();
		env.jersey().register(new HelloResource(messages));
		env.jersey().register(new UsersResource());
	}
}
