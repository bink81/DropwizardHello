package services.users;

import java.util.Collection;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {

	private final UserDao userDao;

	public DatabaseHealthCheck(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected Result check() throws Exception {
		Collection<User> allUsers = userDao.getAllUsers();
		for (User user : allUsers) {
			// FIXME #user NOCOMMIT
			System.err.println("#user:" + user);
		}
		return Result.healthy();
	}

}