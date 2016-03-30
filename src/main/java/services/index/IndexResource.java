package services.index;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import services.users.User;
import services.users.UserDao;
import utils.CollectionToListConverter;

@Path("/")
public class IndexResource {

	private final UserDao userDao;

	public IndexResource(UserDao userDao) {
		this.userDao = userDao;
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Timed
	public List<User> index() {
		Collection<User> collection = userDao.getUsersByName("");
		List<User> list = new CollectionToListConverter<User>(collection).convert();
		return list;
	}
}