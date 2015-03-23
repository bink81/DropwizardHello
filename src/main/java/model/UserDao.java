package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public enum UserDao {
	instance;
	private final Map<String, User> users = new LinkedHashMap<String, User>();

	private UserDao() {
		users.put("1", new User(1, "A"));
		users.put("2", new User(2, "B"));
		users.put("3", new User(3, "A"));
	}

	public User getUserByKey(String key) {
		return users.get(key);
	}

	public Collection<User> getUserByName(String name) {
		if (name == null) {
			return users.values();
		}
		Collection<User> res = new ArrayList<User>();
		for (User user : users.values()) {
			if (user.getName().equals(name)) {
				res.add(user);
			}
		}
		return res;
	}
}
