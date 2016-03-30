package services;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import services.hello.HelloConfiguration;
import services.hello.HelloHealthCheck;
import services.hello.HelloResource;
import services.hello.HelloServiceConfiguration;
import services.index.IndexResource;
import services.users.DatabaseHealthCheck;
import services.users.UserDao;
import services.users.UsersResource;

public class MainService extends Application<HelloServiceConfiguration> {
	public static void main(String[] args) throws Exception {
		new MainService().run(args);
	}

	@Override
	public String getName() {
		return "main service";
	}

	@Override
	public void initialize(Bootstrap<HelloServiceConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(final HelloServiceConfiguration conf, final Environment env)
			throws Exception {
		HelloConfiguration messages = conf.getHelloConfiguration();
		env.healthChecks().register("ConfigurationHealthCheck", new HelloHealthCheck(messages.getGreeting()));
		env.jersey().register(new HelloResource(messages));

		UserDao userDao = UserDao.instance;
		env.healthChecks().register("DatabaseHealthCheck", new DatabaseHealthCheck(userDao));
		env.jersey().register(new UsersResource(userDao));
		env.jersey().register(new IndexResource(userDao));
	}
}
