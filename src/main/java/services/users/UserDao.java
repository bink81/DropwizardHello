package services.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum UserDao {
	instance;
	private final List<User> users = new ArrayList<User>();

	private UserDao() {
		users.add(new User("A", "1"));
		users.add(new User("B", "2"));
		users.add(new User("A", "3"));
	}

	public User getUserById(String id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public Collection<User> getUsersByName(String name) {
		if (StringUtils.isBlank(name)) {
			return users;
		}
		Collection<User> res = new ArrayList<User>();
		for (User user : users) {
			if (user.getName().equals(name)) {
				res.add(user);
			}
		}
		return res;
	}

	public Collection<User> getAllUsers() {
		return users;
	}

	public void insert(User user) {
		users.add(user);
	}
}
