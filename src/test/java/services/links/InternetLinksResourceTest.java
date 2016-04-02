package services.links;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import services.MainService;
import utils.WebServiceUtils;

/**
 * Those are not a proper unit tests as they start the server and run against its methods. Test may
 * also create side effects if they fail during execution.
 *
 * @author Robert Marzeta
 */
public class InternetLinksResourceTest {
	private static final String EXISTING_ID = "2";
	private static final String DUMMY_ID = "111";
	private static final String ID_PATH = "id";
	private static final String COUNT_PATH = "count";
	private static final String INTERNET_PATH = "link";

	private WebTarget target;

	@BeforeClass
	public static void method() throws Exception {
		MainService.main(new String[] { "server", "configuration.yml" });
	}

	@Before
	public void setUp() throws Exception {
		target = WebServiceUtils.createWebTarget();
	}

	@Test
	public void testCountAllInternetLinks() throws Exception {
		Integer expected = InternetLinkDao.instance.getInternetLinkCount();

		Assert.assertEquals(expected, fetchActualCount());
	}

	@Test
	public void testGetAllInternetLinks() throws Exception {
		String actual = target.path(INTERNET_PATH).request().accept(MediaType.APPLICATION_JSON)
				.get(String.class)
				.toString();
		final String jsonTag = "links";
		// I don't know why the framework does not generate a proper JSON document
		JSONObject obj = new JSONObject("{" + jsonTag + ":" + actual + "}");
		JSONArray arr = obj.getJSONArray(jsonTag);

		Integer length = arr.length();
		Assert.assertEquals(length, fetchActualCount());

		for (int i = 0; i < length; i++) {
			String id = arr.getJSONObject(i).getString(ID_PATH);
			Assert.assertTrue(InternetLinkDao.instance.containsId(id));
		}
	}

	@Test
	public void testDeleteInternetLinks() throws Exception {
		Integer initialCount = fetchActualCount();
		target.path(INTERNET_PATH).path(EXISTING_ID).request().accept(MediaType.APPLICATION_JSON)
				.delete();
		Integer actualCount = fetchActualCount();
		Integer expectedCount = initialCount - 1;
		Assert.assertEquals(expectedCount, actualCount);
	}

	private Integer fetchActualCount() {
		String response = target.path(INTERNET_PATH).path(COUNT_PATH).request()
				.accept(MediaType.TEXT_PLAIN)
				.get(String.class).toString();
		return Integer.valueOf(response);
	}

	@Test
	public void testPutAndDeleteInternetLink() throws Exception {
		InternetLink entity = createDummyInternetLink(DUMMY_ID);
		Response putResponse = target.path(INTERNET_PATH).request().accept(MediaType.TEXT_HTML)
				.buildPut(Entity.entity(entity, MediaType.APPLICATION_XML)).invoke();
		Assert.assertEquals(Response.Status.CREATED.getStatusCode(), putResponse.getStatus());
		Assert.assertTrue(StringUtils.endsWith(putResponse.getLocation().getPath(),
				DUMMY_ID));

		Response deleteResponse = target.path(INTERNET_PATH).path(DUMMY_ID).request()
				.accept(MediaType.APPLICATION_JSON)
				.delete();
		Assert.assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());
	}

	@Test
	public void testPostAndDeleteInternetLink() throws Exception {
		Integer expected = fetchActualCount() + 1;
		postEntity(DUMMY_ID);
		postEntity(DUMMY_ID); // testing idempotency (duplicate request doesn't change anything)
		Integer actual = fetchActualCount();
		Assert.assertEquals(expected, actual);

		Response deleteResponse = target.path(INTERNET_PATH).path(DUMMY_ID).request()
				.accept(MediaType.APPLICATION_JSON)
				.delete();
		Assert.assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());
	}

	private void postEntity(String DUMMY_ID) {
		InternetLink entity = createDummyInternetLink(DUMMY_ID);
		Response postResponse = target.path(INTERNET_PATH).request().accept(MediaType.TEXT_HTML)
				.buildPost(Entity.entity(entity, MediaType.APPLICATION_XML)).invoke();
		Assert.assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
		Assert.assertTrue(StringUtils.endsWith(postResponse.getLocation().getPath(), DUMMY_ID));
	}

	private InternetLink createDummyInternetLink(String DUMMY_ID) {
		InternetLink entity = new InternetLink(DUMMY_ID, "B", "2222", InternetLinkType.NEWS);
		return entity;
	}
}
