package services.users;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class User {
	private final String id = UUID.randomUUID().toString();

	@NotBlank
	private final String name;

	@URL
	@NotBlank
	private final String url;

	private final Date createdOn = new Date();

	public User(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
}
