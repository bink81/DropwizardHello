package services.users;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

// This configuration is currently not used
public class DatabaseConfiguration extends Configuration {

	@JsonProperty
	@NotEmpty
	public String host = "localhost";

	@JsonProperty
	@Min(1)
	@Max(65535)
	public int port = 27017;

	@JsonProperty
	@NotEmpty
	public String name = "mydb";
}